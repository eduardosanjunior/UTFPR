
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface para chamada remota de um cliente
 */
public interface iCliente extends Remote {

    public void notificarVendaEfetuada(String comprador, String codigo, double valor) throws RemoteException;

    public void notificarCompraEfetuada(String vendedor, String codigo, double valor)  throws RemoteException;
    
    public void notificarInteresse(Interesse interesse) throws RemoteException;

    public boolean excluirInteresse(Interesse interesse) throws RemoteException;
}
