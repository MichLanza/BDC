/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Michele Lanza
 */
public class Estadistica {
    public String x;
    public int y;
    public float porc;
    public int yS;
   
  
    public Estadistica() {
    }
    
    
    public Estadistica(String x, int y){
        this.x = x;
        this.y = y;
    }

    public Estadistica(String x, float porc) {
        this.x = x;
        this.porc = porc;
    }

    public Estadistica(String x, int y, int yS) {
        this.x = x;
        this.y = y;
        this.yS = yS;
    }

    

    
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getPorc() {
        return porc;
    }

    public void setPorc(float porc) {
        this.porc = porc;
    }

    public int getyS() {
        return yS;
    }

    public void setyS(int yS) {
        this.yS = yS;
    }

   
    
    
    
}
