package beltraoluis;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author beltraoluis
 */
public class Agente {

    protected int posX;
    protected int posY;
    protected int estrategiaDecisao;
    protected int[] origem;
    protected Labirinto modelo;
    protected StringBuffer caminho;
    protected boolean enableEnergia;
    protected int energia;
    protected int energiaParaCaminhar;
    protected ArrayList<Fruta> cesta;
    //protected PrintWriter writer;

    public Agente(int posX, int posY, Labirinto modelo) {

//this.writer = new PrintWriter("frutas_comID3.arff", "UTF-8");
        //writer.println("@relation frutas");
        //writer.println("@attribute madureza {0, 1, 2}");
        //writer.println("@attribute carboidratos {0, 1, 2}");
        //writer.println("@attribute fibras {0, 1, 2}");
        //writer.println("@attribute proteinas {0, 1, 2}");
        //writer.println("@attribute lipideos {0, 1, 2}");
        //writer.println("@attribute energia {5, 25, 50, 100}");
        //writer.println("@data");
        this.posX = posX;
        this.posY = posY;
        this.origem = new int[2];
        this.origem[0] = posX;
        this.origem[1] = posY;
        this.modelo = modelo;
        this.caminho = new StringBuffer("");
        this.enableEnergia = true;
        this.energia = 250;
        this.energiaParaCaminhar = 45;
        cesta = new ArrayList<>();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setEnergiaParaCaminhar(int energiaParaCaminhar) {
        this.energiaParaCaminhar = energiaParaCaminhar;
    }

    public void setEstrategiaDecisao(int estrategiaDecisao) {
        this.estrategiaDecisao = estrategiaDecisao;
    }

    public Labirinto getModelo() {
        return modelo;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setModelo(Labirinto modelo) {
        this.modelo = modelo;
    }

    public void set(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int ir(String dir) {
        if (enableEnergia) {
            while (energia < (energiaParaCaminhar + cesta.size() * 5) && cesta.size() > 0) {
                System.out.println("Energia = " + this.energia + " insuficente para caminhar. Comendo uma fruta da cesta...");
                if (comerCesta() == 0) {
                    System.out.println("Game Over . . . Energia = " + energia + ". Frutas na cesta: " + cesta.size());
                    // Comente a linha abaixo, a outra linha indicada no metodo ir() e a linha idicada no metodo comerCesta() para tirar o criterio de parada por energia
                    return 0;
                }

                System.out.println("");
            }
            if (energia < (energiaParaCaminhar + cesta.size() * 5)) {
                System.out.println("Game Over . . . Energia = " + energia + ". Frutas na cesta: " + cesta.size());
                // Comente a linha abaixo, a outra linha indicada no metodo ir() e a linha idicada no metodo comerCesta() para tirar o criterio de parada por energia
                return 0;
            }

            // Consome energia
            energia -= energiaParaCaminhar - cesta.size() * 5;
        }

        caminho.append(dir);
        caminho.append(" ");
        int x = this.posX;
        int y = this.posY;
        try {
            switch (dir) {
                case "N":
                    if (modelo.get(posX, posY - 1) != modelo.getParede()) {
                        posY = posY - 1;
                    }
                    break;
                case "NE":
                    if (modelo.get(posX + 1, posY - 1) != modelo.getParede()) {
                        posX = posX + 1;
                        posY = posY - 1;
                    }
                    break;
                case "E":
                    if (modelo.get(posX + 1, posY) != modelo.getParede()) {
                        posX = posX + 1;
                    }
                    break;
                case "SE":
                    if (modelo.get(posX + 1, posY + 1) != modelo.getParede()) {
                        posX = posX + 1;
                        posY = posY + 1;
                    }
                    break;
                case "S":
                    if (modelo.get(posX, posY + 1) != modelo.getParede()) {
                        posY = posY + 1;
                    }
                    break;
                case "SO":
                    if (modelo.get(posX - 1, posY + 1) != modelo.getParede()) {
                        posX = posX - 1;
                        posY = posY + 1;
                    }
                    break;
                case "O":
                    if (modelo.get(posX - 1, posY) != modelo.getParede()) {
                        posX = posX - 1;
                    }
                    break;
                case "NO":
                    if (modelo.get(posX - 1, posY - 1) != modelo.getParede()) {
                        posX = posX - 1;
                        posY = posY - 1;
                    }
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            this.posX = x;
            this.posY = y;
        }

        // Decide se pega fruta da proxima casa : escolher entre uma das duas
        if (estrategiaDecisao == 1) {
            decisaoFruta(); // aleatoria (para treinamento)
        } else if (estrategiaDecisao == 2) {
            descisaFrutaInteligente(); // inteligente
        } else if (estrategiaDecisao == 3) {
            comeTudo(); //come todas as frutas que encontra
        }
        return 1;
    }
    //    public int ir(int dir) {
    //        int x = this.posX;
    //        int y = this.posY;
    //        try {
    //            switch (dir) {
    //                case 0:
    //                    if (modelo.get(posX, posY - 1) != modelo.getParede()) {
    //                        posY = posY - 1;
    //                    }
    //                    break;
    //                case 1:
    //                    if (modelo.get(posX + 1, posY - 1) != modelo.getParede()) {
    //                        posX = posX + 1;
    //                        posY = posY - 1;
    //                    }
    //                    break;
    //                case 2:
    //                    if (modelo.get(posX + 1, posY) != modelo.getParede()) {
    //                        posX = posX + 1;
    //                    }
    //                    break;
    //                case 3:
    //                    if (modelo.get(posX + 1, posY + 1) != modelo.getParede()) {
    //                        posX = posX + 1;
    //                        posY = posY + 1;
    //                    }
    //                    break;
    //                case 4:
    //                    if (modelo.get(posX, posY + 1) != modelo.getParede()) {
    //                        posY = posY + 1;
    //                    }
    //                    break;
    //                case 5:
    //                    if (modelo.get(posX - 1, posY + 1) != modelo.getParede()) {
    //                        posX = posX - 1;
    //                        posY = posY + 1;
    //                    }
    //                    break;
    //                case 6:
    //                    if (modelo.get(posX - 1, posY) != modelo.getParede()) {
    //                        posX = posX - 1;
    //                    }
    //                    break;
    //                case 7:
    //                    if (modelo.get(posX - 1, posY - 1) != modelo.getParede()) {
    //                        posX = posX - 1;
    //                        posY = posY - 1;
    //                    }
    //                    break;
    //                default:
    //                    break;
    //            }
    //        } catch (ArrayIndexOutOfBoundsException e) {
    //            this.posX = x;
    //            this.posY = y;
    //        }
    //        return 1;
    //    }

    public Direcao sucessora() {
        double custo = Double.MAX_VALUE;
        double temp;
        String dir = "ERRO";
        Random rand = new Random(System.currentTimeMillis());
        int sel = rand.nextInt(2);
        //seleciona o menor vizinho
        switch (sel) {
            case 0:
                for (int i = 0; i < 8; i++) {
                    try {
                        switch (i) {
                            case 0:
                                if ((temp = modelo.get(posX, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "N";
                                    }
                                }
                                break;
                            case 1:
                                if ((temp = modelo.get(posX + 1, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "NE";
                                    }
                                }
                                break;
                            case 2:
                                if ((temp = modelo.get(posX + 1, posY)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "E";
                                    }
                                }
                                break;
                            case 3:
                                if ((temp = modelo.get(posX + 1, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "SE";
                                    }
                                }
                                break;
                            case 4:
                                if ((temp = modelo.get(posX, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "S";
                                    }
                                }
                                break;
                            case 5:
                                if ((temp = modelo.get(posX - 1, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "SO";
                                    }
                                }
                                break;
                            case 6:
                                if ((temp = modelo.get(posX - 1, posY)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "O";
                                    }
                                }
                                break;
                            case 7:
                                if ((temp = modelo.get(posX - 1, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "NO";
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 8; i++) {
                    try {
                        switch (i) {
                            case 7:
                                if ((temp = modelo.get(posX, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "N";
                                    }
                                }
                                break;
                            case 6:
                                if ((temp = modelo.get(posX + 1, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "NE";
                                    }
                                }
                                break;
                            case 5:
                                if ((temp = modelo.get(posX + 1, posY)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "E";
                                    }
                                }
                                break;
                            case 4:
                                if ((temp = modelo.get(posX + 1, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "SE";
                                    }
                                }
                                break;
                            case 3:
                                if ((temp = modelo.get(posX, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "S";
                                    }
                                }
                                break;
                            case 2:
                                if ((temp = modelo.get(posX - 1, posY + 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "SO";
                                    }
                                }
                                break;
                            case 1:
                                if ((temp = modelo.get(posX - 1, posY)) != modelo.getParede()) {
                                    if (temp + 1 < custo) {
                                        custo = temp + 1;
                                        dir = "O";
                                    }
                                }
                                break;
                            case 0:
                                if ((temp = modelo.get(posX - 1, posY - 1)) != modelo.getParede()) {
                                    if (temp + 1.5 < custo) {
                                        custo = temp + 1.5;
                                        dir = "NO";
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                break;
            default:
                break;
        }
        return new Direcao(dir, custo);
    }

    public double getDir(String s) throws ArrayIndexOutOfBoundsException {
        switch (s) {
            case "N":
                return modelo.get(posX, posY - 1);
            case "NE":
                return modelo.get(posX + 1, posY - 1);
            case "E":
                return modelo.get(posX + 1, posY);
            case "SE":
                return modelo.get(posX + 1, posY + 1);
            case "S":
                return modelo.get(posX, posY + 1);
            case "SO":
                return modelo.get(posX - 1, posY + 1);
            case "O":
                return modelo.get(posX - 1, posY);
            case "NO":
                return modelo.get(posX - 1, posY - 1);
            default:
                throw new NullPointerException("valor invalido");
        }
    }

    public int executar() {
        int k = 0;
        Direcao dir;
        chutarEnergia();
        try {
            modelo.desenhar(1);
            while ((posX != modelo.getObjetivo().getX()) || (posY != modelo.getObjetivo().getY())) {
                dir = sucessora();
                // caslcula o custo na posição
                modelo.set(dir.getCusto(), posX, posY);
                if (ir(dir.getPontoCardinal()) == 0) {
                    return 0;
                }
                modelo.desenhar(1);
                k++;
            }
            System.out.println("Vitória! Chegou na posição final! . . . Energia = " + energia + ". Frutas na cesta: " + cesta.size());
        } catch (NullPointerException e) {
            System.out.println("Faltou inicializar algo");
        }
        return k;
    }

    public int executar(int n) {
        // Chuta os valores de energia de todas as frutas geradas, segundo o resultado do ID3
        chutarEnergia();
        int sucessos = 0;
        for (int i = 0; i < n; i++) {
            // Reposiciona as frutas no labirinto
            modelo.gerarFrutas(modelo.frutas);
            if (executarLimpo() > 0) {
                sucessos++;
            }
            voltaOrigem();
            //System.out.println(i);
        }
        modelo.gerarFrutas(modelo.frutas);
        //writer.close();
        System.out.println("Energia para caminhar: " + this.energiaParaCaminhar + " Sucessos: " + sucessos);
        return 0;
    }

    public int executarLimpo() {
        int k = 0;
        Direcao dir;
        chutarEnergia();
        try {
            while ((posX != modelo.getObjetivo().getX()) || (posY != modelo.getObjetivo().getY())) {
                dir = sucessora();
                // calcula o custo na posição
                modelo.set(dir.getCusto(), posX, posY);
                if (ir(dir.getPontoCardinal()) == 0) {
                    return 0;
                }
                k++;
            }
            System.out.println("(executarLimpo:) Vitória! Chegou na posição final! . . . Energia = " + energia + ". Frutas na cesta: " + cesta.size());
        } catch (NullPointerException e) {
            System.out.println("Faltou inicializar algo");
        }
        return k;
    }

    public String getCaminho() {
        return caminho.toString();
    }

    public void voltaOrigem() {
        this.energia = 250;
        this.cesta.clear();
        this.posX = this.origem[0];
        this.posY = this.origem[1];
        caminho = new StringBuffer("");
        System.out.println("\n\nIniciando novo jogo:");
    }

    public boolean isEnableEnergia() {
        return enableEnergia;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnableEnergia(boolean enableEnergia) {
        this.enableEnergia = enableEnergia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int comerCesta() {
        if (enableEnergia) {
            int maiorEnergia = 0;
            int indexMaiorEnergia = 0;

            if (energia < 15) {
                System.out.println("Sem energia suficiente para comer uma fruta. Min: (15 kcal). Atual: " + energia);
                // Comente a linha abaixo e as duas linhas indicadas no metodo ir() para tirar o criterio de parada por energia
                return 0;
            }
            if (cesta.size() > 0) {

                // Come uma fruta aleatoriamente, imprime suas caracteristicas e a remove da cesta
                Random r = new Random();
                int indx = 0;
                System.out.println("Frutas na cesta: " + cesta.size() + ". Comendo uma fruta... ");
                if (cesta.size() > 1) {
                    indx = r.nextInt(cesta.size() - 1);
                } else if (cesta.size() == 1) {
                    indx = 0;
                }
                Fruta f = cesta.get(indx);
                energia += f.getEnergia() - 15;
                //writer.print(f.madureza + ", " + f.carboidratos + ", " + f.fibras + ", " + f.proteinas + ", " + f.lipideos + ", " + f.energia);
                //writer.println();
                cesta.remove(indx);
                System.out.print("Energia atual do agente: " + this.energia);

//                maiorEnergia = cesta.get(cesta.size() - 1).getEnergia();
//
//                // Procura a fruta de maior energia
//                for (int i = 0; i < cesta.size(); i++) {
//                    Fruta f = cesta.get(i);
//                    if (f.energia > maiorEnergia) {
//                        maiorEnergia = f.getEnergia();
//                        indexMaiorEnergia = i;
//                    }
//                }
//
//                // Adiciona a energia da fruta e a remove da cesta
//                energia += cesta.get(indexMaiorEnergia).getEnergia() - 15;
//                cesta.remove(indexMaiorEnergia);
                return 1;
            } else {
                System.out.println("Cesta vazia.");
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int comerChao() {
        if (enableEnergia) {
            if (energia < 15) {
                System.out.println("Sem energia suficiente para comer uma fruta. Min: (15 kcal). Atual: " + energia);
                return 0;
            } else {
                energia += modelo.energiaFruta() - 15;
                Fruta f = modelo.getFruta();
                //writer.print(f.madureza + ", " + f.carboidratos + ", " + f.fibras + ", " + f.proteinas + ", " + f.lipideos + ", " + f.energia);
                //writer.println();
            }
        } else {
            return 0;
        }
        return 1;
    }

    public void guardarFruta() {
        cesta.add(modelo.getFruta());
    }

    // decisao aleatoria (para fins de treinamento)
    private void decisaoFruta() {
        Random r = new Random();
        int decisao = r.nextInt(3);
        switch (decisao) {
            case 0:
                System.out.println("Energia: " + modelo.getFruta().getEnergia() + ". Decisão aleatória: Deixa fruta.");
                break;
            case 1:
                comerChao();
                System.out.println("Energia: " + modelo.getFruta().getEnergia() + ". Decisão aleatória: Come fruta.");
                break;
            case 2:
                guardarFruta();
                System.out.println("Energia: " + modelo.getFruta().getEnergia() + ". Decisão aleatória: Guarda fruta.");
                break;
            default:
                System.out.println("Tem algo muito errado com a funcao decisaoFruta()");
                break;
        }
    }

    private void descisaFrutaInteligente() {
        if (modelo.getFruta().achoEnergia >= 50) {
            // Se fruta dá muita energia, compensa carregá-la
            guardarFruta();
            System.out.println("Energia real: " + modelo.getFruta().getEnergia() + ". Energia ID3: " + modelo.getFruta().achoEnergia + ". Decisão inteligente: Guarda fruta!");
        } else if (modelo.getFruta().achoEnergia >= 15) {
            // Se não compensa carregar a fruta, porém ela dá mais energia do que comê-la, coma-a
            comerChao();
            System.out.println("Energia real: " + modelo.getFruta().getEnergia() + ". Energia ID3: " + modelo.getFruta().achoEnergia + ". Decisão inteligente: Come fruta!");
        } else {
            // Pouquissima energia. Deixa a fruta lá mesmo
            System.out.println("Energia real: " + modelo.getFruta().getEnergia() + ". Energia ID3: " + modelo.getFruta().achoEnergia + ". Decisão inteligente: Deixa fruta!");
        }
    }

    private void comeTudo() {
        comerChao();
    }

    // 'Chuta' um valor de energia para cada fruta gerada para o modelo atual, segundo os resultados do ID3
    private void chutarEnergia() {
        for (int i = 0; i < modelo.frutas.size(); i++) {
            Fruta f = modelo.frutas.get(i);
            if (f.madureza == 0) {
                if (f.carboidratos == 0) {
                    if (f.lipideos == 0) {
                        if (f.proteinas == 0) {
                            f.achoEnergia = 5;
                        } else if (f.proteinas == 1) {
                            f.achoEnergia = 5;
                        } else if (f.proteinas == 2) {
                            f.achoEnergia = 50;
                        }
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 25;
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 25;
                    }
                } else if (f.carboidratos == 1) {
                    if (f.lipideos == 0) {
                        f.achoEnergia = 25;
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 50;
                    } else if (f.lipideos == 2) {
                        f.achoEnergia = 50;
                    }
                } else if (f.carboidratos == 2) {
                    if (f.lipideos == 0) {
                        f.achoEnergia = 25;
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 50;
                    } else if (f.lipideos == 2) {
                        f.achoEnergia = 50;
                    }
                }
            } else if (f.madureza == 1) {
                if (f.carboidratos == 0) {
                    if (f.lipideos == 0) {
                        if (f.fibras == 0) {
                            f.achoEnergia = 5;
                        } else if (f.fibras == 1) {
                            f.achoEnergia = 5;
                        } else if (f.fibras == 2) {
                            if (f.proteinas == 0) {
                                f.achoEnergia = 5;
                            } else if (f.proteinas == 1) {
                                f.achoEnergia = 5;
                            } else if (f.proteinas == 2) {
                                f.achoEnergia = 50;
                            }
                        }
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 50;
                    } else if (f.lipideos == 2) {
                        f.achoEnergia = 50;
                    }
                } else if (f.carboidratos == 1) {
                    f.achoEnergia = 100;
                } else if (f.carboidratos == 2) {
                    f.achoEnergia = 100;
                }
            } else if (f.madureza == 2) {
                if (f.carboidratos == 0) {
                    f.achoEnergia = 0;
                } else if (f.carboidratos == 1) {
                    if (f.lipideos == 0) {
                        f.achoEnergia = 5;
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 25;
                    } else if (f.lipideos == 2) {
                        f.achoEnergia = 25;
                    }
                } else if (f.carboidratos == 2) {
                    if (f.lipideos == 0) {
                        f.achoEnergia = 5;
                    } else if (f.lipideos == 1) {
                        f.achoEnergia = 25;
                    } else if (f.lipideos == 2) {
                        f.achoEnergia = 25;
                    }
                }
            }
        }
    }
}
