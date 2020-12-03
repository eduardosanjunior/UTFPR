package beltraoluis.Estrutura.ListaEncadeada;

import beltraoluis.Exception.ForaLimitesException;
import beltraoluis.Exception.ListaVaziaException;

/**
 * A classe Lista define as caracteristicas de uma lista
 * 
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html>Byte</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Short.html>Short</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html>Integer</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html>Long</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Float.html>Float</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Double.html>Double</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Character.html>Character</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html>Boolean</a>
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */
public abstract class ListaEncadeada<T extends Comparable<T>>{
    protected Elemento<T> inicio;
    protected int tamanho;
    
    /**
     * Lista Encadeada eh o construtor base para as listas que derivam desta classe e nao recebe
     * nenhum parametro.
     */
    public ListaEncadeada(){
        this.inicio = null;
        this.tamanho = 0;
    }
    
    /**
     * O metodo tamanho retorna o tamanho da lista.
     * @return tamanho da lista.
     */
    public int tamanho(){
        return this.tamanho;
    }
    
    public T ver(int pos) throws ListaVaziaException, ForaLimitesException{
        if(pos >= this.tamanho){
            if(this.tamanho == 0) throw new ListaVaziaException("A estrutura esta vazia!");
            throw new ForaLimitesException("A posicao esta fora dos limites da estrutura!");
        }
        int cont;
        Elemento<T> elemento = this.inicio;
        for(cont = 0; cont < pos; cont++){
            elemento = elemento.getProximo();
        }
        return elemento.getValor();
    }
    
    public abstract void inserir(T elemento);
    
    public abstract T retirar() throws ListaVaziaException;
}
