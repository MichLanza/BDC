/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.Archivo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michele Lanza
 */
public class docDAO {
    
    String INSERT ="INSERT INTO Archivo(arch_name,arch_file) VALUES (?,?)";
    String SELECT ="SELECT arch_file, arch_name from Archivo where arch_id= ?";
    String SELECT_ID ="SELECT arch_id from Archivo where arch_name like ?";
   
    public void insert( InputStream file , String _filename) {
   
      Connection _conn = SqlConn.getConnection();
        try {
           
           PreparedStatement  _ps = _conn.prepareStatement( INSERT );    
           _ps.setString(1,_filename);
           _ps.setBinaryStream(2, file); 
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
   
   public Archivo select (int _id){
       Connection _conn = SqlConn.getConnection();
       InputStream input = null;
       FileOutputStream output = null;
       Archivo _arch = new Archivo();
       try{
       PreparedStatement _ps = _conn.prepareStatement( SELECT );
       _ps.setInt(1, _id);
       ResultSet rs  = _ps.executeQuery();
       
          while (rs.next()) {
          String _filename = rs.getString("arch_name");
          input = rs.getBinaryStream("arch_file");
          _arch.file = input;
          _arch.nombre = _filename;
          return _arch;
//          output =  new FileOutputStream( new File 
//          ("C:\\"+_filename));
//          int r = 0;
//          while ((r = input.read()) != -1) {
//          output.write(r);
//            }
//          output.close();
        
            }
      
       
       }catch(Exception e){
           e.printStackTrace();
       }finally {
           try{
            _conn.close();
           }catch(SQLException ex){
            ex.printStackTrace();
           }
       }
       return _arch;
   }
   
    public int selectId (String _filename ){
       Connection _conn = SqlConn.getConnection();
       int _id = 0;          
       try{
        PreparedStatement _ps = _conn.prepareStatement( SELECT_ID );
        _ps.setString(1, _filename);
        ResultSet _rs  = _ps.executeQuery();
          while (_rs.next()) {
            _id = _rs.getInt( "arch_id" );
            }
          return _id;   
       }catch(Exception e){
           e.printStackTrace();
       }finally {
           try{
            _conn.close();
           }catch(SQLException ex){
            ex.printStackTrace();
           }
       }
   return _id; 
   }
       

  
       
 
   
   
   
   
}
