package sistema;

import ambiente.*;
import comuns.*;

/**
 *
 * @author tacla
 */
public class Agente implements CoordenadasGeo {

    Model model;
    Problema prob;   // Problema estah na 'mente' do agente

    //@todo colocar aqui plano armazenado de acoes que será executado 
    // passo a passo em deliberar
    //int plan[]={...};
    int plan[], passo = 0;
    float custo = 0, custoTotal = 0;

    public Agente(Model m) {
        this.model = m;

        //@todo - INSTANCIAR PROBLEMA
        prob = new Problema();
        prob.criarLabirinto();

        //@todo - DEFINIR ESTADO INICIAL, OBJETIVO E ATUAL
        // estado inicial
        // estado objetivo
        // estado atual
        prob.defEstIni(0, 0);
        prob.defEstObj(4, 3);
        if (prob.creLab.parede[0][0] != 1) {
            prob.defEstAtu(0, 0);
        } else {
            System.out.println("Na mente do agente, o estado inicial está ocupado por uma parede.");
            prob.defEstAtu(prob.estObj[0], prob.estObj[1]);
        }
        
        plan = new int[5];
        
        plan[0] = 3;
        plan[1] = 3;
        plan[2] = 4;
        plan[3] = 3;
        plan[4] = 2;
        // calcularPlano();
        
        // Calcula o custo total segundo o planejamento armazenado na variável (int) plan[]
        for (int i = 0; i < plan.length; i++) {
            if (plan[passo] == 0 || plan[passo] == 2 || plan[passo] == 4 || plan[passo] == 6) {
                custoTotal += 1;
            } else {
                custoTotal += 1.5;
            }
        }
    }

    /* 
     * Escolhe qual ação será executada em um ciclo de raciocínio
     */
    public int deliberar() {
        int ap[];
        ap = prob.acoesPossiveis(prob.estAtu);

        // nao atingiu objetivo e ha acoesPossiveis a serem executadas no plano
        if (!prob.testeObjetivo()) {
            System.out.println("estado atual: " + prob.estAtu[0] + "," + prob.estAtu[1]);
            System.out.print("açoes possiveis: ");
            for (int i = 0; i < ap.length; i++) {
                if (ap[i] != -1) {
                    System.out.print(acao[i] + " ");
                }
            }
            // @todo executar acao ir
            System.out.println("\nPasso " + passo + " de " + (plan.length - 1) + " ação=" + acao[plan[passo]] + "\n");
            executarIr(plan[passo]);
            passo++;

            // @todo calcular custo do caminho e imprimir ateh este momento
            // -> Custo do caminho calculado na classe de instanciamento
            if (plan[passo] == 0 || plan[passo] == 2 || plan[passo] == 4 || plan[passo] == 6) {
                custo += 1;
            } else {
                custo += 1.5;
            }
            System.out.println("Esforço realizado até o momento: " + custo + " de " + custoTotal);

        } else {
            return (-1);
        }

        return 1;
    }

    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
     */
    public int executarIr(int direcao) {
        model.ir(direcao);
        prob.suc(prob.estAtu, direcao); // atualiza estado do agente
        return 1;
    }
    
    public void calcularPlano() {
        int i = 0;
        int acsPoss[];
        int testerEstado[] = new int[2];
        int melhorEstado[] = new int[2];
        
        melhorEstado = prob.estIni;
        
        while (!prob.testeObjetivo()) {
            acsPoss = prob.acoesPossiveis(prob.estAtu);
            for (int j = 0; j < acsPoss.length; j++) System.out.print(acsPoss[j] + ", "); System.out.println(" '");
            for (int j = 0; j < acsPoss.length; j++) {
                if (acsPoss[j] == 1) {
                    testerEstado = prob.suc(prob.estAtu, j);
                    if (calcularDistancia(testerEstado, prob.estObj) < calcularDistancia(melhorEstado, prob.estObj)) {
                        melhorEstado = testerEstado;
                        plan[i++] = j;
                        System.out.print(plan[i] + "* ");
                    }
                }
            }
        }
        
        for (int j = 0; j < plan.length; j++) System.out.print(plan[j] + ", ");
    }
    
    // Calcula a distancia em linha reta entre dois pontos do labirinto
    public double calcularDistancia(int[] pontoA, int[] pontoB) {
        double distancia, dx, dy;
        
        dx = pontoA[0] - pontoB[0];
        dy = pontoA[1] - pontoB[1];
        
        distancia = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        
        return distancia;
    }

    // Sensor: lê posição atual do agente 
    // @todo
    //public int[] lerSensor() {
    //}
}
