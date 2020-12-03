

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Classe do servidor. Responsavel por fazer a gerencia dos recursos
 * implementa a interface iServidor para poder ser requerida remotamente
 * acoesDAO = Objeto para manipulacao dos pacotes
 * interesses = Lista de todos os interesses cadastrados
 */
public class Servidor extends UnicastRemoteObject implements iServidor{
	private static final long serialVersionUID = 1L;
    private AcaoDAO acoesDAO;
    private HashMap<String, ArrayList<String>> cotacoes = new HashMap<String, ArrayList<String>>();
    private ArrayList<Interesse> interesses = new ArrayList<Interesse>();

	public Servidor() throws RemoteException {
        super();
        // Inicia os objetos de manipulacao
        this.acoesDAO = new AcaoDAO();
    }

    /**
    * Funcao para informar a um servidor a entrada de um novo cliente
    * usuario = Nome do usuario entrando
    */
    @Override
    public String sayHello(String usuario) throws RemoteException {
        System.out.println("Usuario " + usuario + " acabou de logar");
        return "Bem vindo, " + usuario;
    }

    /**
    * Funcao para listar as hospedagens
    */
    @Override
    public ArrayList<Acao> listarAcoes() throws RemoteException {
        System.out.println("Listando Acoes");
        return this.acoesDAO.select();
    }

    /**
     * Função para adicionar uma cotacao de acao para um usuario
     * @params codigo: Codigo da acao a ser monitorada
     * @params usuario: Codigo do usuario
     */
    public void adicionarCotacao(String codigo, String usuario) throws RemoteException {
        System.out.println("Adicionando acao " + codigo + " a lista de cotacoes de " + usuario);
        
        ArrayList<String> acoes = this.cotacoes.get(usuario);
        if (acoes == null) {
            acoes = new ArrayList<String>();
        } 

        acoes.add(codigo);
        this.cotacoes.put(usuario, acoes);
    }

    /**
     * Função para remover uma cotacao de acao para um usuario
     * @params codigo: Codigo da acao sendo monitorada
     * @params usuario: Codigo do usuario
     */
    public void removerCotacao(String codigo, String usuario) throws RemoteException {
        System.out.println("Removendo acao " + codigo + " a lista de cotacoes de " + usuario);

        ArrayList<String> acoes = this.cotacoes.get(usuario);
        if (acoes != null) {
            int index = acoes.indexOf(codigo);
            if (index > -1) {
                acoes.remove(index);
            }
        }
        this.cotacoes.put(usuario, acoes);
    }

    /**
     Função para efetivar a transação entre dois usuarios
     @params codigo: Codigo da acao sendo vendida
     @params comprador: Codigo do comprador
     @params vendedor: Codigo do vendedor
     */
    private void efetuarCompra(String codigo, String comprador, iCliente cliente) throws RemoteException {
        System.out.println("Efetuando compra da acao " + codigo);

        // Troca o proprietario da acao
        Acao acao = this.acoesDAO.get(codigo);

        // Remove a acao das cotacoes do vendedor
        this.removerCotacao(codigo, acao.usuario);

        // Adiciona a acao as cotacoes do comprador
        this.adicionarCotacao(codigo, comprador);
        
        
        acao.transacionar(comprador, cliente);

    }

    /**
     Função para obter as cotacoes atuais da lista de cotacao do usuario
     @params usuario: Codigo do usuario
    */
    public ArrayList<Acao> obterCotacoes(String usuario) throws RemoteException {
        System.out.println("Listando acoes para cotacao do usuario " + usuario);

        ArrayList<String> codigosAcoes = this.cotacoes.get(usuario);
        ArrayList<Acao> acoes = new ArrayList<Acao>();

        for(String codigo : codigosAcoes){
            Acao acao = this.acoesDAO.get(codigo);
            acoes.add(acao);
        }

        return acoes;
    }

    /**
     Função para um usuário adicionar a sua ação para venda no servidor
     @params codigo: O codigo da acao
     @params quantidade: A quantidade de cotas sendo vendidas
     @params valor: Valor total da oferta
     @params usuario: Codigo do usuario
     */
    public Acao venderAcao(String codigo, int quantidade, double valor, String usuario, iCliente cliente) throws RemoteException {
        System.out.println("Registrando nova venda de acao para o usuario " + usuario);

        Acao acao = new Acao(codigo, quantidade, valor, usuario,cliente);

        this.acoesDAO.insert(acao);

        this.checarInteresses(acao);

        return acao;
    }

    /**
     Função para um usuário adicionar uma oferta de compra para uma ação do servidor
     @params codigo: O codigo da acao
     @params valor: Valor total da oferta
     @params usuario: Codigo do usuario
     */
    public boolean comprarAcao(String codigo, double valor, String usuario, iCliente cliente) throws RemoteException {
        System.out.println("Registrando nova compra de acao para o usuario " + usuario);

        Acao acao = this.acoesDAO.get(codigo);

        if (acao == null) 
            return false;

        if (valor == acao.preco) {
            this.efetuarCompra(codigo, usuario, cliente);
        } else {
            acao.ofertas.put(usuario, valor);
        }

        this.checarInteresses(acao);

        return true;
    }


    /**
    * Funcao para registrar um interesse no servidor
    * @params codigo: Codigo da acao que deseja
    * @params cliente: Objeto remoto do cliente
    */
    @Override
    public Interesse registrarInteresse(String codigo, double preco, boolean checarMaisBaratos, iCliente cliente) throws RemoteException {
        Interesse interesse = new Interesse(codigo, preco, checarMaisBaratos, cliente);
        this.interesses.add(interesse);
        return interesse;
    }

    private void checarInteresses(Acao acao) throws RemoteException {
        for(Interesse interesse : this.interesses) {
            if (!interesse.codigo.equals(acao.getId())) { 
                continue;
            }

            for (Map.Entry<String, Double> entry : acao.ofertas.entrySet()) {
                double oferta = entry.getValue();
                
                if (interesse.checarMaisBaratos && (acao.preco < interesse.preco || oferta < interesse.preco)) {
                    interesse.notificarCliente();
                } else if (!interesse.checarMaisBaratos && (acao.preco > interesse.preco || oferta > interesse.preco)) {
                    interesse.notificarCliente();
                }
            }
        }
    }

    /**
    * Funcao para excluir um interesse do servidor
    * id = ID do interesse sendo excluido
    */
    @Override
    public boolean excluirInteresse(long id) throws RemoteException {
        for (int i = 0; i < interesses.size(); i++) {
            Interesse interesse = interesses.get(i);
            if(interesse.getId() == id){
                if(interesse.excluirInteresse()){
                    this.interesses.remove(i);
                    return true;
                };
            }
        }
        return false;
    }

    // Funcao para executar a logica principal do servidor
    public void run() throws RemoteException, AlreadyBoundException, AccessException{
        Registry registry = LocateRegistry.createRegistry(8080);
        Scanner in = new Scanner(System.in);
        registry.bind("Servidor", this);
        System.out.println("Servidor pronto para novos clientes");
        boolean run = true;
        do {
            System.out.println("O que deseja fazer?");
            System.out.println("0 - sair");
            int opcao = in.nextInt();
            in.nextLine();
            if(opcao == 0){
                System.out.println("Adeus!");
                run = false;
            }
            else{
                System.out.println("Opcao invalida");
            }
        } while (run);
        in.close();
        System.exit(0);
    }

    // MAIN
    // Apenas instancia a classe e chama a logica principal
    public static void main(String[] args){
        try {
            new Servidor().run();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

}
