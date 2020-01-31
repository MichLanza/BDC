/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.InputStream;

/**
 *
 * @author Michele Lanza
 */
public class Archivo {
    public String nombre;
       public InputStream file;

        public Archivo(String nombre, InputStream file) {
            this.nombre = nombre;
            this.file = file;
        }

        public Archivo() {
            
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
        
}
