/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;


/**
 *
 * @author Michele Lanza
 */
public class Incidencia {
    int _id;
    String _nombre;
    String _descripcion;
    LocalDate _fechaOcurrencia; 
    String _solDescripcion;
    LocalDate _fechaResolucion;
    int _are;
    int _plat;
    String _areName;
    String _plName;
    Area _ar;
    Plataforma pl;
    int _idFile;
  
    //contructor por defecto
    public Incidencia(){}
    
    //incidencia sin solucion id area y plataforma
    public Incidencia( String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, int _are, int _plat ) {
        
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._are = _are;
        this._plat = _plat;
    }
    
      public Incidencia( int _id ,String _nombre, String _descripcion,  
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

    public Incidencia( int _id, String _nombre, String _descripcion, 
                      LocalDate _fechaOcurrencia ) {
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
    }
    
 
    public Incidencia( int _id, String _nombre, String _descripcion,  
                      LocalDate _fechaOcurrencia, Area _ar, Plataforma pl ) {
       
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._ar = _ar;
        this.pl = pl;
    }

    //constructor incidencia con solucion id area y plataforma
    public Incidencia( String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, String _solDescripcion, 
                      LocalDate _fechaResolucion, int _are, int _plat  ) {
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._fechaResolucion = _fechaResolucion;
        this._are = _are;
        this._plat = _plat;
    }
    
      public Incidencia( int _id, String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, String _solDescripcion, 
                       Area _are, Plataforma _plat  ) {
          
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._ar = _are;
        this.pl = _plat;
    }
    
    //constructor incidencia con id incidencia, solucion, id area y plataforma
    public Incidencia(int _id, String _nombre, String _descripcion,
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
    
        //constructor incidencia con id incidencia, solucion, id area y plataforma
    public Incidencia(int _id, String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, String _solDescripcion,
                      LocalDate _fechaResolucion, Area _are, Plataforma _plat) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._fechaResolucion = _fechaResolucion;
        this._ar = _are;
        this.pl = _plat;
    }
    

    public Incidencia(int _id, String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, String _solDescripcion,
                      LocalDate _fechaResolucion,
                      int _are, int _plat, String _areName, String _plName) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._fechaResolucion = _fechaResolucion;
        this._are = _are;
        this._plat = _plat;
        this._areName = _areName;
        this._plName = _plName;
    }
 
    
       public Incidencia(int _id, String _nombre, String _descripcion,
                      LocalDate _fechaOcurrencia, String _solDescripcion,
                
                      int _are, int _plat, String _areName, String _plName) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._are = _are;
        this._plat = _plat;
        this._areName = _areName;
        this._plName = _plName;
    }

    public Incidencia(int _id, String _nombre, String _descripcion, 
                      LocalDate _fechaOcurrencia, String _solDescripcion,
                      LocalDate _fechaResolucion,  int _are, int _plat, 
                      String _areName, String _plName,int _idFile) {
        
        this._id = _id;
        this._nombre = _nombre;
        this._descripcion = _descripcion;
        this._fechaOcurrencia = _fechaOcurrencia;
        this._solDescripcion = _solDescripcion;
        this._fechaResolucion = _fechaResolucion;
        this._are = _are;
        this._plat = _plat;
        this._areName = _areName;
        this._plName = _plName;
        this._idFile = _idFile;
    }
 
       
    
    
    
    public int getId() {
        return _id;
    }

    public void setId( int _id ) {
        this._id = _id;
    }

    public String getNombre() {
        return _nombre;
    }
 
    public void setNombre( String _nombre ) {
        this._nombre = _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion( String _descripcion ) {
        this._descripcion = _descripcion;
    }
       
     public LocalDate getFechaOcurrencia() {
        return _fechaOcurrencia;
    }

    public void setFechaOcurrencia( LocalDate _fechaOcurrencia ) {
        this._fechaOcurrencia = _fechaOcurrencia;
    }

    public int getAre() {
        return _are;
    }

    public void setAre(int _are) {
        this._are = _are;
    }

    public int getPlat() {
        return _plat;
    }

    public void setPlat(int _plat) {
        this._plat = _plat;
    }

    public Area getAr() {
        return _ar;
    }

    public void setAr(Area _ar) {
        this._ar = _ar;
    }

    public Plataforma getPl() {
        return pl;
    }

    public void setPl(Plataforma pl) {
        this.pl = pl;
    }

    public String getSolDescripcion() {
        return _solDescripcion;
    }

    public void setSolDescripcion(String _solDescripcion) {
        this._solDescripcion = _solDescripcion;
    }

    public LocalDate getFechaResolucion() {
        return _fechaResolucion;
    }

    public void setFechaResolucion(LocalDate _fechaResolucion) {
        this._fechaResolucion = _fechaResolucion;
    }

    public String getAreName() {
        return _areName;
    }

    public void setAreName(String _areName) {
        this._areName = _areName;
    }

    public String getPlName() {
        return _plName;
    }

    public void setPlName(String _plName) {
        this._plName = _plName;
    }

    public int getIdFile() {
        return _idFile;
    }

    public void setIdFile(int _idFile) {
        this._idFile = _idFile;
    }

    
    
    
}
