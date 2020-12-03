package beltraoluis.Estrutura.ListaEncadeada;

/**
 * A classe elemento representa uma estrutura que conhece o elemento seguinte e intera em apenas um sentindo.
 * Essa classe eh uma clase generica de modo que para utilizar os tipos primitivos sera necessario encapsular
 * eles em um dos tipos (Classes) definidos pela linguagem java.
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
 * @version 28/05/2014
 */
public class Elemento<T extends Comparable<T>> 
{
    /**
     * essa estrutura pode ser graficamente representada da seguinte maneira.
     * -------------------      -------------------
     * | valor | proximo | ---> | valor | proximo |  ---> (...)
     * -------------------      -------------------
     */
    
    private T valor; /** a variavel valor eh responsavel por armazenar o dado  que sera passado ao elemento */
    private Elemento<T> proximo; /** o Elemento<T> proximo representa o elemento seguinte na estrutura */
    
    /**
     * O construtor da classe Elemento<T> ira criar e inicializar um objeto com im valor e que conhece um proximo elemento
     * de mesmo tipo.
     * @param valor eh o valor que sera armazenado pelo elemento.
     * @param proximo eh o proximo elemento da estrutura.
     */
    public Elemento(T valor, Elemento<T> proximo){
        this.valor = valor;
        this.proximo = proximo;
    }
    
    /**
     * O metodo setValor eh o setter responsavel por alterar o valor do atributo valor.
     * @param valor eh o novo valor que sera atribuido ao atributo valor.
     */
    public void setValor(T valor){
        this.valor = valor;
    }
    
    /**
     * O metodo setValor eh o getter responsavel por retornar o valor do atributo valo.
     * @return retorna o atributo valor.
     */
    public T getValor(){
        return this.valor;
    }
    
    /**
     * O metodo setProximo eh o setter responsavel por atribuir o proximo elemento da estrutura ao atributo proximo.
     * @param proximo representa o proximo elemento do estrutura.
     */
    public void setProximo(Elemento<T> proximo){
        this.proximo = proximo;
    }
    
    /**
     * O metodo getProximo eh o getter que retorna o proximo elemento da estrutura que esta armazenado no atributo proximo.
     * @param retorna o atributo proximo que representa o proximo membro da estrutura.
     */
    public Elemento<T> getProximo(){
        return this.proximo;
    }
}
