
import java.io.Serializable;
import java.util.*;

/**
 * cliente = Objeto remoto do cliente que registrou o interesse
 * OBS: A classe implementa a interface Serializable para poder ser serializada
 *      e enviada atraves do RMI
 */
public class Interesse implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id = new Random().nextLong();
    public double preco;
    public boolean checarMaisBaratos;
    public String codigo;
    private iCliente cliente;

    public Interesse(String codigo, double preco, boolean checarMaisBaratos, iCliente cliente){
        this.codigo = codigo;
        this.preco = preco;
        this.checarMaisBaratos = checarMaisBaratos;
        this.cliente = cliente;
    }

    public long getId() { return this.id; }

    /**
     * Funcao para notificar o cliente que um evento que atende
     * ao interesse registrado foi adicionado no servidor
     */
    public void notificarCliente() {
        try {
          // Faz a chamada remota do cliente para notificar sobre este interesse
          this.cliente.notificarInteresse(this);
        } catch (Exception e) {
          System.out.println(e.toString());
          e.printStackTrace();
        }

    }

    /**
     * Funcao para excluir um interesse de um cliente
     * OBS: esta funcao apaga o interesse apenas do lado do cliente
     *      e nao do servidor
     */
    public boolean excluirInteresse() {
        try {
            // Faz a chamada remota do cliente para apagar este interesse
            return this.cliente.excluirInteresse(this);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Override da chamada padrao de comparacao de objetos
     * Verifica se ambos possuem o mesmo ID
     */
    public boolean equals(Interesse obj) {
        return obj.getId() == this.getId();
    }

    /**
     * Override da chamada padrao de conversao de objetos para String
     * Retorna algumas informacoes sobre a reserva
     */
    @Override
    public String toString() {
        String str = "Interesse: [ ";
        str += "ID: " + this.id + ", ";
        str += "ACAO: " + this.codigo + "]";
        return str;
    }
}
