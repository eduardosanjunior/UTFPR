/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beltraoluis.matematica;

/**
 *
 * @author beltraoluis
 * @version 20170921
 */
public class MatrizInt {

    /**
     * vetor bi dimensional que armazena os dados
     */
    protected int[][] vetor;

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
    public MatrizInt(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this. vetor = new int[linhas][colunas];
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
    
    public int get(int x, int y) throws ArrayIndexOutOfBoundsException{   
    return vetor[y][x];
    }
    
    /**
     *
     * @param valor
     * @param x
     * @param y
     */
    public void set(int valor, int x, int y) throws ArrayIndexOutOfBoundsException{
        vetor[y][x] = valor;
    }
    
    public void setLinha(int valor, int linha) throws ArrayIndexOutOfBoundsException{
        for(int i = 0; i < colunas;i++){
            vetor[linha][i] = valor;
        }
    }
    
    public void setLinha(int valor, int inicio, int fim, int linha) throws ArrayIndexOutOfBoundsException{
        for(int i = inicio; i <= fim;i++){   
            vetor[linha][i] = valor;
        }
    }
    
    public void setColuna(int valor, int coluna) throws ArrayIndexOutOfBoundsException{
        for(int i = 0; i < linhas;i++){
            vetor[i][coluna] = valor;
        }
    }
    
    public void setColuna(int valor, int inicio, int fim, int coluna) throws ArrayIndexOutOfBoundsException{
        for(int i = inicio; i <= fim;i++){
            vetor[i][coluna] = valor;
        }
    }
    
    public void print(){
        StringBuilder s = new StringBuilder();
        System.out.println();
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                s.append(" ");
                s.append(vetor[i][j]);
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
    
    public MatrizInt copiar(){
        MatrizInt m = new MatrizInt(linhas, colunas);
        for(int i = 0;i < linhas;i++){
            for(int j =0;j < colunas;j++){
                m.set(vetor[i][j], j, i);
            }
        }
        return m;
    }
    
}
