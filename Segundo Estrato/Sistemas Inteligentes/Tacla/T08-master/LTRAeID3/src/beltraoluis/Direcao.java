/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beltraoluis;

/**
 *
 * @author beltraoluis
 */
public class Direcao {
    protected String pontoCardinal;
    protected double custo;

    public Direcao(String pontoCardinal, double custo) {
        this.pontoCardinal = pontoCardinal;
        this.custo = custo;
    }

    public String getPontoCardinal() {
        return pontoCardinal;
    }

    public double getCusto() {
        return custo;
    }

    public void setPontoCardinal(String pontoCardinal) {
        this.pontoCardinal = pontoCardinal;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
    
}
