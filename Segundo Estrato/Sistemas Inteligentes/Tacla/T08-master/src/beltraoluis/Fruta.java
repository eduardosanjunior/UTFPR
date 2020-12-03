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
public class Fruta {
    protected int madureza;
    protected int carboidratos;
    protected int fibras;
    protected int proteinas;  
    protected int lipideos;
    protected int energia;

    public Fruta(int madureza, int carboidratos, int fibras, int proteinas, int lipideos) {
        this.madureza = madureza;
        this.carboidratos = carboidratos;
        this.fibras = fibras;
        this.proteinas = proteinas;
        this.lipideos = lipideos;
        this.energia = 5;
        if(lipideos == Lipideo.ALTA || lipideos == Lipideo.MODERADA){
            if(carboidratos == Carboidrato.POUCA){
                if(madureza == Madureza.PODRE) energia = 5;
                if(madureza == Madureza.VERDE) energia = 25;
                if(madureza == Madureza.MADURA) energia = 50;
            }else{
                if(madureza == Madureza.PODRE) energia = 25;
                if(madureza == Madureza.VERDE) energia = 50;
                if(madureza == Madureza.MADURA) energia = 100;
            }
        }else{
            if(carboidratos == Carboidrato.POUCA){
                if(proteinas == Proteina.ALTA && fibras == Fibra.ALTA && madureza != Madureza.PODRE) energia = 50;
            }else{
                if(madureza == Madureza.PODRE) energia = 5;
                if(madureza == Madureza.VERDE) energia = 25;
                if(madureza == Madureza.MADURA) energia = 100;
            }
        }
    }
    
    public int getEnergia(){
        return energia;
    }    
    
}
