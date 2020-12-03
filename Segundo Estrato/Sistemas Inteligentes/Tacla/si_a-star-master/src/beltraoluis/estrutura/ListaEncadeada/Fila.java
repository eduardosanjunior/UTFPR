package beltraoluis.Estrutura.ListaEncadeada;

import beltraoluis.Exception.ListaVaziaException;

/**
 * Escreva a descrição da classe Fila aqui.
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */
public class Fila<T extends Comparable<T>> extends ListaEncadeada<T>{
    protected Elemento<T> fim;
    
    public Fila(){
        super();
        this.fim = this.inicio;
    }
    
    public void inserir(T elemento){
        Elemento<T> novo = new Elemento<T>(elemento, null);
        if(this.fim == null){
            this.inicio = novo;
            this.fim = this.inicio;
        }else{
            this.fim.setProximo(novo);
            this.fim = novo;
        }
        this.tamanho++;
    }
    
    public T retirar() throws ListaVaziaException{
        if(this.inicio == null){
            throw new ListaVaziaException("A fila esta vazia!");
        }else{
            Elemento<T> antigo = this.inicio;
            this.inicio = this.inicio.getProximo();
            if(this.inicio == null) this.fim = null;
            this.tamanho--;
            return antigo.getValor();
        }
    }
   
}
