/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Michele Lanza
 */
public class DTOIncidenciaWOS {
    

    public String _incNombre;
    public String _incDesc;
    public LocalDate  _incFecha;
    public int _idArea;
    public int _idPlat;

    public DTOIncidenciaWOS() {
    }

    
    public DTOIncidenciaWOS(  String _incNombre, String _incDesc, 
                              LocalDate _incFecha, int _idArea, int _idPlat ) {

        this._incNombre = _incNombre;
        this._incDesc = _incDesc;
        this._incFecha = _incFecha;
        this._idArea = _idArea;
        this._idPlat = _idPlat;
    }


 
    public String getIncDesc() {
        return _incDesc;
    }

    public void setIncDesc(String _incDesc) {
        this._incDesc = _incDesc;
    }

    public int getIdArea() {
        return _idArea;
    }

    public void setIdArea(int _idArea) {
        this._idArea = _idArea;
    }

    public int getIdPlat() {
        return _idPlat;
    }

    public void setIdPlat(int _idPlat) {
        this._idPlat = _idPlat;
    }

    public String getIncNombre() {
        return _incNombre;
    }

    public void setIncNombre(String _incNombre) {
        this._incNombre = _incNombre;
    }

    public LocalDate getIncFecha() {
        return _incFecha;
    }

    public void setIncFecha(LocalDate _incFecha) {
        this._incFecha = _incFecha;
    }

    
    
    
    
}





