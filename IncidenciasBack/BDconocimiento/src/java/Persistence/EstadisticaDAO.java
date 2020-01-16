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


}

