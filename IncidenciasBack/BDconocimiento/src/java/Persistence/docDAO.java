/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Michele Lanza
 */
public class docDAO {
    
    String INSERT ="";
    String SELECT ="";
    
   
   public void insert( ) {
      int len;
      Connection _conn = SqlConn.getConnection();
        try {
             // File file = new File(filename);
             // FileInputStream fis = new FileInputStream(file);
              //len = (int)file.length();
            PreparedStatement  _ps = _conn.prepareStatement(INSERT);
          //    _ps.setString(1,file.getName());
            //  _ps.setInt(2, len);
              //method to insert a stream of bytes
             // _ps.setBinaryStream(3, fis, len); 
              _ps.execute();

          } catch (Exception e) {
              e.printStackTrace();
          }finally{
              try {
                  _conn.close();
              } catch ( SQLException e1 ) {
                  e1.printStackTrace();
              }
                } 
            }
   
   public void select ( ){
       Connection _conn = SqlConn.getConnection();
       try{
       PreparedStatement _ps = _conn.prepareStatement(SELECT);
       
       
       _ps.execute();
       
       }catch(Exception e){
           e.printStackTrace();
       }finally {
           try{
            _conn.close();
           }catch(SQLException ex){
            ex.printStackTrace();
           }
       
       
       
       
       }
   
   
   
   
   }
}
