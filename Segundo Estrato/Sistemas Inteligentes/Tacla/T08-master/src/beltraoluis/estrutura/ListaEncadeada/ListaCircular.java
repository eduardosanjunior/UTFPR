package beltraoluis.Estrutura.ListaEncadeada;

import java.lang.Math;

import beltraoluis.Exception.ListaVaziaException;


/**
 * Write a description of class ListaCircular here.
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */
public class ListaCircular<T extends Comparable<T>> extends Fila<T>{
    public ListaCircular(){
        super();
    }
    
    public void inserir(T elemento){
        Elemento<T> novo;
        if(this.inicio == null){
            novo = new Elemento<T>(elemento,null);
            novo.setProximo(novo);
            this.inicio = novo;
            this.fim = novo;
        }else{
            novo = new Elemento<T>(elemento,this.inicio);
            this.fim.setProximo(novo);
            this.fim = novo;
        }
        this.tamanho++;
    }
    
    public T retirar() throws ListaVaziaException{
        if(this.inicio == null) throw new ListaVaziaException("A lista esta vazia!");
        Elemento<T> antigo;
        antigo = this.inicio;
        this.inicio = this.inicio.getProximo();
        this.fim.setProximo(this.inicio);
        this.tamanho--;
        if(this.tamanho == 0){
            this.inicio = null;
            this.fim = null;
        }
        return antigo.getValor();
    }
    
    public T retirar(int pos) throws ListaVaziaException{
        if(this.inicio == null) throw new ListaVaziaException("A lista esta vazia!");
        Elemento<T> navegador = this.inicio;
        Elemento<T> antigo;
        int cont;
        pos = Math.abs(pos);
        if(pos == 0) return this.retirar();
        for(cont = 0; cont < pos-1; cont++){
            navegador = navegador.getProximo();
        }
        if(navegador.getProximo().equals(this.fim)) this.fim = navegador;
        antigo = navegador.getProximo();
        navegador.setProximo(antigo.getProximo());
        this.tamanho--;
        if(this.tamanho == 0){
            this.inicio = null;
            this.fim = null;
        }
        return antigo.getValor();
    }
    
    private void rotE(int pos){
        for(int i = 0; i < Math.abs(pos); i++){
            this.inicio = this.inicio.getProximo();
            this.fim = this.fim.getProximo();
        }
    }
    
    private void rotD(int pos){
        pos = Math.abs(pos);
        for(int i = 0; i < Math.abs(this.tamanho-pos); i++){
            this.inicio = this.inicio.getProximo();
            this.fim = this.fim.getProximo();
        }
    }    
    
    public void rotacionar(int pos){
        if(pos>0){
            this.rotE(pos);
        }else{
            this.rotD(pos);
        }
    }
    
    public static ListaCircular<Integer> test(){
        ListaCircular<Integer> lc = new ListaCircular<Integer>();
        for(int n = 0; n<6; n++){
            lc.inserir(n);
        }
        return lc;
    }
}
