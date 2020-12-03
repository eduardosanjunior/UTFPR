/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package sistema;

import comuns.*;

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
        
        //@todo colocar paredes
        
        creLab.porParedeVertical(0, 1, 1);
        creLab.porParedeVertical(1, 2, 3);
        creLab.porParedeHorizontal(2, 4, 2);
        creLab.porParedeHorizontal(1, 2, 3);
        creLab.porParedeHorizontal(0, 2, 4);
        creLab.porParedeVertical(4, 6, 4);
        creLab.porParedeVertical(4, 5, 5);
        creLab.porParedeVertical(3, 6, 7);
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

        // atribui nova posicao
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
        
        //@todo
        int SuN[] = suc(estAtu, N);
        int SuNO[] = suc(estAtu, NO);
        int SuO[] = suc(estAtu, O);
        int SuSO[] = suc(estAtu, SO);
        int SuS[] = suc(estAtu, S);
        int SuSE[] = suc(estAtu, SE);
        int SuL[] = suc(estAtu, L);
        int SuNE[] = suc(estAtu, NE);
        if(SuN[0] == estAtu[0] && SuN[1] == estAtu[1]){acoes[0] = -1;}
        else{acoes[0] = 1;}
        if(SuNO[0] == estAtu[0] && SuNO[1] == estAtu[1]){acoes[1] = -1;}
        else{acoes[1] = 1;}
        if(SuO[0] == estAtu[0] && SuO[1] == estAtu[1]){acoes[2] = -1;}
        else{acoes[2] = 1;}
        if(SuSO[0] == estAtu[0] && SuSO[1] == estAtu[1]){acoes[3] = -1;}
        else{acoes[3] = 1;}
        if(SuS[0] == estAtu[0] && SuS[1] == estAtu[1]){acoes[4] = -1;}
        else{acoes[4] = 1;}
        if(SuSE[0] == estAtu[0] && SuSE[1] == estAtu[1]){acoes[5] = -1;}
        else{acoes[5] = 1;}
        if(SuL[0] == estAtu[0] && SuL[1] == estAtu[1]){acoes[6] = -1;}
        else{acoes[6] = 1;}
        if(SuNE[0] == estAtu[0] && SuNE[1] == estAtu[1]){acoes[7] = -1;}
        else{acoes[7] = 1;}
   
        return acoes;
    }

    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testeObjetivo() {
        //@todo fazer o teste
        if(estAtu[0] == estObj[0] && estAtu[1] == estObj[1]){
            return true;
        }
        return false;
    }
}
