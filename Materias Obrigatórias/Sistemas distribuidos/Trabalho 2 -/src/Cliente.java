import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Classe do cliente. Faz a camada de usuario da aplicacao
 * implementa a interface iCliente para poder ser requerida remotamente
 * interesses = Lista de interessados cadastrados por esse usuario
 */
public class Cliente extends UnicastRemoteObject implements iCliente{
	private static final long serialVersionUID = 1L;
    private ArrayList<Interesse> interesses = new ArrayList<Interesse>();

    public Cliente() throws RemoteException {
        super();
    }

	// MAIN
	// Apenas instancia a classe e chamada a logica principal
    
    public static void main(String[] args){
        try {
            new Cliente().run();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void notificarVendaEfetuada(String comprador, String codigo, double valor) throws RemoteException {
        System.out.println("Parabens! Nova venda da acao " + codigo + " efetuada pelo valor " + valor + " para o usuario " + comprador);
    }

    @Override
    public void notificarCompraEfetuada(String vendedor, String codigo, double valor)  throws RemoteException {
        System.out.println("Parabens! Nova compra da acao " + codigo + " efetuada pelo valor " + valor + " do usuario " + vendedor);
    }

	/**
	 * Funcao para notificar um cliente sobre um interesse
	 * interesse = Interesse registrado previamente que atende
	 *             o evento cadastrado
	 */
    @Override
    public void notificarInteresse(Interesse interesse) throws RemoteException{
      System.out.println("Atencao, voce tem uma notificacao:");
      System.out.println(interesse);
	}

	/**
	 * Funcao para excluir um interesse de um cliente
	 * interesse = Objeto do interesse que deve ser excluido
	 */
    @Override
    public boolean excluirInteresse(Interesse interesse) throws RemoteException{
      for (int i = 0; i < this.interesses.size(); i++) {
          Interesse inter = interesses.get(i);
          if(inter.getId() == interesse.getId()){
              this.interesses.remove(i);
              return true;
          }
      }
      return false;
  }

	// Logica principal da aplicacao
    public void run() throws RemoteException, MalformedURLException, NotBoundException {
        iServidor server = (iServidor) Naming.lookup("//localhost:8080/Servidor");
        Scanner in = new Scanner(System.in);
        System.out.println("Qual o seu nome?");
        String nome = in.nextLine();
        String resposta = server.sayHello(nome);
        System.out.println(resposta);
        boolean run = true;
        do {
          System.out.println("O que deseja fazer?");
          System.out.println("1 - listar todas as acoes");
          System.out.println("2 - comprar acao");
          System.out.println("3 - vender acao");
          System.out.println("4 - registrar cotacao");
          System.out.println("5 - excluir cotacao");
          System.out.println("6 - listar cotacoes atuais");
          System.out.println("7 - listar meus interesses");
          System.out.println("8 - registrar interesse");
          System.out.println("9 - cancelar interesse");
          System.out.println("0 - sair");
          int opcao = in.nextInt();
          in.nextLine();
          String id;
          double valor;
          ArrayList<Acao> acoes;
          int quantidade;
         
          switch (opcao) {
              case 1:
                  acoes = server.listarAcoes();
                  for (Acao acao : acoes) {
                      System.out.println(acao.toString());
                  }
                  break;
              case 2:
                  System.out.println("Informe o codigo da acao para comprar");
                  id = in.nextLine();

                  System.out.println("Informe o valor que deseja pagar");
                  valor = in.nextDouble();

                  if (server.comprarAcao(id, valor, nome, (iCliente) this)) {
                    System.out.println("Oferta realizada com sucesso!");
                  } else {
                    System.out.println("Nao foi possivel cadastrar a oferta para esta acao!");
                  }

                  break;
              case 3:
                  System.out.println("Informe o codigo da acao:");
                  id = in.nextLine();

                  System.out.println("Informe a quantidade de cotas que ira ofertar:");
                  quantidade = in.nextInt();
                  in.nextLine();

                  System.out.println("Informe o valor de oferta:");
                  valor = in.nextDouble();

                  Acao acao = server.venderAcao(id, quantidade, valor, nome, (iCliente) this);

                  if (acao == null) {
                    System.out.println("Nao foi possivel efetivar a oferta da acao!");
                  } else {
                    System.out.println("Acao ofertada com sucesso! Nova acao:");
                    System.out.println(acao);
                  }

                  break;
              case 4:
                  System.out.println("Informe o codigo da acao que deseja cotar");
                  id = in.nextLine();
                  server.adicionarCotacao(id, nome);
                  System.out.println("Cotacao adicionada com sucesso!");
                  break;
              case 5:
                  System.out.println("Informe o codigo da acao que deseja remover");
                  id = in.nextLine();
                  server.removerCotacao(id, nome);
                  System.out.println("Cotacao removida com sucesso!");
                  break;
              case 6:
                  acoes = server.obterCotacoes(nome);
                  for( Acao cotacao : acoes){
                    
                      System.out.println(cotacao);
                  }
                  break;
              case 7:
                  for (Interesse interesse : this.interesses) {
                      System.out.println(interesse.toString());
                  }
                  break;
              case 8:
                  System.out.println("Informe o codigo da acao que deseja receber atualizacoes");
                  id = in.nextLine();
                  System.out.println("Informe o valor que deseja monitorar para esta acao");
                  valor = in.nextDouble();
                  System.out.println("Deseja receber notificacoes quando:");
                  System.out.println("1 - A cotacao estiver acima deste valor");
                  System.out.println("2 - A cotacao estiver abaixo deste valor");
                  int check = in.nextInt();
                  in.nextLine();
                  Interesse interesse = server.registrarInteresse(id, valor, check == 2, (iCliente) this);
                  this.interesses.add(interesse);
                  System.out.println("Interesse cadastrado com sucesso!");
                  break;
              case 9:
                  System.out.println("Informe o id do interesse que deseja remover");
                  long idInteresse = in.nextLong();
                  if(server.excluirInteresse(idInteresse)){
                      System.out.println("Interesse excluido com sucesso!");
                  }else{
                      System.out.println("Nao foi possivel excluir o interesse!");
                  }
                  break;
              case 0:
                  System.out.println("Adeus!");
                  run = false;
                  break;
              default:
                  System.out.println("Opcao invalida");
                  break;
          }
      } while (run);
      in.close();
      System.exit(0);
  }
}
