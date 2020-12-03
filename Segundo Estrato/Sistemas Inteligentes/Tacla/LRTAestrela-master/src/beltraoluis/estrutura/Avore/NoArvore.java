/**
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 * 
 * toda a implementação foi realizada no linux através da IDE Eclipse
 * Oxygen
 */

package beltraoluis.Estrutura.Avore;
import java.util.ArrayList;
import java.util.Collections;


// TODO: Auto-generated Javadoc
/**
 * A classe NoArvore representa a estrutura básica para a construção 
 * de uma arvore com tipo generico devendo o mesmo ser encapsulado 
 * por um objeto.
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html>Byte</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Short.html>Short</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html>Integer</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html>Long</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Float.html>Float</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Double.html>Double</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Character.html>Character</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html>Boolean</a>
 *
 * @param <T> parametro de tipo genérico
 */
public class NoArvore<T extends Comparable<T>> implements Comparable<NoArvore<T>>{
    
    /** Dados que serão armazenados. */
    protected T dados; 
    /** O pai do nó. */
    protected NoArvore<T> pai;
    /** Uma lista de filhos do nó. */
    protected ArrayList<NoArvore<T>> filhos;
    /** O nivel da arvore no qual o nó está situado. */
    protected int nivel;
    /** Um marcador para saber se o nó foi visitado. */
    protected boolean visitado;
    /** um marcador para saber se o nó é uma folha. */
    protected boolean folha;
    
    // construtor    
	/**
     * Construtor que instancia um novo NoArvore.
     *
     * @param dados é a informação que sera armazenada no no.
     * @param pai é o pai do nó se for passado o valor null esse nó é 
     * a raiz da arvore.
     */
    public NoArvore(T dados, NoArvore<T> pai) {
		super();
		this.dados = dados;
		this.pai = pai;
		try{
			this.nivel = pai.nivel + 1;
		}catch(NullPointerException e){ // se o pai for null dispara a exception
			this.nivel = 0;
		}
		this.filhos = new ArrayList<NoArvore<T>>();
		this.visitado = false;
		this.folha = false;
	}
    
    // Getters e Setters
	/**
	 * Getter do atributo dados.
	 *
	 * @return os dados armazenados no nó
	 */
	public T getDados() {
		return dados;
	}

	/**
	 * Setter do atributo dados.
	 *
	 * @param dados o dado que será armazenado
	 */
	public void setDados(T dados) {
		this.dados = dados;
	}

	/**
	 * Getter do atributo pai.
	 *
	 * @return o nó pai
	 */
	public NoArvore<T> getPai() {
		return pai;
	}

	/**
	 * Setter do atributo pai.
	 *
	 * @param pai recebe por parametro o pai do nó
	 */
	public void setPai(NoArvore<T> pai) {
		this.pai = pai;
	}

	/**
	 * Getter do atributo filhos.
	 *
	 * @return filhos retorna a lista de filhos
	 */
	public ArrayList<NoArvore<T>> getFilhos() {
		return filhos;
	}

	/**
	 * Esse nó retorna o filho na posição solicitada.
	 *
	 * @param pos a posição do filho
	 * @return retorna o filho na posição desejada
	 * @throws NullPointerException se a posição estiver vazia
	 */
	public NoArvore<T> getFilho(int pos) throws NullPointerException{
		/* 
		 * se o tamanho da lista de filhos for menor ou igual a 
		 * posição lança uma NullPointerException.
		 */
		if(filhos.size()<=pos) throw new NullPointerException("posição invalida");
		return filhos.get(pos);
	}

	/**
	 * Setter da lista filhos.
	 *
	 * @param filhos é a lista de filhos
	 */
	public void setFilhos(ArrayList<NoArvore<T>> filhos) {
		this.filhos = filhos;
		Collections.sort(this.filhos);
	}

	/**
	 * Setter da lista filhos.
	 *
	 * @param filho é a lista de filhos
	 */
	public void setFilho(NoArvore<T> filho) {
		this.filhos.add(filho);
		Collections.sort(this.filhos);
	}

	/**
	 * Getter do atributo nivel.
	 *
	 * @return o nivel onde o nó está situado
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setter do atributo nivel.
	 *
	 * @param nivel o nivel onde o nó está situado na arvore
	 * @throws NullPointerException se o nó não tiver um pai.
	 */
	public void setNivel(int nivel) throws NullPointerException {
		if(this.pai == null) throw new NullPointerException("Nó não possui um pai");
		this.nivel = nivel;
	}

	/**
	 * Verifica se o nó foi visitado.
	 *
	 * @return true, se o nó foi visitado
	 */
	public boolean isVisitado() {
		return visitado;
	}

	/**
	 * Setter do atributo visitado.
	 *
	 * @param visitado estado do nó em relação a ter sido visitado
	 */
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	/**
	 * Verifica se o nó é uma folha.
	 *
	 * @return true, se o nó é uma folha
	 */
	public boolean isFolha() {
		return folha;
	}

	/**
	 * Setter do atributo folha.
	 *
	 * @param folha estado do no em relação a ser uma folha.
	 */
	public void setFolha(boolean folha) {
		this.folha = folha;
	}

	@Override
	public int compareTo(NoArvore<T> o) {
		return this.dados.compareTo(o.getDados());
	}
    
	
  
}