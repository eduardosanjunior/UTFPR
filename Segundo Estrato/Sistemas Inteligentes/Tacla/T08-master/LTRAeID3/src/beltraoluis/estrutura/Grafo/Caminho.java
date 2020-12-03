package beltraoluis.Estrutura.Grafo;


/**
 * Escreva a descrição da classe Caminho aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Caminho<T extends Comparable<T>> implements Comparable<Caminho<T>>{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int dist;
    private Vertice<T> vert;

    public Caminho(int dist,Vertice<T> vert){
        this.dist = dist;
        this.vert = vert;
    }

    public int getDist(){
        return this.dist;
    }
    
    public Vertice<T> getVert(){
        return this.vert;
    }

	@Override
	public int compareTo(Caminho<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
