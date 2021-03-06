
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
    int plan[]={};
    int ct;
    float custo;
    int estadoInicial[];
    int estadoObjetivo[];
    int estadoAtual[];
    
         
    public Agente(Model m) {
        this.model = m;  
        
        //@todo - INSTANCIAR PROBLEMA
        prob = new Problema();
        prob.criarLabirinto();
        ct = 0;
        custo = 0f;
       
        //@todo - DEFINIR ESTADO INICIAL, OBJETIVO E ATUAL
        estadoInicial = new int[] {0,8};
        estadoObjetivo = new int[] {8,2};
        estadoAtual = new int[] {0,8};
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
           for (int i=0;i<ap.length;i++) {
               if (ap[i]!=-1)
                   System.out.print(acao[i]+" ");
           }
           // @todo executar acao ir
           System.out.println("\nct = "+ ct + " de " + (plan.length-1) + " ação=" + acao[plan[ct]] + "\n");
           executarIr(plan[ct]);
           
           // @todo calcular custo do caminho e imprimir ateh este momento
           if(plan[ct]==N || plan[ct]==S || plan[ct]==L || plan[ct]==O){
               custo++;
           }else{
               custo += 1.5;
           }
           System.out.println("custo: " + custo);
           ct++;
        }
        else
            return (-1);
        
        return 1;
    }
    
    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
    */
    public int executarIr(int direcao) {
        model.ir(direcao);
        estadoAtual = lerSensor();
        prob.suc(prob.estAtu, direcao); // atualiza estado do agente
        return 1; 
    }   
    
    // Sensor: lê posição atual do agente 
    // @todo
    public int[] lerSensor() {
        return model.lerPos();
    }
    
}
    

