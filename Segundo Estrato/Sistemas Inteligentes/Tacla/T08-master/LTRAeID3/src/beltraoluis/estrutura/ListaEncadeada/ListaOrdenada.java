package beltraoluis.Estrutura.ListaEncadeada;


/**
 * Escreva a descrição da classe FilaOrdenada aqui.
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 28/05/2014
 */
public class ListaOrdenada<T extends Comparable<T>> extends Fila<T>{
    
    public ListaOrdenada(){
        super();
    }
    
    public void inserir(T elemento){
        Elemento<T> anterior = null;
        Elemento<T> proximo = this.inicio;
        Elemento<T> novo;
        if(this.tamanho() == 0){
            novo = new Elemento<T>(elemento,null);
            this.inicio = novo;
            this.tamanho++;
        }else{
            while(proximo != null && proximo.getValor().compareTo(elemento) < 0){
                anterior = proximo;
                proximo = proximo.getProximo();
            }
            novo = new Elemento<T>(elemento,proximo);
            anterior.setProximo(novo);
            this.tamanho++;
        }
    }
}
