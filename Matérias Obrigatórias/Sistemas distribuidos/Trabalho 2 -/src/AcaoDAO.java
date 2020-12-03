
import java.util.*;

/**
 * Classe para centralizar a manipulacao de Objetos Hospedagem
 * hospedagens = List das hospedagens criadas no servidor
 */
public class AcaoDAO {
    private ArrayList<Acao> acoes = new ArrayList<Acao>();
    private boolean comprando = false;

    /**
     * Funcao para apagar uma Hospedagem da lista de hospedagens
     * hosp = Hospedagem a ser apagada
     * retorna true caso consiga apagar, do contrario retorna false
     * OBS: o metodo .remove() faz uso do metodo .equals() da Hospedagem
     *      para comparar os objetos
     */
    public boolean delete(Acao acao){
        return this.acoes.remove(acao);
    }

    /**
     * Funcao para inserir uma nova Hospedagem na lista de hospedagens
     * pass = Hospedagem a ser inserida
     */
    public boolean insert(Acao acao){
        return this.acoes.add(acao);
    }

    public Acao get(String codigo){
        for(Acao acao : this.acoes){
            if(acao.getId().equals(codigo)) 
                return acao;
        }
        return null;
    }

    /**
     * Funcao para retornar a lista atual de hospedagens
     */
    public ArrayList<Acao> select(){
        return this.acoes;
    }

    /**
     * Funcao para retornar se uma compra ja esta sendo realizada
     */
    public boolean isComprando(){
        return this.comprando;
    }

    /**
     * Funcao para iniciar uma compra
     */
    public void startCompra(){
        this.comprando = true;
    }

    /**
     * Funcao para encerrar uma compra
     */
    public void endCompra(){
        this.comprando = false;
    }
}
