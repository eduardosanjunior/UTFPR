package beltraoluis.Estrutura.Grafo;

import beltraoluis.Estrutura.ListaEncadeada.Lista;

public class test
{
    public static Lista<Vertice<String>> geraGrafo(){
        // Vertices
        Vertice<String> A =  new Vertice<String>("A");
        Vertice<String> B =  new Vertice<String>("B");
        Vertice<String> C =  new Vertice<String>("C");
        Vertice<String> D =  new Vertice<String>("D");
        
        // caminhos
        Caminho<String> AB = new Caminho<String>(1,B);
        Caminho<String> AC = new Caminho<String>(2,C);
        Caminho<String> BA = AB;
        Caminho<String> BC = new Caminho<String>(1,C);
        Caminho<String> BD = new Caminho<String>(7,D);
        Caminho<String> CA = AC;
        Caminho<String> CB = BC;
        Caminho<String> CD = new Caminho<String>(3,D);
        Caminho<String> DB = BD;
        Caminho<String> DC = CD;
        
        //montagem
        A.setVia(AB);
        A.setVia(AC);
        B.setVia(BA);
        B.setVia(BC);
        B.setVia(BD);
        C.setVia(CA);
        C.setVia(CB);
        C.setVia(CD);
        D.setVia(DB);
        D.setVia(DC);
        Lista<Vertice<String>> grafo = new Lista<Vertice<String>>();
        grafo.inserir(A);
        grafo.inserir(B);
        grafo.inserir(C);
        grafo.inserir(D);
        return grafo;
    }
}
