/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beltraoluis.matematica;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author beltraoluis
 * @version 20170921
 */
public class MatrizDouble {

    /**
     * vetor bi dimensional que armazena os dados
     */
    protected double[][] vetor;

    /**
     * número de linhas da matriz correspondente a coordena y que esta 
     * direcionada de cima para baixo iniciando da posição 0 até a 
     * posição linhas-1.
     */
    protected int linhas;

    /**
     * número de colunas da matriz correspondente a coordenada x que esta 
     * direcionada da esquerda para a direita iniciando da posição 0 até a 
     * posição colunas-1.
     */
    protected int colunas;

    // construtor

    /**
     * construtor da matriz
     * @param linhas
     * @param colunas
     */
    public MatrizDouble(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this. vetor = new double[linhas][colunas];
    }

    // getter

    /**
     *
     * @return
     */
    public int getLinhas() {
        return linhas;
    }

    /**
     *
     * @return
     */
    public int getColunas() {
        return colunas;
    }
    
    public double get(int x, int y) throws ArrayIndexOutOfBoundsException{   
    return vetor[y][x];
    }
    
    /**
     *
     * @param valor
     * @param x
     * @param y
     */
    public void set(double valor, int x, int y) throws ArrayIndexOutOfBoundsException{
        vetor[y][x] = valor;
    }
    
    public void setLinha(double valor, int linha) throws ArrayIndexOutOfBoundsException{
        for(int i = 0; i < colunas;i++){
            vetor[linha][i] = valor;
        }
    }
    
    public void setLinha(double valor, int inicio, int fim, int linha) throws ArrayIndexOutOfBoundsException{
        for(int i = inicio; i <= fim;i++){   
            vetor[linha][i] = valor;
        }
    }
    
    public void setColuna(double valor, int coluna) throws ArrayIndexOutOfBoundsException{
        for(int i = 0; i < linhas;i++){
            vetor[i][coluna] = valor;
        }
    }
    
    public void setColuna(double valor, int inicio, int fim, int coluna) throws ArrayIndexOutOfBoundsException{
        for(int i = inicio; i <= fim;i++){
            vetor[i][coluna] = valor;
        }
    }
    
    public void print(){
        StringBuilder s = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        Double d;
        System.out.println();
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                d = vetor[i][j];
                ss.append(d);
                if(d == Double.MAX_VALUE){
                    ss = new StringBuilder("inf  ");
                }
                // trucar em 3 caracteres
                else if(ss.length() < 5){
                    for(int k = 5-ss.length(); k > 0; k--){
                        ss.append("0");
                    }
                }
                else if(ss.length() > 5){
                    ss.delete(ss.length()-(ss.length()-5), ss.length());
                }
                s.append(" ");
                s.append(ss);
                ss.delete(0, ss.length());
            }
            System.out.println(s.toString());
            s.delete(0, s.length());
        }
    }
    
    public double Dispersao(){
        int k = 0;
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                if(vetor[i][j] != 0) {
                    k++;
                }
            }
        }
        return 1-(k/(double)(linhas*colunas));
    }
    
    public MatrizDouble copiar(){
        MatrizDouble m = new MatrizDouble(linhas, colunas);
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                m.set(vetor[i][j], j, i);
            }
        }
        return m;
    }
    
    public void euclidiana(int x, int y){
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                vetor[i][j] = Math.sqrt(Math.pow((i-y),2) + Math.pow((j-x),2));
            }
        }
    }
    
}
