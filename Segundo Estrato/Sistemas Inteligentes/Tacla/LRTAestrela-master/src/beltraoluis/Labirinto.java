/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beltraoluis;

import beltraoluis.matematica.Coordenada2;
import beltraoluis.matematica.MatrizDouble;

/**
 *
 * @author beltraoluis
 */
public class Labirinto extends MatrizDouble {

    private final double PAREDE;
    private Agente agente;
    private Coordenada2 objetivo;
    
    public Labirinto(int linhas, int colunas) {
        super(linhas, colunas);
        PAREDE = Double.MAX_VALUE;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
    
    public void setObjetivo(int x, int y){
        objetivo = new Coordenada2(x, y);
    }
    
    public void euclidiana(){
        this.euclidiana(objetivo.getX(), objetivo.getY());
    }
    
    public void desenhar(int valor){
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        StringBuilder f = new StringBuilder();
        StringBuilder l = new StringBuilder();
        //criando linha de topo em unicode
        t.append((char)9484);
        for(int k = 0;k < colunas-1;k++){
            for(int k1 = 0; k1 < valor;k1++){
                t.append((char)9472);
            }
            t.append((char)9472);
            t.append((char)9472);
            t.append((char)9516);
        }
        t.append((char)9472);
        t.append((char)9472);
        t.append((char)9472);
        t.append((char)9488);
        //criando linha de meio em unicode
         l.append((char)9500);
        for(int k = 0;k < colunas-1;k++){
            for(int k2 = 0; k2 < valor;k2++){
                l.append((char)9472);
            }
            l.append((char)9472);
            l.append((char)9472);
            l.append((char)9532);
        }
        l.append((char)9472);
        l.append((char)9472);
        l.append((char)9472);
        l.append((char)9508);
        //criando linha de fundo em unicode
        f.append((char)9492);
        for(int k = 0;k < colunas-1;k++){
            for(int k3 = 0; k3 < valor;k3++){
                f.append((char)9472);
            }
            f.append((char)9472);
            f.append((char)9472);
            f.append((char)9524);
        }
        f.append((char)9472);
        f.append((char)9472);
        f.append((char)9472);
        f.append((char)9496);
        System.out.println();
        System.out.println(t.toString());
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                s.append((char)9474);
                s.append(" ");
                if(vetor[i][j] == PAREDE){
                    s.append((char)9608);
                    s.append(" ");
                }else if(agente != null && i == agente.getPosY() && j == agente.getPosX()){
                    s.append("A ");
                }else{
                    s.append("  ");
                }
            }
            if(i> 0 && i<linhas){
                System.out.println(l.toString());
            }
            s.append((char)9474);
            System.out.println(s.toString());
            s.delete(0, s.length());
        }
        System.out.println(f.toString());
    }

    public void porParedeHorizontal(int ini, int fim, int linha) {
        setLinha(PAREDE, ini, fim, linha);
    }

    public void porParedeVertical(int ini, int fim, int coluna) {
        setColuna(PAREDE, ini, fim, coluna);
    }

    public Agente getAgente() {
        return agente;
    }
    
    public double getParede(){
        return this.PAREDE;
    }
    
    public Coordenada2 getObjetivo(){
        return this.objetivo;
    }
    
}
