package beltraoluis.Estrutura.ListaEncadeada;

import beltraoluis.Exception.ForaLimitesException;
import beltraoluis.Exception.CheioException;
import beltraoluis.Exception.ListaVaziaException;
import beltraoluis.Exception.PosicaoVaziaException;

import java.lang.NullPointerException;

/**
 * Classe Lista - escreva a descrição da classe aqui
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */
public class Lista<T extends Comparable<T>> extends Fila<T>{
    
    protected int tamMax;
    
    public Lista(){
        super();
        this.tamMax = 0;
    }
    
    public Lista(int tam) throws NullPointerException{
        super();
        if(tam < 0) throw new NullPointerException("Valor de tamanho invalido!");
        this.tamMax = tam;
    }
    
    private Elemento<T> getPos(int pos) throws ForaLimitesException, ListaVaziaException{
        if(pos >= this.tamanho){
            if(this.tamanho == 0) throw new ListaVaziaException("A lista esta vazia!");
            throw new ForaLimitesException("A posicao esta fora dos limites da lista!");
        }
        int cont;
        Elemento<T> elemento = this.inicio;
        for(cont = 0; cont < pos; cont++){
            elemento = elemento.getProximo();
        }
        return elemento;
    }
    
    public void inserir(T valor, int pos) throws ForaLimitesException, ListaVaziaException, CheioException{
        if(this.tamMax > 0 && this.tamMax == this.tamanho()) throw new CheioException("A lista está cheia");
        if(pos == this.tamanho){
            this.inserir(valor);
        }else{
            Elemento<T> elemento = getPos(pos);
            elemento.setValor(valor);
        }
    }
    
    public T retirar(int pos) throws ForaLimitesException, ListaVaziaException, PosicaoVaziaException{
        T valor;
        Elemento<T> elemento = getPos(pos);
        valor = elemento.getValor();
        elemento.setValor(null);
        if(valor == null) throw new PosicaoVaziaException("Esta posicao esta vazia");
        return valor;
    }

}
