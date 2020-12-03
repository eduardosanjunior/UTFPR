package beltraoluis.Estrutura.Grafo;

import beltraoluis.Estrutura.ListaEncadeada.Lista;
import beltraoluis.Exception.ForaLimitesException;
import beltraoluis.Exception.ListaVaziaException;

public class Vertice<T extends Comparable<T>> implements Comparable<Vertice<T>>{
    private T dado;
    private Lista<Caminho<T>> vert;

    public Vertice(T dado){
        this.dado = dado;
        this.vert = new Lista<Caminho<T>>();
    }
    
    public void setDado(T dado){
        this.dado = dado;
    }
    
    public T getDado(){
        return this.dado;
    }
    
    public void setVia(Caminho<T> vet){
        this.vert.inserir(vet);
    }
    
    public Caminho<T> getVia(int pos) throws ForaLimitesException, ListaVaziaException{
        return vert.ver(pos);
    }

	@Override
	public int compareTo(Vertice<T> o) {
		return this.dado.compareTo(o.getDado());
	}

}
