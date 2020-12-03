const express = require('express');
const router = express.Router();
const uuid4 = require('uuid/v4'); // Gerar identificadores unicos
const moment = require('moment'); // Trabalhar com manipulação de data
const maxBy = require('lodash/maxBy'); // Um valor maximo em um array, por uma chave do objeto
const axios = require('axios') // Fazer requisições HTTP
const sleep = ms => new Promise(resolve => setTimeout(resolve, ms)); // Trava a thread
const cotations = { } // Lista de cotacoes
const users = { } // Lista de usuarios => porta que esta ouvindo
const stocks = [ ]; // Lista de ações
const buyIns = [ ]; // Lista de compras

let isStocksInUse = false

// Função pra pegar todas as cotacoes de um usuario
function getUserCotationStocks(owner){
  const user_stocks_codes = cotations[owner] || []
  const sell_stocks       = stocks.filter(stock => user_stocks_codes.includes(stock.code))
  const buy_stocks        = buyIns.filter(bid => user_stocks_codes.includes(bid.code))
  return { venda: sell_stocks, compra: buy_stocks }
}

// Função para checar se algum usuário deve ser notificado
function checkUserNotifications(stock, price){
  for (const user in users) {
    if (users.hasOwnProperty(user)) {
      if(cotations[user] && cotations[user].includes(stock.code))
        notify(user, { message: 'Uma ação da sua lista de cotações recebeu uma atualização', data: stock.code })

      users[user].notify.forEach(item => {
        const { code, maxGain, maxLoss } = item
        if (code !== stock.code) return
        
        if (maxGain <= price) {
          notify(user, { message: `A ação ${code} superou o limite de ganho. Lance de ${price}`, data: stock })
        } else if (maxLoss >= price)  {
          notify(user, { message: `A ação ${code} superou o limte de perda. Lance de ${price}`, data: stock })
        }
      })
    }
  }
}

// Verifica se uma ação tem uma oferta que corresponde
function checkStockBids(stock){
  const { bids, owner, id } = stock
  for (const bid of bids) {
    if(bid.price == stock.minPrice)
      confirmDeal(id, bid.bider, owner, bid.id)
  }
}

// Rota para primeira conexao com o servidor
router.get('/', (req,res) => {
  console.log("Listando pontos da aplicacao")
  const { user, port } = req.query;
  users[user] = { port, notify: [] }
  console.log(users)
  return res.status(200).send({
    message: "Welcome",
    routes: ["GET /stocks/", "PUT /stocks/", "POST /stocks/:id/bid/"]
  });
})

// Rota para listar acoes
// Se informado o usuario, lista as cotacoes dele
router.get('/stocks/', (req, res) => {
  if (!isStocksInUse) {
    console.log("Listando acoes disponiveis")
    isStocksInUse = true
    const { owner } = req.query;
    // Lista cotações
    if (owner) {
      user_stocks = getUserCotationStocks(owner)
      isStocksInUse = false
      return res.status(200).send(user_stocks);
    } else {
    // Lista todas as ações
      isStocksInUse = false
      return res.status(200).send({ venda: stocks, compra: buyIns })
    }
  } else {
    return res.status(403).send({ message: "Opera ção indisponivel no momento" });
  }
});

// Rota para criar uma nova cotacao
router.put('/stocks/cotation', (req, res) => {
  if (!isStocksInUse) {
    console.log("Adicionando cotação")
    isStocksInUse = true
    const { owner, code } = req.body;
  
    if(!Array.isArray(cotations[owner]))
      cotations[owner] = []
    cotations[owner].push(code)
  
    user_stocks = getUserCotationStocks(owner)
    
    isStocksInUse = false
    return res.status(200).send(user_stocks);
  } else {
    return res.status(403).send({ message: "Operação indisponivel no momento" });
  }
})

// Rota para deletar uma cotacao
router.delete('/stocks/cotation', (req, res) => {
  if (!isStocksInUse) {
    console.log("Excluindo cotação")
    isStocksInUse = true

    const { owner, code } = req.body;
    const idIndex = cotations[owner].findIndex(cotationCode => cotationCode === code)
    cotations[owner].splice(idIndex, 1)
    user_stocks = getUserCotationStocks(owner)

    isStocksInUse = false
    return res.status(200).send(user_stocks);
  } else {
    return res.status(403).send({ message: "Operação indisponivel no momento" });
  }
})

// Rota para criar e atualizar uma cotacao
router.put('/stocks/', (req, res) => {
  if(!isStocksInUse){
    console.log("Cadastrando acao")
    isStocksInUse = true
    const { owner, code, quantity, minPrice, dueData, id } = req.body;
    let newStock = {}
  
    // Se o id da acao foi informada. Atualiza ela
    if (id) {
      const index = stocks.findIndex(stock => stock.id === id && owner === stock.owner);
      // Se não encontrou a acao a ser modificada, retorna erro
      isStocksInUse = false
      if (index === -1) {
        return res.status(404).send({ message: "Not found" });
      }
  
      newStock = {
        ...stocks[index],
        minPrice:       minPrice      !== undefined ? minPrice                    : stocks[index].minPrice,
        quantity:       quantity      !== undefined ? quantity                    : stocks[index].quantity,
        dueData:        dueData       !== undefined ? moment(dueData)             : stocks[index].dueData,
      };
  
      stocks[index] = newStock
  
    // Do contrario, cria um novo
    } else {
      const newId = uuid4()
      if(!Array.isArray(cotations[owner]))
        cotations[owner] = []
      cotations[owner].push(code)

      const currentBids = buyIns.filter(item => item.code === code)
  
      newStock = {
        id: newId,
        owner,
        code,
        minPrice,
        quantity,
        bids: currentBids,
        dueData: moment(dueData),
      }
      stocks.push(newStock);
    }
    checkUserNotifications(newStock, newStock.minPrice)
    checkStockBids(newStock)
    isStocksInUse = false
    return res.status(200).send(newStock);
  } else {
    return res.status(403).send({ message: "Operação indisponivel no momento" });
  }
});

// Rota para comprar uma acao
router.post('/stocks/:code/bid/', (req, res) => {
  if (!isStocksInUse) {
    console.log("Criando oferta para acao")
    isStocksInUse = true
    const { code } = req.params;
    const { price, quantity, bider, dueData } = req.body;
    
    for (const stock of stocks) {
      if (stock.code !== code) continue;
        
      if (stock.quantity == quantity && stock.minPrice == price) {
        confirmDeal(stock.id, bider, stock.owner)
        if(!Array.isArray(cotations[bider]))
          cotations[bider] = []
          cotations[bider].push(code)
        isStocksInUse = false
        return res.status(200).send({ message: "Compra efetuada com sucesso" })
      }
  
      if (stock.quantity >= quantity) {
        stock.bids.push({ id: uuid4(), price, bider, dueData })
        if(!Array.isArray(cotations[bider]))
          cotations[bider] = []
          cotations[bider].push(code)
        checkUserNotifications(stock, price)
        isStocksInUse = false
        return res.status(200).send(stock);
      }
    }

    console.log("Nova oferta de compra")
    if(!Array.isArray(cotations[bider]))
      cotations[bider] = []
    cotations[bider].push(code)
    const newBid = { id: uuid4(), code, price, bider, dueData: moment(dueData) }
    buyIns.push(newBid)
    isStocksInUse = false
    checkUserNotifications(newBid, price)
    return res.status(200).send(newBid);

  } else {
    return res.status(403).send({ message: "Operação indisponivel no momento" });
  }
});

// Rota para cadastrar interesse em notificacao
router.post('/notify/', (req, res) => {
  console.log("Novo pedido de notificação")
  const { user, code, maxLoss, maxGain } = req.body;
  users[user].notify.push({ code, maxLoss, maxGain })
  return res.status(200).send({ message: "Pedido de notificação cadastrado" })
})

// Função para notificar um cliente
function notify(user, payload) {
  const port = users[user].port
  axios.post(`http://localhost:${port}/notify`, payload)
  .then(() => console.log("Notificação enviada"))
  .catch((err) => console.error("Erro na notificação", err))
}

// Função para efetivar uma negociação
function  confirmDeal(stockId, buyer, seller, bidId = undefined) {
  console.log(`Confirmar oferta stockId:${stockId} buyer:${buyer} seller:${seller}`)
  const stockIndex = stocks.findIndex(item => item.id === stockId)
  const stock = stocks[stockIndex]
  stock.owner = buyer
  stock.dueData = undefined
  stock.bids = []

  if(!Array.isArray(cotations[buyer]))
    cotations[buyer] = []
  cotations[buyer].push(stock.id)

  notify(buyer, { message: "Nova compra realizada", data: stock })
  notify(seller, { message: "Nova venda realizada", data: stock })

  stocks.splice(stockIndex, 1)

  if (bidId) {
    const bidIndex = buyIns.findIndex(item => item.id === bidId)
    buyIns.splice(bidIndex, 1)
  }

}


// Thread secundaria para verificar experição de ofertas
async function checkExpiredOffers() {
  while (true) {
    if(!isStocksInUse){
      isStocksInUse = true
      console.log("Verificando se ofertas expiraram")

      // Vendas
      for (const index in stocks) {
        const stock = stocks[index]

        if (!!stock.dueData && moment().isSameOrAfter(moment(stock.dueData))) {
          const maxBid = maxBy(stock.bids, "price")

          if (maxBid && maxBid.price >= stock.minPrice) {
            confirmDeal(stock.id, maxBid.bider, stock.owner, maxBid.id)
          } else {
            console.log(`Removendo oferta ${stock.id}`)
            stocks.splice(index, 1)
            notify(stock.owner, { message: "Oferta expirada", data: stock.id })
          }
        }

        // Compras
        checkExpiredBids(stock.bids)
      }

      // Compras
      checkExpiredBids(buyIns)

      isStocksInUse = false
    } else {
      console.log("Não é possivel verificar agora")
    }
    await sleep(60000) // Await 1min
  }
}

// Função para verificar se uma oferta de compra expirou
function checkExpiredBids(bids){
  for (const index in bids) {
    
    const bid = bids[index]
    if (moment().isSameOrAfter(moment(bid.dueData))) {
      console.log(`Removendo oferta ${bid.id}`)
      bids.splice(index, 1)
      notify(bid.bider, { message: "Oferta expirada", data: bid.id })
    }
  }
}

checkExpiredOffers(); // Starta thread

module.exports = router;
 