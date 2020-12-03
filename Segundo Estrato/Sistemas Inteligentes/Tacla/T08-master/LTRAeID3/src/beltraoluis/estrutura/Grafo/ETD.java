package beltraoluis.Estrutura.Grafo;

public class ETD<T extends Comparable<T>>
{
    private Vertice<T> vert;
    private boolean conhecido;
    private int dist;
    private Vertice<T> ant;
    
    public ETD(Vertice<T> v, boolean k, int d, Vertice<T> p){
        this.vert = v;
        this.conhecido = k;
        this.dist = d;
        this.ant = p;
    }
    
    public void set(boolean k, int d, Vertice<T> p){
        this.conhecido = k;
        this.dist = d;
        this.ant = p;
    }
    
    public Vertice<T> getVert(){
        return this.vert;
    }
    
    public boolean getConhecido(){
        return this.conhecido;
    }
    
    public int getDist(){
        return this.dist;
    }
    
    public Vertice<T> getAnt(){
        return this.ant;
    }
}
