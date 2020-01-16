/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.time.LocalDate;

/**
 *
 * @author Michele Lanza
 */
public class DTOUpdate {
    
public int _id;
public String _nombre;
public String _descripcion;
public LocalDate _fechaOcurrencia;
public String _solDescripcion;
public LocalDate _fechaResolucion;
public int _are;
public int _plat;


     public DTOUpdate(){
    
     }
    
    public DTOUpdate(int _id, String _nombre, String _descripcion, 
                     LocalDate _fechaOcurrencia, String _solDescripcion, 
                     LocalDate _fechaResolucion, int _are, int _plat) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._fechaResolucion = _fechaResolucion;
        this._are = _are;
        this._plat = _plat;
    }
    
   
        public DTOUpdate( int _id, String _nombre, String _descripcion, 
                         String _solDescripcion, LocalDate _fechaOcurrencia,
                         int _are, int _plat ) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._solDescripcion = _solDescripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._are = _are;
        this._plat = _plat;
    }
    
  
}
