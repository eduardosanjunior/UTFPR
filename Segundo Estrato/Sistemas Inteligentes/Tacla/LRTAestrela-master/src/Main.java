/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import beltraoluis.Labirinto;
import beltraoluis.matematica.MatrizInt;
import beltraoluis.Agente;
import beltraoluis.matematica.MatrizDouble;

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
        Labirinto mat = new Labirinto(5, 9);
        Agente ag = new Agente(0, 4, mat);
        mat.setAgente(ag);
        mat.setObjetivo(8, 2);
        mat.euclidiana();
        try{
            mat.porParedeHorizontal(7, 8, 0);
            mat.porParedeHorizontal(0, 1, 1);
            mat.porParedeHorizontal(3, 5, 1);
            mat.porParedeHorizontal(3, 6, 2);
            mat.porParedeHorizontal(3, 5, 3);
            mat.porParedeVertical(3, 4, 1);
            
        }catch(ArrayIndexOutOfBoundsException e){
        }
        for(int i = 0; i < 50; i++){
            System.out.print(ag.executarLimpo()+", ");
            ag.set(0, 4);
        }
        //mat.print();
    }
}
