/**
 * @author Luis Henrique Beltrao Santana
 * @version 20170911
 * 
 * toda a implementação foi realizada no linux através da IDE Eclipse
 * Oxygen
 */

package beltraoluis.Estrutura.Avore;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Arvore.
 *
 * @param <T> the generic type
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html>Byte</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Short.html>Short</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html>Integer</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html>Long</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Float.html>Float</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Double.html>Double</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Character.html>Character</a>
 * @see <a href=http://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html>Boolean</a>
 */
public class Arvore<T extends Comparable<T>>{
	
	/** The raiz. */
	protected NoArvore<T> raiz;
	
	/** The nos. */
	protected ArrayList<NoArvore<T>> nos;
	
	/** The folhas. */
	protected ArrayList<NoArvore<T>> folhas;
	
	/** The ponteiro. */
	protected NoArvore<T> ponteiro;
	
	/**
	 * Instantiates a new arvore.
	 *
	 * @param raiz the raiz
	 */
	//construtor
	public Arvore(NoArvore<T> raiz) {
		this.raiz = raiz;
		this.nos = new ArrayList<NoArvore<T>>();
		this.folhas = new ArrayList<NoArvore<T>>();
		this.ponteiro = this.raiz;
	}
	
	/**
	 * Instantiates a new arvore.
	 *
	 * @param dado the dado
	 */
	public Arvore(T dado) {
		this.raiz = new NoArvore<T>(dado, null);
		this.nos = new ArrayList<NoArvore<T>>();
		this.folhas = new ArrayList<NoArvore<T>>();
		this.ponteiro = this.raiz;
	}
	
	/**
	 * Instantiates a new arvore.
	 */
	public Arvore() {
		this.raiz = new NoArvore<T>(null, null);
		this.nos = new ArrayList<NoArvore<T>>();
		this.folhas = new ArrayList<NoArvore<T>>();
		this.ponteiro = this.raiz;
	}
	

	/**
	 * Gets the raiz.
	 *
	 * @return the raiz
	 */
	public NoArvore<T> getRaiz() {
		return raiz;
	}
	

	/**
	 * Sets the raiz.
	 *
	 * @param raiz the new raiz
	 */
	public void setRaiz(NoArvore<T> raiz) {
		this.raiz = raiz;
	}
	

	/**
	 * Gets the nos.
	 *
	 * @return the nos
	 */
	public ArrayList<NoArvore<T>> getNos() {
		return nos;
	}
	

	/**
	 * Sets the nos.
	 *
	 * @param nos the new nos
	 */
	public void setNos(ArrayList<NoArvore<T>> nos) {
		this.nos = nos;
	}
	

	/**
	 * Gets the folhas.
	 *
	 * @return the folhas
	 */
	public ArrayList<NoArvore<T>> getFolhas() {
		return folhas;
	}
	

	/**
	 * Sets the folhas.
	 *
	 * @param folhas the new folhas
	 */
	public void setFolhas(ArrayList<NoArvore<T>> folhas) {
		this.folhas = folhas;
	}
	

	/**
	 * Gets the ponteiro.
	 *
	 * @return the ponteiro
	 */
	public NoArvore<T> getPonteiro() {
		return ponteiro;
	}
	

	/**
	 * Sets the ponteiro.
	 *
	 * @param ponteiro the new ponteiro
	 */
	public void setPonteiro(NoArvore<T> ponteiro) {
		this.ponteiro = ponteiro;
	}
	
	
	/**
	 * Navegar pai.
	 *
	 * @return true, if successful
	 */
	public boolean navegarPai() {
		if(this.ponteiro.getPai() != null) {
			this.ponteiro = this.ponteiro.getPai();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Navegar filho min.
	 *
	 * @return true, if successful
	 */
	public boolean navegarFilhoMin() {
		if(this.ponteiro.getFilhos().size() > 1) {
			this.ponteiro = this.ponteiro.getFilho(0);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Adds the.
	 *
	 * @param dado the dado
	 */
	public void add(NoArvore<T> dado) {
		this.nos.add(dado);
		if(dado.getFilhos().size() == 0) {
			dado.setFolha(true);
			this.folhas.add(dado);
		}
	}
	
}
