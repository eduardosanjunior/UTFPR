package beltraoluis.Estrutura.ListaEncadeada;

import beltraoluis.Exception.ListaVaziaException;

/**
 * Escreva a descrição da classe FilaPrioritaria aqui.
 * 
 * @author Luis Henrique Beltrao Santana
 * @version 20170910
 */
public class FilaPrioritaria<T extends Comparable<T>>{
    private Fila<T> normal;
    private Fila<T> prioritario;
    private final int defaultV[] = {3,2};
    private int norm;
    private int prior;
    
    public FilaPrioritaria(){
        this.normal = new Fila<T>();
        this.prioritario = new Fila<T>();
        this.norm = defaultV[0];
        this.prior = defaultV[1];
    }
    
    public void inserir(T elemento){
        this.normal.inserir(elemento);
    }
    
    public void inserirP(T elemento){
        this.prioritario.inserir(elemento);
    }
    
    public T retirar() throws ListaVaziaException{
        if(this.tamanho() == 0) throw new ListaVaziaException("A fila esta vazia!");
        if(this.prior > 0){
            try{
                this.prior--;
                return this.prioritario.retirar();
            }catch(ListaVaziaException e){
                this.prior = 0;
                return this.retirar();
            }
        }else if(this.norm > 0){
            try{
                this.norm--;
                return this.normal.retirar();
            }catch(ListaVaziaException e){
                this.norm = 0;
                return this.retirar();
            }
        }else{
            this.norm = defaultV[0];
            this.prior = defaultV[1];
            return this.retirar();
        }
    }
    
    public int tamanho(){
        return this.normal.tamanho() + this.prioritario.tamanho();
    }
}
