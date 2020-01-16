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
public class Plataforma {
    int _id;
    String _nombre;
 
    
    
    public Plataforma( String _nombre ) {
        this._nombre = _nombre;
  
    }

    public Plataforma() {
     
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

 
    
    
}
