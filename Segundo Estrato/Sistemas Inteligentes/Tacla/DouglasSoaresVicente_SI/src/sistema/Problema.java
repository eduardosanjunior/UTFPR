/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package sistema;

import comuns.*;
import java.math.MathContext;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problema implements CoordenadasGeo {

    protected int estIni[] = new int[2];  // estado inicial [linha, coluna] = pos do agente
    protected int estObj[] = new int[2];  // estado objetivo [linha, coluna]
    protected int estAtu[] = new int[2];  // estado atual [linha, coluna]
    protected Labirinto creLab;           // crença do agente sobre como eh o labirinto
    protected int maxLin, maxCol;

    /*
     * O que o agente crê sobre o labirinto
     */
    public void criarLabirinto() {
        maxLin = 8;
        maxCol = 8;
        creLab = new Labirinto(maxLin, maxCol);

        //@todo colocar paredes -> Colocar paredes aleatorias
        int lin, col;
        for (int i = 0; i < 20; i++) {
            lin = (int) (Math.random() * 8 % 8);
            col = (int) (Math.random() * 8 % 8);
            if (creLab.parede[lin][col] != 1) {
                creLab.porParedeHorizontal(col, col+1, lin);
            }
        }

        // Imprime a crença do agente
        for (int i = 0; i < maxLin; i++) {
            for (int j = 0; j < maxCol; j++) {
                System.out.print(creLab.parede[i][j] + " ");
            }
            System.out.println("'");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Define estado inicial
     */
    protected void defEstIni(int linha, int coluna) {
        estIni[0] = linha;
        estIni[1] = coluna;
    }

    /*
     * Define estado objetivo
     */
    protected void defEstObj(int linha, int coluna) {
        estObj[0] = linha;
        estObj[1] = coluna;
    }

    /*
     * Define estado atual
     */
    protected void defEstAtu(int linha, int coluna) {
        estAtu[0] = linha;
        estAtu[1] = coluna;
    }

    /*
     * Funcao sucessora: recebe um estado '(lin, col)' e calcula o estado atual
     * que resulta da execucao da acao = {N, NE, L, SE, S, SO, O, NO}
     */
    protected int[] suc(int[] estado, int acao) {
        // @todo
        // calcular estado sucessor a partir do estado e acao
        int[] proxEstado = new int[2];
        int lin = estado[0];
        int col = estado[1];
        
        switch (acao) {
            case N:
                lin--;
                break;
            case NE:
                col++;
                lin--;
                break;
            case L:
                col++;
                break;
            case SE:
                col++;
                lin++;
                break;
            case S:
                lin++;
                break;
            case SO:
                col--;
                lin++;
                break;
            case O:
                col--;
                break;
            case NO:
                col--;
                lin--;
                break;
        }
        // verifica se está fora do grid
        if (col < 0 || col >= maxCol || lin < 0 || lin >= maxLin) {
            lin = estado[0];
            col = estado[1];  // fica na posicao atual
            
        }
        // verifica se bateu em algum obstaculo
        if (creLab.parede[lin][col] == 1) {
            lin = estado[0];
            col = estado[1];  // fica na posicao atual
        }

        // atribui nova posicao ao estado atual
        defEstAtu(lin, col);
        return estAtu;
    }

    /*
     * Retorna as acoesPossiveis possiveis de serem executadas em um estado 
     * O valor retornado é um vetor de inteiros. Se o valor da posicao é -1
     * então a ação correspondente não pode ser executada, caso contrario valera 1.
     * Por exemplo, 
     * [-1, -1, -1, 1, 1, -1, -1, -1] indica apenas que S e SO podem ser executadas.
     */
    protected int[] acoesPossiveis(int[] estado) {
        int acoes[] = new int[8];

        // Calcula acoes possiveis
        if (estado[0]-1 > 0                              && creLab.parede[estado[0]-1]  [estado[1]]     == 0) acoes[0] = 1; else acoes[0] = -1;
        if (estado[0]-1 > 0 &&      estado[1]+1 < maxCol && creLab.parede[estado[0]-1]  [estado[1]+1]   == 0) acoes[1] = 1; else acoes[1] = -1;
        if (                        estado[1]+1 < maxCol && creLab.parede[estado[0]]    [estado[1]+1]   == 0) acoes[2] = 1; else acoes[2] = -1;
        if (estado[0]+1 < maxLin && estado[1]+1 < maxCol && creLab.parede[estado[0]+1]  [estado[1]+1]   == 0) acoes[3] = 1; else acoes[3] = -1;
        if (estado[0]+1 < maxLin                         && creLab.parede[estado[0]+1]  [estado[1]]     == 0) acoes[4] = 1; else acoes[4] = -1;
        if (estado[0]+1 < maxLin && estado[1]-1 > 0      && creLab.parede[estado[0]+1]  [estado[1]-1]   == 0) acoes[5] = 1; else acoes[5] = -1;
        if (                        estado[1]-1 > 0      && creLab.parede[estado[0]]    [estado[1]-1]   == 0) acoes[6] = 1; else acoes[6] = -1;
        if (estado[0]-1 > 0      && estado[1]-1 > 0      && creLab.parede[estado[0]-1]  [estado[1]-1]   == 0) acoes[7] = 1; else acoes[7] = -1;
        return acoes;
    }

    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testeObjetivo() {
        //fazer o teste
        if (estAtu[0] == estObj[0] && estAtu[1] == estObj[1]) return true;
        else return false;
    }
}
