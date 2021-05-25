
import java.io.Serializable;
import java.util.*;
import java.rmi.RemoteException;
/**
 * Classe para representar a reserva em um Hotel
 * id = ID aleatorio da Hospedagem
 * destino = Cidade ou Hotel onde sera feita a reserva
 * entrada = Data de check-in no Hotel
 * saida = Data de check-out no Hotel
 * quartos = Numero de quartos sendo reservados
 * pessoas = Numero de visitantes
 * usuario = Nome do usuario que realizou a reserva
 * disponivel = Flag para disponibilidade da reserva para compra
 * preco = Valor da reserva
 * OBS: A classe implementa a interface Serializable para poder ser serializada
 *      e enviada atraves do RMI
 */
public class Acao implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    public int quantidade;
    public String usuario;
    public double preco;
    private iCliente cliente;
    public HashMap<String, Double> ofertas = new HashMap<String, Double>();

    public Acao(String codigo, int quantidade, double preco, String usuario, iCliente cliente) {
        this.id             = codigo;
        this.quantidade     = quantidade;
        this.usuario        = usuario;
        this.preco          = preco;
        this.cliente        = cliente;
    }

    public String getId() { return this.id; }

    /**
     * Override da chamada padrao de comparacao de objetos
     * Verifica se ambos possuem o mesmo ID
     */
    public boolean equals(Acao obj) {
        return obj.getId() == this.getId();
    }

    public void transacionar(String comprador, iCliente cliente) throws RemoteException {
        this.cliente.notificarVendaEfetuada(comprador, this.id, this.preco);

        cliente.notificarCompraEfetuada(this.usuario, this.id, this.preco);

        this.usuario = comprador;
        this.cliente = cliente;
    }
    
    /**
     * Override da chamada padrao de conversao de objetos para String
     * Retorna algumas informacoes sobre a reserva
     */
    @Override
    public String toString() {
        String str = "Acao: [";
        str += "CODIGO: " + this.id + ", ";
        str += "OWNER: " + this.usuario + ", ";
        str += "QUANT: " + this.quantidade + ", ";
        str += "R$: " + this.preco + ", ";
        str += "OFERTAS: [";
        for(Map.Entry<String, Double> entry : this.ofertas.entrySet()) {
            String usuario = entry.getKey();
            double oferta = entry.getValue();
            str += usuario + ":" + oferta + ";";
        }
        str += "]";
        str += "]";
        return str;
    }
}
