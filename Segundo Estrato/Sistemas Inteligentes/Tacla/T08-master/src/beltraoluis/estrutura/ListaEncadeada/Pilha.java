package beltraoluis.Estrutura.ListaEncadeada;

import beltraoluis.Exception.ListaVaziaException;

/**
 * Escreva a descrição da classe Pilha aqui.
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */

public class Pilha<T extends Comparable<T>> extends ListaEncadeada<T>{
    
    public Pilha(){
        super();
    }
    
    public void inserir(T elemento){
        this.inicio = new Elemento<T>(elemento, this.inicio);
        this.tamanho++;
    }
    
    public T retirar() throws ListaVaziaException{
        if (this.inicio == null){
            throw new ListaVaziaException("A pilha esta vazia!");
        }else{
            Elemento<T> antigo;
            antigo = this.inicio;
            this.inicio = this.inicio.getProximo();
            this.tamanho--;
            return antigo.getValor();
        }
    }
}
