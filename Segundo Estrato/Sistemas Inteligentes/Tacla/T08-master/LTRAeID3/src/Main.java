/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beltraoluis.Labirinto;
import beltraoluis.Agente;
import beltraoluis.Fruta;
import java.util.ArrayList;

/**
 *
 * @author tacla
 */
public class Main {

    public static void main(String args[]) {
        /**
         * // Cria o labirinto = ambiente Model mdl = new Model(5,8);
         *
         * // Cria um agente Agente ag = new Agente(mdl);
         *
         * // Ciclo de execucao do sistema mdl.desenhar(); while
         * (ag.deliberar() != -1) { mdl.desenhar(); } *
         */
        ArrayList<Fruta> frutas;
        frutas = new ArrayList<>();
//        frutas.add(new Fruta(Madureza.MADURA,Carboidrato.ALTA,Fibra.ALTA,Proteina.ALTA,Lipideo.ALTA));       
//        frutas.add(new Fruta(Madureza.VERDE,Carboidrato.MODERADA,Fibra.MODERADA,Proteina.MODERADA,Lipideo.MODERADA));
//        frutas.add(new Fruta(Madureza.PODRE,Carboidrato.POUCA,Fibra.POUCA,Proteina.POUCA,Lipideo.POUCA));       
//        frutas.add(new Fruta(Madureza.VERDE,Carboidrato.MODERADA,Fibra.ALTA,Proteina.ALTA,Lipideo.MODERADA));
//        frutas.add(new Fruta(Madureza.PODRE, Carboidrato.POUCA, Fibra.MODERADA, Proteina.POUCA, Lipideo.POUCA));

        // Adiciona todas as frutas possíveis
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        for (int e = 0; e < 3; e++) {
                            frutas.add(new Fruta(a, b, c, d, e));
                        }
                    }
                }
            }
        }

        Labirinto maze = new Labirinto(9, 9);
        Agente ag = new Agente(0, 8, maze);
        maze.setAgente(ag);
        maze.setObjetivo(6, 2);
        maze.euclidiana();
        try {
            maze.porParedeHorizontal(0, 7, 0);
            maze.porParedeHorizontal(0, 1, 1);
            maze.porParedeHorizontal(3, 5, 2);
            maze.porParedeHorizontal(3, 6, 3);
            maze.porParedeHorizontal(5, 7, 5);
            maze.porParedeHorizontal(4, 7, 6);
            maze.porParedeHorizontal(4, 7, 7);
            maze.porParedeHorizontal(1, 2, 8);
            maze.porParedeVertical(2, 3, 0);
            maze.porParedeVertical(6, 7, 1);
            maze.porParedeVertical(5, 5, 2);

        } catch (ArrayIndexOutOfBoundsException e) {
        }
        maze.gerarFrutas(frutas);
        
        //Deseja utilizar qual estratégia de decisão?
        //1 - Aleatória
        //2 - Inteligente
        //3 - Come tudo
        ag.setEstrategiaDecisao(2);
        
        // Qual a energia necessária para caminhar?
        ag.setEnergiaParaCaminhar(45);
        
        // Quantas execuções realizar?
        //ag.executar(50); // param int n = número de execuções
        ag.executar(); // executa apenas uma vez, detalhadamente
        System.out.println(ag.getCaminho());
    }
}
