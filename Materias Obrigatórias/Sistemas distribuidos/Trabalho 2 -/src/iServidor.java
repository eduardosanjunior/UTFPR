
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Interface para chamada remota de um servidor
 */
public interface iServidor extends Remote {

    public String sayHello(String usuario) throws RemoteException;

    public ArrayList<Acao> listarAcoes() throws RemoteException;

    public void adicionarCotacao(String codigo, String usuario) throws RemoteException;

    public void removerCotacao(String codigo, String usuario) throws RemoteException;

    public ArrayList<Acao> obterCotacoes(String usuario) throws RemoteException;

    public Acao venderAcao(String codigo, int quantidade, double valor, String usuario, iCliente cliente) throws RemoteException;

    public boolean comprarAcao(String codigo, double valor, String usuario, iCliente cliente) throws RemoteException;

    public Interesse registrarInteresse(String codigo, double preco, boolean checarMaisBaratos, iCliente cliente) throws RemoteException;

    public boolean excluirInteresse(long id) throws RemoteException;

}
