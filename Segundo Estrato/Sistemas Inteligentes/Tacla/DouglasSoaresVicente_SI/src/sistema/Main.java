/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import ambiente.*;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto = ambiente
        Model mdl = new Model(8, 8);

        // Cria um agente
        Agente ag = new Agente(mdl);
        
        // Ciclo de execucao do sistema
        mdl.desenhar();
        while (ag.deliberar() != -1) {
            mdl.desenhar();
        }
    }
}
