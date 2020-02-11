/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Michele Lanza
 */
public class DAO {
    
    String CREATE_INCIDENCIA = "{ CALL addIncidencia (?,?,?,?,?,?,?) }";
    String SELECT_ALL_INCIDENCIAS = "{ CALL getAll() }" ;
    String READ_INCIDENCIA =  "{CALL getIncidencia (?) }";
    String READ_AREA = "{ CALL getArea() }";
    String READ_PLAT = "{ CALL getPlat() }";
    String UPDATE_INCIDENCIA = "{ CALL updateInc (?,?,?,?,?,?,?,?) }";
    String DELETE_INCIDENCIA = " {CALL deleteInc (?)}";
//    String INSERTDOC  = "{Call insertIncFile(?,?)}";
//    String UPDATEDOC = "{Call updateIncFile(?,?)}";

    
    String INSERTDOC = "UPDATE Incidencia SET fk_archivo_id = ?\n" +
                       "WHERE inc_name = ?";
    String UPDATEDOC = "UPDATE Incidencia SET fk_archivo_id = ?\n" +   
                       "WHERE inc_id = ?";
            
    public void createIncidencia( Incidencia _in ) throws  SQLException {
        
        Connection _conn = SqlConn.getConnection();
        PreparedStatement preparedStatement;
        LocalDate incLDate = _in.getFechaOcurrencia();
            
        try {
         
            Date date = java.sql.Date.valueOf(incLDate);
            preparedStatement = _conn.prepareCall(CREATE_INCIDENCIA);
            preparedStatement.setString( 1, _in.getNombre() );  
            preparedStatement.setString( 2, _in.getDescripcion() );
            preparedStatement.setDate( 3, date );
            preparedStatement.setInt( 6, _in.getPlat() ); 
            preparedStatement.setInt( 7, _in.getAre() ); 
          
            if ( _in.getSolDescripcion() != null ){
            LocalDate solLDate = _in.getFechaResolucion();
            Date dateS = java.sql.Date.valueOf( solLDate );
            preparedStatement.setString( 5, _in.getSolDescripcion());    
            preparedStatement.setDate( 4, dateS ); 
           
            }else {
            preparedStatement.setNull( 5, java.sql.Types.CHAR );
            preparedStatement.setNull( 4, java.sql.Types.DATE );
          
            }
           preparedStatement.execute();
        }catch (NullPointerException | SQLException en) {
            en.printStackTrace();
            throw  en;
        }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
    }
    
  
      public void updateIncidencia( Incidencia _in ) throws  SQLException {
        Connection _conn = SqlConn.getConnection();
        PreparedStatement preparedStatement = null;
         
     
        try {
          
       if ( !(_in.getSolDescripcion().equals("Por solucionar")) ){

            LocalDate incLDate = _in.getFechaOcurrencia(); 
            LocalDate solLDate = _in.getFechaResolucion();  
            
            Date date = java.sql.Date.valueOf(incLDate);
            Date dateS = java.sql.Date.valueOf(solLDate);
            
            preparedStatement = _conn.prepareCall( UPDATE_INCIDENCIA );
            preparedStatement.setString( 1, _in.getNombre() );  
            preparedStatement.setString( 2, _in.getDescripcion());
            preparedStatement.setDate( 3, date );
            preparedStatement.setDate( 4,  dateS ); 
            preparedStatement.setString( 5, _in.getSolDescripcion()); 
            preparedStatement.setInt( 6, _in.getPlat() ); 
            preparedStatement.setInt( 7, _in.getAre() ); 
            preparedStatement.setInt( 8, _in.getId());   
            
                       
            preparedStatement.execute();
            
       } else {
           
          
            Date date = java.sql.Date.valueOf( _in.getFechaOcurrencia() );
            
            preparedStatement = _conn.prepareCall( UPDATE_INCIDENCIA );
            preparedStatement.setString( 1, _in.getNombre() ); 
            preparedStatement.setString( 2, _in.getDescripcion());
            preparedStatement.setDate( 3, date );
            preparedStatement.setNull( 5, java.sql.Types.CHAR );
            preparedStatement.setNull( 4, java.sql.Types.DATE );
            preparedStatement.setInt( 6, _in.getPlat() ); 
            preparedStatement.setInt( 7, _in.getAre() ); 
            preparedStatement.setInt( 8, _in.getId());   
            preparedStatement.execute();
        }

        }catch (NullPointerException | SQLException en) {
            en.printStackTrace();
            throw  en;
        }finally{
          try {
            _conn.close();
         } catch ( SQLException e1 ) {
            e1.printStackTrace();
           } 
        }     
    }
    

     
    
     public ArrayList<Incidencia> incidenciaList() {
         Connection _conn = SqlConn.getConnection();
         ArrayList<Incidencia> _inList= new ArrayList<>();
         
      
       try {
           PreparedStatement _ps = _conn.prepareCall( SELECT_ALL_INCIDENCIAS );
           ResultSet _result = _ps.executeQuery();
              while ( _result.next() ){
               _inList.add( getIncidencia( _result ) );                
              }
               return _inList; 
        }catch ( SQLException e ) {
                e.printStackTrace();
               
        }finally{
            try {
                _conn.close();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
        return _inList;
        }
    

    public Incidencia getIncidencia( ResultSet _result ) throws SQLException {
       
        Area _area= new Area();
        Plataforma _plat = new Plataforma();
        _plat.setId(_result.getInt("pla_id"));
        _plat.setNombre(_result.getString("pla_name"));
        _area.setId(_result.getInt("are_id"));
        _area.setNombre(_result.getString("are_name"));
        String _output =  _result.getString( "inc_soldesc" );
        Timestamp _output2 = _result.getTimestamp("inc_soldate");
        int _doc = _result.getInt("fk_archivo_id");
        
      if ( _output == null && 
           _output2  == null && _doc == 0 ){
                
               _output = "Por solucionar";
          
          Incidencia _incidencia = new Incidencia(
                _result.getInt( "inc_id" ),   
                _result.getString( "inc_name" ), 
                _result.getString( "inc_description" ), 
                _result.getDate("inc_date" ).toLocalDate(),
                _output,
                _result.getInt( "are_id" ),   
                _result.getInt( "pla_id" ),
                _result.getString( "are_name" ),
                _result.getString( "pla_name" )
      ); 
         
          return _incidencia;
          
      }else if ( _output == null  && _doc != 0 ){
                
               _output = "Ver archivo adjunto";
          
          Incidencia _incidencia = new Incidencia(
                _result.getInt( "inc_id" ),   
                _result.getString( "inc_name" ), 
                _result.getString( "inc_description" ), 
                _result.getDate("inc_date" ).toLocalDate(),
                _output,
                _result.getDate("inc_soldate").toLocalDate(),
                _result.getInt( "are_id" ),   
                _result.getInt( "pla_id" ),
                _result.getString( "are_name" ),
                _result.getString( "pla_name" ),
                _doc 
      ); 
         
          return _incidencia;
      }else{
         Incidencia _incidencia = new Incidencia(
                _result.getInt( "inc_id" ),   
                _result.getString( "inc_name" ), 
                _result.getString( "inc_description" ), 
                _result.getDate( "inc_date" ).toLocalDate(),
                _result.getString("inc_soldesc"),
                _result.getDate("inc_soldate").toLocalDate(),
                _result.getInt( "are_id" ),   
                _result.getInt( "pla_id" ),
                 _result.getString( "are_name" ),
                _result.getString( "pla_name" ),
                _doc
      ); 
             return _incidencia;
      }
    
    
       }

       
        
    public ArrayList<Area> areaList (){
    ArrayList<Area> _areList = new ArrayList<>();
    Connection _conn = SqlConn.getConnection();

       try {
            PreparedStatement _ps = _conn.prepareCall( READ_AREA );
            ResultSet _result = _ps.executeQuery();
              while ( _result.next() ){
                   Area _area = new Area();
                  _area.setId(_result.getInt("are_id"));
                  _area.setNombre(_result.getString("are_name"));
                  _areList.add( _area );
 
            }
              return _areList;
             }
           catch( SQLException e ){
                     e.printStackTrace();
                    
            }finally{
            try {
                _conn.close();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
              } 
              return _areList;
}
    
         
    public ArrayList<Plataforma> platList (){
           
   
    ArrayList<Plataforma> _platList = new ArrayList<>();
    Connection _conn = SqlConn.getConnection();

       try {
            PreparedStatement _ps = _conn.prepareCall( READ_PLAT );
            ResultSet _result = _ps.executeQuery();
              while ( _result.next() ){
                   Plataforma _plat = new Plataforma();
                  _plat.setId(_result.getInt("pla_id"));
                  _plat.setNombre(_result.getString("pla_name") );
                  _platList.add( _plat );
 
            }
              return _platList;
             }
           catch( SQLException e ){
                     e.printStackTrace();
                    
            }finally{
            try {
                _conn.close();
            } catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
              } 
              return _platList;
}
    
    
    
    
    
    public Incidencia readIncidencia ( int id ){
        Connection _conn = SqlConn.getConnection();
        try{ 
            PreparedStatement _ps = _conn.prepareCall( READ_INCIDENCIA );
            _ps.setInt( 1, id );
            ResultSet _rs = _ps.executeQuery(); 
            while ( _rs.next() ){
            Incidencia _inc =   getIncidencia( _rs );          
            return _inc;
            }
        
        }catch(SQLException e ){
              e.printStackTrace();
          }finally{
            try {
                _conn.close();
            } catch ( SQLException e1 ) {
               e1.printStackTrace();
            }
              } 
        
            
        return null;
    }
    
    
    
    public int deleteIncidencia ( int id ) {  
       Connection _conn = SqlConn.getConnection();
        try{      
         PreparedStatement _ps = _conn.prepareCall( DELETE_INCIDENCIA ); 
         _ps.setInt( 1, id);
         _ps.execute();          
         return 1;
        }catch(SQLException e){          
            e.printStackTrace();
            return 0;
            }finally{
            try {
                _conn.close();
            } catch ( SQLException e1 ) {
              e1.printStackTrace();
            }
              } 
    }
    
    
  public void  InsertDocInc( int _docId, String _name ) throws  SQLException{
       Connection _conn = SqlConn.getConnection();
       try{
           PreparedStatement _ps = _conn.prepareCall( INSERTDOC );
           _ps.setInt( 1,_docId );
           _ps.setString( 2, _name );
           _ps.execute();
           
       }catch(SQLException e){
           e.printStackTrace();
           throw  e;
       }finally {
           try{
            _conn.close();
           }catch(SQLException ex){
            ex.printStackTrace();
           }
       }
       
  }

    public void  InsertDocInc( int _docId, int _name ) throws  SQLException{
       Connection _conn = SqlConn.getConnection();
       try{
           PreparedStatement _ps = _conn.prepareCall( UPDATEDOC );
           _ps.setInt( 1,_docId );
           _ps.setInt( 2, _name );
           _ps.execute();
           
       }catch(SQLException e){
           e.printStackTrace();
           throw  e;
       }finally {
           try{
            _conn.close();
           }catch(SQLException ex){
            ex.printStackTrace();
           }
       }
       
  }
    
}
