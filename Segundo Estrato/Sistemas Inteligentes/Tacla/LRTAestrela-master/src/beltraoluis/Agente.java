package beltraoluis;

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
    protected Labirinto modelo;

    public Agente(int posX, int posY, Labirinto modelo) {
        this.posX = posX;
        this.posY = posY;
        this.modelo = modelo;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
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
    
    public void set(int x, int y){
        this.posX = x;
        this.posY = y;        
    }
    
    public int ir(String dir){
        int x = this.posX;
        int y = this.posY;
        try{
            switch(dir){
                case "N":   if(modelo.get(posX, posY-1) != modelo.getParede()){
                                posY = posY - 1;
                            }
                            break;
                case "NE":  if(modelo.get(posX+1, posY-1) != modelo.getParede()){
                                posX = posX + 1;          
                                posY = posY - 1;
                            }
                            break;
                case "E":   if(modelo.get(posX+1, posY) != modelo.getParede()){
                                posX = posX + 1;
                            }
                            break;
                case "SE":  if(modelo.get(posX+1, posY+1) != modelo.getParede()){
                                posX = posX + 1;          
                                posY = posY + 1;
                            }
                            break;
                case "S":   if(modelo.get(posX, posY+1) != modelo.getParede()){
                                posY = posY + 1;
                            }
                            break;
                case "SO":  if(modelo.get(posX-1, posY+1) != modelo.getParede()){
                                posX = posX - 1;          
                                posY = posY + 1;
                            }
                            break;
                case "O":   if(modelo.get(posX-1, posY) != modelo.getParede()){
                                posX = posX - 1;
                            }
                            break;
                case "NO": if(modelo.get(posX-1, posY-1) != modelo.getParede()){
                                posX = posX - 1;          
                                posY = posY - 1;
                            }
                            break;
                default: break;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            this.posX = x;
            this.posY = y;
        }
        return 1;
    }
    
    public int ir(int dir){
        int x = this.posX;
        int y = this.posY;
        try{
            switch(dir){
                case 0: if(modelo.get(posX, posY-1) != modelo.getParede()){
                            posY = posY - 1;
                        }
                        break;
                case 1: if(modelo.get(posX+1, posY-1) != modelo.getParede()){
                            posX = posX + 1;          
                            posY = posY - 1;
                        }
                        break;
                case 2: if(modelo.get(posX+1, posY) != modelo.getParede()){
                            posX = posX + 1;
                        }
                        break;
                case 3: if(modelo.get(posX+1, posY+1) != modelo.getParede()){
                            posX = posX + 1;          
                            posY = posY + 1;
                        }
                        break;
                case 4: if(modelo.get(posX, posY+1) != modelo.getParede()){
                            posY = posY + 1;
                        }
                        break;
                case 5: if(modelo.get(posX-1, posY+1) != modelo.getParede()){
                            posX = posX - 1;          
                            posY = posY + 1;
                        }
                        break;
                case 6: if(modelo.get(posX-1, posY) != modelo.getParede()){
                            posX = posX - 1;
                        }
                        break;
                case 7: if(modelo.get(posX-1, posY-1) != modelo.getParede()){
                            posX = posX - 1;          
                            posY = posY - 1;
                        }
                        break;
                default: break;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            this.posX = x;
            this.posY = y;
        }
        return 1;
    }
    
    public Direcao sucessora(){
        double custo = Double.MAX_VALUE;
        double temp;
        String dir = "ERRO";
            //seleciona o menor vizinho
        for(int i = 0; i < 8; i++){
            try{
                switch(i){
                    case 0: if((temp = modelo.get(posX, posY-1)) != modelo.getParede()){
                                if(temp+1 < custo){
                                    custo = temp+1;
                                    dir = "N";
                                }
                            }
                            break;
                    case 1: if((temp = modelo.get(posX+1, posY-1)) != modelo.getParede()){
                                if(temp+1.5 < custo){
                                    custo = temp+1.5;
                                    dir = "NE";
                                }
                            }
                            break;
                    case 2: if((temp = modelo.get(posX+1, posY)) != modelo.getParede()){
                                if(temp+1 < custo){
                                    custo = temp+1;
                                    dir = "E";
                                }
                            }
                            break;
                    case 3: if((temp = modelo.get(posX+1, posY+1)) != modelo.getParede()){
                                if(temp+1.5 < custo){
                                    custo = temp+1.5;
                                    dir = "SE";
                                }
                            }
                            break;
                    case 4: if((temp = modelo.get(posX, posY+1)) != modelo.getParede()){
                                if(temp+1 < custo){
                                    custo = temp+1;
                                    dir = "S";
                                }
                            }
                            break;
                    case 5: if((temp = modelo.get(posX-1, posY+1)) != modelo.getParede()){
                                if(temp+1.5 < custo){
                                    custo = temp+1.5;
                                    dir = "SO";
                                }
                            }
                            break;
                    case 6: if((temp = modelo.get(posX-1, posY)) != modelo.getParede()){
                                if(temp+1 < custo){
                                    custo = temp+1;
                                    dir = "O";
                                }
                            }
                            break;
                    case 7: if((temp = modelo.get(posX-1, posY-1)) != modelo.getParede()){
                                if(temp+1.5 < custo){
                                    custo = temp+1.5;
                                    dir = "NO";
                                }
                            }
                            break;
                    default: break;
                }
            }catch(ArrayIndexOutOfBoundsException e){
            }
        }
        return new Direcao(dir,custo);
    }
    
    
    public double getDir(String s) throws ArrayIndexOutOfBoundsException{
        switch(s){
                case "N":   return modelo.get(posX, posY-1);
                case "NE":  return modelo.get(posX+1, posY-1);
                case "E":   return modelo.get(posX+1, posY);
                case "SE":  return modelo.get(posX+1, posY+1);
                case "S":   return modelo.get(posX, posY+1);
                case "SO":  return modelo.get(posX-1, posY+1);
                case "O":   return modelo.get(posX-1, posY);
                case "NO":  return modelo.get(posX-1, posY-1);
                default: throw new NullPointerException("valor invalido");
            }
    }
    
    public int executar(){
        int k = 0;
        Direcao dir;
        try{
            modelo.desenhar(1);
            while((posX != modelo.getObjetivo().getX()) || (posY != modelo.getObjetivo().getY())){
                dir = sucessora();
                // caslcula o custo na posição
                modelo.set(dir.getCusto(), posX, posY);
                ir(dir.getPontoCardinal());
                modelo.desenhar(1);
                k++;
            }
        }catch(NullPointerException e){
            System.out.println("Faltou inicializar algo");
        }
        return k;
    }
    
    public int executarLimpo(){
        int k = 0;
        Direcao dir;
        try{
            while((posX != modelo.getObjetivo().getX()) || (posY != modelo.getObjetivo().getY())){
                dir = sucessora();
                // caslcula o custo na posição
                modelo.set(dir.getCusto(), posX, posY);
                ir(dir.getPontoCardinal());
                k++;
            }
        }catch(NullPointerException e){
            System.out.println("Faltou inicializar algo");
        }
        return k;
    }
}
