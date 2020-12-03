
package sistema;

import ambiente.*;
import comuns.*;
import java.util.*;

/**
 *
 * @author tacla
 */
public class Agente implements CoordenadasGeo {
    Model model;
    Problema prob;   // Problema estah na 'mente' do agente
    public double custo = 0; // Custo ate o momento
    public String plano = ""; // Caminho ate o momento
    public List<Integer> caminho; // Caminho a ser percorrido
    List<Integer> mochila = new ArrayList<>(); // Mochila de frutas
    Map<String,No> mapa; // Map com estado dos nos ja percorridos
    No noAtual; // Posicao atual
    Random rand = new Random();
    public int energia = 3;
    public Agente(Model m) {
        this.model = m;
        mapa = new HashMap<String,No>();
        prob = new Problema();
        prob.criarLabirinto();
        prob.defEstIni(8,0);
        prob.defEstObj(2,6);
        prob.defEstAtu(8,0);
        if(mapa.get("80") == null) {
        	noAtual = new No(lerSensor());
        	mapa.put("80", noAtual);
        }else {
        	noAtual = mapa.get("80"); 
        }
    }

    public int deliberar(int metodo) {
		if(!prob.testeObjetivo(lerSensor())) {
			if(metodo == 0) { // EXECUCAO LRTA*
			   // Guarda as acoes possiveis a serem executadas
	    	   int ap[];
		       ap = prob.acoesPossiveis(prob.estAtu);
		       // Caso nao tenha atingido o objetivo e ha acoesPossiveis a serem executadas no plano
			   // Escolhe uma direcao a seguir usando LRTAEstrela
	           int dir = LRTAEstrela(ap);
	           // Guarda caminho no plano
	           plano += acao[dir] + " ";
	           // Vai para a nova posicao
	           executarIr(dir);
			   // Incrimenta o custo
	           custo += (dir == N || dir == S || dir == L || dir == O) ? 1 : 1.5;
	           
	    	} else if(metodo == 1) { // EXECUCAO ALEATORIA
	    		int dir = caminho.remove(0);
	    		char[] fruta = prob.getFrutinha();
	    		int energiaFruta;
	    		if(Math.abs(rand.nextInt()) % 2 == 1) { // Come a fruta
	    			energiaFruta = energia(fruta);
	    		} else { // Nao come a fruta
	    			energiaFruta = 0;
	    		}
	    		energiaFruta -= (dir == N || dir == S || dir == L || dir == O) ? 1 : 1.5;
	    		energia += energiaFruta;
	    		executarIr(dir);
	    	} else { // EXECUCAO INTELIGENTE
	    		int dir = caminho.remove(0);
	    		char[] fruta = prob.getFrutinha();
	    		int energiaFruta = energia(fruta);
	    		int energiaAdicionar = 0;
	    		switch(energiaFruta) {
	    			case 0:
	    				energiaAdicionar = 0;
	    				break;
	    			case 1:
	    				energiaAdicionar = energiaFruta;
	    				break;
	    			case 2:
	    				mochila.add(energiaFruta);
	    				break;
	    		}
	    		if(energia == 1 && mochila.size() > 0 && energiaAdicionar == 0) {
	    			energiaAdicionar = mochila.remove(0);
	    		}
	    		energiaAdicionar -= (dir == N || dir == S || dir == L || dir == O) ? 1 : 1.5;
	    		energia += energiaAdicionar;
	    		executarIr(dir);
	    	}
		} else{
			return(-1);
		}
		return 1;
    }
    
    private int LRTAEstrela(int ap[]) {
    	No proxNo = null; // Proxima posicao a ser escolhida
    	ArrayList<No> proximos = null; // Lista de possiveis proximas posicoes
		// Para cada acao possivel
    	for(int i = 0; i< ap.length;i++) {
			// Se a acao pode ser executada
    		if(ap[i] != -1) {
				// Se a lista de proximos esta vazia, adiciona o primeiro
    			if(proximos == null) {
    				proximos = new ArrayList<No>();
    				No primeiro = getAdjacente(i);
    				primeiro.dir = i;
    				proximos.add(primeiro);
    				continue;
				}
				// Pega o no adjacente na direcao i
    			No adjacente = getAdjacente(i);
				// Se o custo do no na direcao i eh menor que os que estao na lista de possiveis
				// apaga a lista anterior e coloca o no na direcao i na lista
    			if(adjacente.getCusto() < proximos.get(0).getCusto()) {
    				proximos = new ArrayList<No>();
    				adjacente.dir = i;
    				proximos.add(adjacente);
    			}
				// Se o custo do no na direcao i eh o mesmo dos que estao na lista de possiveis
				// adiciona ele na lista
				else if(adjacente.getCusto() == proximos.get(0).getCusto()){
					adjacente.dir = i;
					proximos.add(adjacente);
				}
    		}
    	}
    	int index;
		// Se a lista de possiveis tem mais de 1 no, escolhe aleatoriamente
		// do contrario pega o primeiro (e unico)
    	if(proximos.size() > 1) {
    		index = (int)(Math.random() * proximos.size());
    	}else {
    		index = 0;
    	}
		// Pega o no
    	proxNo = proximos.get(index);
		// Atualiza o custo do no Atual (o que vc esta, e nao oq vc vai ir)
    	double cus = (proxNo.dir == N || proxNo.dir == S || proxNo.dir == L || proxNo.dir == O) ? 1 : 1.5;
    	noAtual.setCusto(proxNo.getCusto() + cus);
		// Guarda o no atual no mapa de nos
    	String key = Integer.toString(noAtual.pos[0]) + Integer.toString(noAtual.pos[1]);
    	mapa.put(key, noAtual);
		// Passa para o proximo no
    	noAtual = proxNo;
    	return proxNo.dir;
    }
    
    private No getAdjacente(int direcao) {
    	No adjacente;
    	String key = "";
    	int[] pos = lerSensor().clone();
    	switch(direcao) {
    		case N:
    			key = Integer.toString(pos[0] - 1) + Integer.toString(pos[1]);
    			pos[0]--;
    			break;
    		case NE:
    			key = Integer.toString(pos[0] - 1) + Integer.toString(pos[1] + 1);
    			pos[0]--;
    			pos[1]++;
    			break;
    		case L:
    			key = Integer.toString(pos[0]) + Integer.toString(pos[1] + 1);
    			pos[1]++;
    			break;
    		case SE:
    			key = Integer.toString(pos[0] + 1) + Integer.toString(pos[1] + 1);
    			pos[0]++;
    			pos[1]++;
    			break;
    		case S:
    			key = Integer.toString(pos[0] + 1) + Integer.toString(pos[1]);
    			pos[0]++;
    			break;
    		case SO:
    			key = Integer.toString(pos[0] + 1) + Integer.toString(pos[1] - 1);
    			pos[0]++;
    			pos[1]--;
    			break;
    		case O:
    			key = Integer.toString(pos[0]) + Integer.toString(pos[1] - 1);
    			pos[1]--;
    			break;
    		case NO:
    			key = Integer.toString(pos[0] - 1) + Integer.toString(pos[1] - 1);
    			pos[0]--;
    			pos[1]--;
    			break;
    	}
		// Se o no ainda nao esta no mapa, gera uma nova entrada pra ele no mapa
		// do contrario pega o que esta no mapa
    	if(mapa.get(key) == null) {
    		adjacente = new No(pos);
    		mapa.put(key,adjacente);
    	}else {
    		adjacente = mapa.get(key);
    	}
    	return adjacente;
    }
    
    public void restart() {
    	prob.defEstIni(8,0);
        prob.defEstObj(2,6);
        prob.defEstAtu(8,0);
        custo = 0;
        plano = "";
    }
    
    /*
    * Atuador: solicita ao agente 'fisico' executar a acao. Atualiza estado.
    */
    public int executarIr(int direcao) {
        model.ir(direcao);
        prob.suc(prob.estAtu, direcao); // atualiza estado do agente
        return 1; 
    }   
    
    // Sensor: lê posição atual do agente 
    // @todo
    public int[] lerSensor() {
    	return model.lerPos();
    }
    
    private int comeGuardaNaoCome(int energia) {
    	switch(energia) {
    		case 0:
    			return 0; // Nao come
    		case 2:
    			return 1; // Come
    		case 4:
    			return 2; // Guarda
    		default:
    			return 0; // Erro
    	}
    }
    
    private int energia(char cor[]) {
    	/* RESULTADO DO ID3:
    	    cor2 = R
			|   cor3 = R
			|   |   cor4 = R : 4 (18/0) [10/0]
			|   |   cor4 = G : 2 (12/0) [12/0]
			|   |   cor4 = B : 2 (25/0) [13/0]
			|   cor3 = G
			|   |   cor4 = R : 2 (15/0) [18/0]
			|   |   cor4 = G : 2 (27/0) [11/0]
			|   |   cor4 = B : 4 (26/0) [10/0]
			|   cor3 = B
			|   |   cor4 = R : 2 (23/0) [10/0]
			|   |   cor4 = G : 0 (19/0) [13/0]
			|   |   cor4 = B : 2 (25/0) [8/0]
			cor2 = G
			|   cor1 = K
			|   |   cor3 = R
			|   |   |   cor4 = R : 2 (9/0) [5/0]
			|   |   |   cor4 = G : 2 (13/0) [8/0]
			|   |   |   cor4 = B : 0 (12/0) [3/0]
			|   |   cor3 = G
			|   |   |   cor4 = R : 2 (10/0) [7/0]
			|   |   |   cor4 = G : 4 (7/0) [4/0]
			|   |   |   cor4 = B : 2 (12/0) [6/0]
			|   |   cor3 = B
			|   |   |   cor4 = R : 0 (11/0) [7/0]
			|   |   |   cor4 = G : 2 (8/0) [2/0]
			|   |   |   cor4 = B : 2 (16/0) [7/0]
			|   cor1 = W : 0 (103/0) [48/0]
			cor2 = B
			|   cor3 = R
			|   |   cor4 = R : 2 (17/0) [13/0]
			|   |   cor4 = G : 0 (16/0) [13/0]
			|   |   cor4 = B : 2 (27/0) [10/0]
			|   cor3 = G
			|   |   cor4 = R : 0 (23/0) [8/0]
			|   |   cor4 = G : 2 (25/0) [12/0]
			|   |   cor4 = B : 2 (31/0) [13/0]
			|   cor3 = B
			|   |   cor4 = R : 2 (27/0) [13/0]
			|   |   cor4 = G : 2 (19/0) [3/0]
			|   |   cor4 = B : 4 (24/0) [13/0]
    	 */
    	if(cor[1] == 'R') {
    		if(cor[2] == 'R') {
    			if(cor[3] == 'R') {
    				return 4;
    			} else if(cor[3] == 'G') {
    				return 2;
    			} else if(cor[3] == 'B') {
    				return 2;
    			}
    		} else if(cor[2] == 'G') {
    			if(cor[3] == 'R') {
    				return 2;
    			} else if(cor[3] == 'G') {
    				return 2;
    			} else if(cor[3] == 'B') {
    				return 4;
    			}
    		} else if(cor[2] == 'B') {
    			if(cor[3] == 'R') {
    				return 2;
    			} else if(cor[3] == 'G') {
    				return 0;
    			} else if(cor[3] == 'B') {
    				return 2;
    			}
    		}
    	}
    	else if(cor[1] == 'G') {
    		if(cor[0] == 'K') {
    			if(cor[2] == 'R') {
    				if(cor[3] == 'R') {
    					return 2;
    				} else if (cor[3] == 'G') {
    					return 0;
    				} else if (cor[3] == 'B') {
    					return 2;
    				}
    			} else if(cor[2] == 'G') {
    				if(cor[4] == 'R') {
    					return 2;
    				} else if(cor[4] == 'G') {
    					return 4;
    				} else if(cor[4] == 'B') {
    					return 2;
    				}
    			} else if(cor[2] == 'B') {
    				if(cor[3] == 'R') {
    					return 0;
    				} else if(cor[3] == 'G') {
    					return 2;
    				} else if(cor[3] == 'B') {
    					return 2;
    				}
    			}
    		} else {
    			return 0;
    		}
    	} else if(cor[1] == 'B') {
    		if(cor[2] == 'R') {
    			if(cor[3] == 'R') {
    				return 2;
    			} else if(cor[3] == 'G') {
    				return 0;
    			} else if (cor[3] == 'B') {
    				return 2;
    			}
    		} else if(cor[2] == 'G') {
    			if(cor[3] == 'R') {
    				return 0;
    			} else if(cor[3] == 'G') {
    				return 2;
    			} else if(cor[3] == 'B') {
    				return 2;
    			}
    		} else if(cor[2] == 'B') {
    			if(cor[3] == 'R') {
    				return 2;
    			} else if(cor[3] == 'G') {
    				return 2;
    			} else if(cor[3] == 'B') {
    				return 4;
    			}
    		}
    	}
    	return 0;
    }
}
    

