/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Michele Lanza
 */
public class SqlConn {

    
   public static Connection  getConnection(){
      String db_connect_string = "jdbc:sqlserver://localhost\\BDMLP:55397"
                                + ";databaseName=bdconocimiento ";
      String db_userid  = "sa";
      String db_pass = "Berlin$1945";
      Connection _conn = null;
 
      try {
         Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
         _conn = DriverManager.getConnection( db_connect_string, 
                                              db_userid, db_pass );
     
      } catch (Exception e) {
         e.printStackTrace();
         

      }  
    return _conn;
   }

  
}

