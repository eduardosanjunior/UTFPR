/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beltraoluis.matematica;

/**
 *
 * @author beltraoluis
 */
public class Coordenada2 {
    protected int x;
    protected int y;

    public Coordenada2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }
}
