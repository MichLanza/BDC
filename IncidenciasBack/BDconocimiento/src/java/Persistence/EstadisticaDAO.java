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
import javax.ws.rs.core.Response;

/**
 *
 * @author Michele Lanza
 */
public class EstadisticaDAO {
    String ANUAL = "select  DATENAME(MONTH,I.inc_date) as Mes,\n"+
                    "count(I.inc_id) as Cuenta from Incidencia as I where\n"+
                    "YEAR(I.inc_date ) = ? Group By  MONTH(I.inc_date),\n"+
                    "DATENAME(MONTH,I.inc_date)"; 
    
    
    String PLATAFORMA = "select count(I.inc_id) as cuenta, P.pla_name  \n" +
                        "from Incidencia as I, Plataforma as P \n" +
                        "where YEAR(I.inc_date ) = ? and I.fk_plataforma_id = P.pla_id\n" +
                        "Group By  P.pla_name\n" +
                        "Order BY  count(I.inc_id) DESC";

    String AREA =  "select count(I.inc_id) as cuenta, A.are_name  \n" +
                   "from Incidencia as I, Area as A \n" +
                   "where YEAR(I.inc_date ) = ? and  I.fk_area_id = A.are_id\n"+
                   "Group By  A.are_name\n" +
                   "Order BY  count(I.inc_id) DESC  ";
    
    
    String SOLUCION = "select count(I.inc_id) as cuenta, A.are_name  \n" +
                      "from Incidencia as I, Area as A \n" +
                      "where I.fk_area_id = A.are_id AND inc_soldesc is not null and  DATEPART(year,inc_date ) = ?\n" +
                      "Group By  A.are_name  \n" +
                      "Order BY  count(I.inc_id) DESC";
    String NOSOL =    "";

    String CUENTA = "select count (I.inc_id) as cuenta\n" +
                    "from Incidencia as I \n" +
                    "where   DATEPART(year,inc_date ) = ?";
    
    String CUENTASOL = "select count(I.inc_id) as cuenta\n" +
                       "from Incidencia as I, Area as A \n" +
                       "where I.fk_area_id = A.are_id AND inc_soldesc is not null and  DATEPART(year,inc_date ) = ?";

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
          int _cuenta = count( _year );
          PreparedStatement _ps = _conn.prepareCall( SOLUCION );
          _ps.setInt(1, _year);
     
          ResultSet _result = _ps.executeQuery();
          System.out.println(_cuenta);
          while ( _result.next() ){
            
           float _porce = (_result.getInt("Cuenta")*100) / _cuenta ;
              System.out.println(_result.getInt("Cuenta"));
           System.out.println(_porce);
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

public int count( int _year ) throws SQLException{
        int _cuenta = 0;
        Connection _conn = SqlConn.getConnection();
         try{
          PreparedStatement _ps = _conn.prepareCall( CUENTASOL );
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
         
       
        

}

