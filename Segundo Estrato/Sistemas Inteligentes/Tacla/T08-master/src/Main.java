/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import beltraoluis.Labirinto;
import beltraoluis.matematica.MatrizInt;
import beltraoluis.Agente;
import beltraoluis.Carboidrato;
import beltraoluis.Fibra;
import beltraoluis.Fruta;
import beltraoluis.Lipideo;
import beltraoluis.Madureza;
import beltraoluis.Proteina;
import beltraoluis.matematica.MatrizDouble;
import java.util.ArrayList;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
    /**    // Cria o labirinto = ambiente
        Model mdl = new Model(5,8);

        // Cria um agente
        Agente ag = new Agente(mdl);
        
        // Ciclo de execucao do sistema
        mdl.desenhar();
        while (ag.deliberar() != -1) {
            mdl.desenhar();
        } **/
        ArrayList<Fruta> frutas;
        frutas = new ArrayList<>();
        frutas.add(new Fruta(Madureza.MADURA,Carboidrato.ALTA,Fibra.ALTA,Proteina.ALTA,Lipideo.ALTA));       
        frutas.add(new Fruta(Madureza.VERDE,Carboidrato.MODERADA,Fibra.MODERADA,Proteina.MODERADA,Lipideo.MODERADA));
        frutas.add(new Fruta(Madureza.PODRE,Carboidrato.POUCA,Fibra.POUCA,Proteina.POUCA,Lipideo.POUCA));       
        frutas.add(new Fruta(Madureza.VERDE,Carboidrato.MODERADA,Fibra.ALTA,Proteina.ALTA,Lipideo.MODERADA));
        frutas.add(new Fruta(Madureza.PODRE,Carboidrato.POUCA,Fibra.MODERADA,Proteina.POUCA,Lipideo.POUCA));
        Labirinto mat = new Labirinto(9, 9);
        Agente ag = new Agente(0, 8, mat);
        mat.setAgente(ag);
        mat.setObjetivo(6, 2);
        mat.euclidiana();
        try{
            mat.porParedeHorizontal(0, 7, 0);
            mat.porParedeHorizontal(0, 1, 1);
            mat.porParedeHorizontal(3, 5, 2);
            mat.porParedeHorizontal(3, 6, 3);
            mat.porParedeHorizontal(5, 7, 5);           
            mat.porParedeHorizontal(4, 7, 6);           
            mat.porParedeHorizontal(4, 7, 7);
            mat.porParedeHorizontal(1, 2, 8);
            mat.porParedeVertical(2, 3, 0);
            mat.porParedeVertical(6, 7, 1);
            mat.porParedeVertical(5, 5, 2);
            
        }catch(ArrayIndexOutOfBoundsException e){
        }
        //executar com 50 repetições
        ag.executar(50);
        System.out.println(ag.getCaminho());
        mat.gerarFrutas(frutas);
    }
}
