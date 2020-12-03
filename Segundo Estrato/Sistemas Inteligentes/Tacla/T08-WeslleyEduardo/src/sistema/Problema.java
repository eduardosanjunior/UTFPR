/* 
 * @author Tacla
 * Classe para representar um problema no labirinto; sao representacoes que
 * ficam na 'mente' do agente
 */
package sistema;

import java.util.Random;

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
        maxLin = 9;
        maxCol = 9;
        creLab = new Labirinto(maxCol, maxLin);
        
        //@todo colocar paredes
        creLab.porParedeVertical(0, 1, 0);
        creLab.porParedeVertical(0, 0, 1);
        creLab.porParedeVertical(6, 8, 1);
        creLab.porParedeVertical(5, 5, 2);
        creLab.porParedeVertical(8, 8, 2);
        creLab.porParedeHorizontal(4, 7, 0);
        creLab.porParedeHorizontal(3, 5, 2);
        creLab.porParedeHorizontal(3, 6, 3);
        creLab.porParedeVertical(6, 7, 4);
        creLab.porParedeVertical(5, 6, 5);
        creLab.porParedeVertical(5, 7, 7);
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
    	int[] acoes = this.acoesPossiveis(estado);
    	switch(acao) {
	    	case N:
	    		if(acoes[N] == 1) {this.defEstAtu(estAtu[0] - 1, estAtu[1]);}
	    		break;
	    	case L:
	    		if(acoes[L] == 1) {this.defEstAtu(estAtu[0], estAtu[1] + 1);}
	    		break;
	    	case S:
	    		if(acoes[S] == 1) {this.defEstAtu(estAtu[0] + 1, estAtu[1]);}
	    		break;
	    	case O:
	    		if(acoes[O] == 1) {this.defEstAtu(estAtu[0], estAtu[1] - 1);}
	    		break;
	    	case NE:
	    		if(acoes[NE] == 1) {this.defEstAtu(estAtu[0] - 1, estAtu[1] + 1);}
	    		break;
	    	case NO:
	    		if(acoes[NO] == 1) {this.defEstAtu(estAtu[0] - 1, estAtu[1] - 1);}
	    		break;
	    	case SE:
	    		if(acoes[SE] == 1) {this.defEstAtu(estAtu[0] + 1, estAtu[1] + 1);}
	    		break;
	    	case SO:
	    		if(acoes[SO] == 1) {this.defEstAtu(estAtu[0] + 1, estAtu[1] - 1);}
	    		break;
    	}
        // atribui nova posicao ao estado atual
        // defEstAtu(lin, col);
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
        int linha = estado[0];
        int coluna = estado[1];
        for(int i = 0; i<8; i++) {acoes[i] = 1;}
        if(estado[0] == 0 ) {
        	acoes[N] = -1;
        	acoes[NE] = -1;
        	acoes[NO] = -1;
        }
        else {
        	if(creLab.parede[linha - 1][coluna] == 1) {acoes[N] = -1;}
        	if(estado[1] < maxCol-1) {
        		if(creLab.parede[linha - 1][coluna + 1] == 1) {acoes[NE] = -1;}
        	}
        	if(estado[1] > 0) {
            	if(creLab.parede[linha - 1][coluna - 1] == 1) {acoes[NO] = -1;}
        	}
        }
        if(estado[0] == maxLin-1) {
        	acoes[S] = -1;
        	acoes[SE] = -1;
        	acoes[SO] = -1;
        }
        else {
        	if(creLab.parede[linha + 1][coluna] == 1) {acoes[S] = -1;}
        	if(estado[1] < maxCol-1) {
        		if(creLab.parede[linha + 1][coluna + 1] == 1) {acoes[SE] = -1;}
        	}
        	if(estado[1] > 0) {
            	if(creLab.parede[linha + 1][coluna - 1] == 1) {acoes[SO] = -1;}
        	}
        }
        if(estado[1] == 0) {
        	acoes[NO] = -1;
        	acoes[O] = -1;
        	acoes[SO] = -1;
        }
        else {
        	if(creLab.parede[linha][coluna - 1] == 1) {acoes[O] = -1;}
        	if(estado[0] > 0) {
        		if(creLab.parede[linha - 1][coluna - 1] == 1) {acoes[NO] = -1;}
        	}
        	if(estado[0] < maxLin-1) {
            	if(creLab.parede[linha + 1][coluna - 1] == 1) {acoes[SO] = -1;}
        	}
        }
        if(estado[1] == maxCol-1) {
        	acoes[NE] = -1;
        	acoes[L] = -1;
        	acoes[SE] = -1;
        }
        else {
        	if(creLab.parede[linha][coluna + 1] == 1) {acoes[L] = -1;}
        	if(estado[0] > 0) {
        		if(creLab.parede[linha - 1][coluna + 1] == 1) {acoes[NE] = -1;}
        	}
        	if(estado[0] < maxLin-1) {
            	if(creLab.parede[linha + 1][coluna + 1] == 1) {acoes[SE] = -1;}
        	}
        }
        
        return acoes;
    }

    /*
     * Retorna true quando estado atual = estado objetivo, caso contrario retorna falso
     */
    protected boolean testeObjetivo(int[] pos) {
        //@todo fazer o teste 
    	if(pos[0] == estObj[0] && pos[1] == estObj[1]) {
    		return true;
    	}
        return false;
    }
    
    public char[] getFrutinha() {
    	char rgb[] = {'R', 'G', 'B'};
    	char kw[] = {'K', 'W'};
    	char cores[] = new char[5];
    	int pos;
    	Random rand = new Random();
    	pos = Math.abs(rand.nextInt()) % 2;
    	cores[0] = kw[pos];
    	pos = Math.abs(rand.nextInt()) % 3;
    	cores[1] = rgb[pos];
    	pos = Math.abs(rand.nextInt()) % 3;
    	cores[2] = rgb[pos];
    	pos = Math.abs(rand.nextInt()) % 3;
    	cores[3] = rgb[pos];
    	pos = Math.abs(rand.nextInt()) % 2;
    	cores[4] = kw[pos];
    	return cores;
    }
}
