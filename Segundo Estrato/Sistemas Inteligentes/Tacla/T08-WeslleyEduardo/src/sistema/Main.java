/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.Scanner;

import ambiente.*;
import java.util.*;

/**
 *
 * @author tacla
 */
public class Main {
    public static void main(String args[]) {
        // Cria o labirinto = ambiente
        Model mdl = new Model(9, 9);
        int execucoes = 1;
		ArrayList<String> planos = new ArrayList<String>();
        // Cria um agente
        Agente ag = new Agente(mdl);
        // Ciclo de execucao do sistema
        //mdl.desenhar();
        // EXECUCAO DO LRTA*
        while(execucoes < 10000) {
        	 while (ag.deliberar(0) != -1) {
                 //mdl.desenhar();
             }
			 // Se achou um melhor caminho
        	 if(ag.custo <= 12) {
				boolean novoPlano = true;
				// Verifica se ja tinha encontrado este caminho
				for(int i = 0; i < planos.size(); i++){
					if(ag.plano.equals(planos.get(i))){
						novoPlano = false;
					}
				}
				// Caso nao tenha encontrado, adiciona aos caminhos encontrados
				if(novoPlano){
					planos.add(ag.plano);
				}
				// Reinicia
				execucoes++;
				ag.restart();
				mdl.restart();
        	 }else {
				// Reinicia
        		execucoes++;
        		ag.restart();
				mdl.restart();
        	}
        }
        // EXECUCAO COM FRUTAS
        Random rand = new Random();
        int pos = Math.abs(rand.nextInt()) % planos.size();
        System.out.println("Caminho a seguir:");
        System.out.println(planos.get(pos));
        String[] caminho = planos.get(pos).split(" ");
        ArrayList<Integer> caminhoInt = new ArrayList<>();
        for(int i = 0; i < caminho.length; i++) {
        	caminhoInt.add(charToInt(caminho[i]));
        }
        ag.caminho = caminhoInt;
        ag.restart();
		mdl.restart();
		execucoes = 0;
		while(execucoes < 100) {
			while(ag.deliberar(2) != -1) {
				//mdl.desenhar();
				if(ag.energia <= 0 ) {
					ag.energia = 100;
					break;
				}
			}
			System.out.println("Fim de jogo!");
			System.out.println("Execucao: " + (execucoes+1));
			System.out.println("Pontuacao: " + ag.energia*-1);
			// Reseta
			pos = Math.abs(rand.nextInt()) % planos.size();
	        caminho = planos.get(pos).split(" ");
	        caminhoInt = new ArrayList<>();
	        for(int i = 0; i < caminho.length; i++) {
	        	caminhoInt.add(charToInt(caminho[i]));
	        }
	        ag.caminho = caminhoInt;
	        ag.restart();
			mdl.restart();
			execucoes++;
		}
    }
    
    private static int charToInt(String direcao) {
    	switch(direcao) {
    		case "N":
    			return 0;
    		case "NE":
    			return 1;
    		case "L":
    			return 2;
    		case "SE":
    			return 3;
    		case "S":
    			return 4;
    		case "SO":
    			return 5;
    		case "O":
    			return 6;
    		case "NO":
    			return 7;
    		default:
    			return 8;
    	}
    }
}
