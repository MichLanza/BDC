/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.Estadistica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Michele Lanza
 */
public class EstadisticaDAO {
    String ANUAL = "{CALL getAnual (?)}";   
    String PLATAFORMA ="{CALL getByPlataforma (?)}";
    String AREA =  "{CALL getByArea (?)} ";
    String SOLUCION = "{CALL getBySol (?)}";
    String NOSOL =    "{CALL getByNoSol (?)}";
    String CUENTASOL = "{CALL cuentaSol (?)}";
 

public ArrayList <Estadistica> getByYear( int _year ){
          Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();
        
                          
         try{
         PreparedStatement _ps = _conn.prepareCall( ANUAL );
         _ps.setInt(1, _year);

          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
          Estadistica  stat = new Estadistica( _result.getString("Mes"),     
                                               _result.getInt ("Cuenta"));
          _list.add( stat );
          }

         }catch (SQLException en) {
             en.printStackTrace();
        }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}

public ArrayList <Estadistica> getByPlat( int _year ){
         Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();
        
                          
         try{
         PreparedStatement _ps = _conn.prepareCall( PLATAFORMA );
         _ps.setInt(1, _year);

          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
          Estadistica  stat = new Estadistica( _result.getString("pla_name"),     
                                               _result.getInt ("Cuenta"));
          _list.add( stat );
          }

         }catch (SQLException en) {
             en.printStackTrace();
        }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}

public ArrayList <Estadistica> getByArea( int _year ){
         Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();
        
                          
         try{
         PreparedStatement _ps = _conn.prepareCall( AREA );
         _ps.setInt(1, _year);

          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
          Estadistica  stat = new Estadistica( _result.getString("are_name"),     
                                               _result.getInt ("Cuenta"));
          _list.add( stat );
          }

         }catch (SQLException en) {
             en.printStackTrace();
        }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}


public ArrayList <Estadistica> getBySol( int _year ){
          Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();
            
                          
         try{
          int _cuenta = count( _year, CUENTASOL );
          PreparedStatement _ps = _conn.prepareCall( SOLUCION );
          _ps.setInt(1, _year);
     
          ResultSet _result = _ps.executeQuery();
        
          while ( _result.next() ){
            
           float _porce = (_result.getInt("Cuenta")*100) / _cuenta ;
           Estadistica  stat = new Estadistica( _result.getString("are_name"),     
                                                _porce );
          _list.add( stat );
          }

         }catch (SQLException en) {
             en.printStackTrace();
         }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}


public int count( int _year , String _query) throws SQLException{
        int _cuenta = 0;
        Connection _conn = SqlConn.getConnection();
        try{
          PreparedStatement _ps = _conn.prepareCall( _query );
          _ps.setInt(1, _year);
          ResultSet _result = _ps.executeQuery();
          while( _result.next() ){
          _cuenta = _result.getInt("Cuenta");}
          return _cuenta;
          
         }catch (SQLException en) {
             en.printStackTrace();
         }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }  
         return _cuenta;
      } 
         
       
public ArrayList <Estadistica> sol( int _year ){
         Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();

         try{
        
          PreparedStatement _ps = _conn.prepareCall( SOLUCION );
          _ps.setInt(1, _year);
          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
                      
           Estadistica  stat = new Estadistica( _result.getString("are_name"),     
                                                _result.getInt("Cuenta"),
                                                 0);
           _list.add( stat );
           }
          
         }catch (SQLException en) {
             en.printStackTrace();
         }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}   

public ArrayList <Estadistica> nosol ( int _year ){
         Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();

         try{
        
       
          PreparedStatement _ps = _conn.prepareCall( NOSOL );  
          _ps.setInt(1, _year);
          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
         
           Estadistica  stat = new Estadistica( _result.getString("are_name"),0,
                                                _result.getInt("no_sol"));
           _list.add( stat );
       
           }
          
         }catch (SQLException en) {
             en.printStackTrace();
         }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
          return _list; 
}   

}

