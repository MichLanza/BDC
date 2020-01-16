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
public class DTOfullincidencia {
    public int _incId;
    public String _incNombre;
    public String _incDesc;
    public LocalDate  _incFecha;
    public String _solDesc;
    public LocalDate _solFecha;
    public int _idArea;
    public int _idPlat;

    
    
     public DTOfullincidencia() {
    }
    
     
    public DTOfullincidencia( String incNombre, String incDesc, 
                              LocalDate incFecha, String solDesc, 
                              LocalDate solFecha, int idArea, int idPlat ) {
    
        _incNombre = incNombre;
        _incDesc = incDesc;
        _incFecha = incFecha;
        _solDesc = solDesc;
        _solFecha = solFecha;
        _idArea = idArea;
        _idPlat = idPlat;
    }

    public DTOfullincidencia(int _incId, String _incNombre, String _incDesc,
                             LocalDate _incFecha, String _solDesc, 
                             LocalDate _solFecha, int _idArea, int _idPlat) {
        
        this._incId = _incId;
        this._incNombre = _incNombre;
        this._incDesc = _incDesc;
        this._incFecha = _incFecha;
        this._solDesc = _solDesc;
        this._solFecha = _solFecha;
        this._idArea = _idArea;
        this._idPlat = _idPlat;
    }
    
    
    

    public int getIncId() {
        return _incId;
    }

    public void setIncId(int _incId) {
        this._incId = _incId;
    }


    public String getIncDesc() {
        return _incDesc;
    }

    public void setIncDesc(String incDesc) {
       _incDesc = incDesc;
    }

    public String getSolDesc() {
        return _solDesc;
    }

    public void setSolDesc(String solDesc) {
        _solDesc = solDesc;
    }

    public int getIdArea() {
        return _idArea;
    }

    public void setIdArea(int idArea) {
       _idArea = idArea;
    }

    public int getIdPlat() {
        return _idPlat;
    }

    public void setIdPlat(int idPlat) {
        _idPlat = idPlat;
    }

    public String getIncNombre() {
        return _incNombre;
    }

    public void setIncNombre(String incNombre) {
        _incNombre = incNombre;
    }

    public LocalDate getIncFecha() {
        return _incFecha;
    }

    public void setIncFecha(LocalDate incFecha) {
       _incFecha = incFecha;
    }

    public LocalDate getSolFecha() {
        return _solFecha;
    }

    public void setSolFecha(LocalDate solFecha) {
       _solFecha = solFecha;
    }
    
    
    
    
}



