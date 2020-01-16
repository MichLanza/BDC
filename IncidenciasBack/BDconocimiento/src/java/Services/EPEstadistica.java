/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Estadistica;
import Persistence.EstadisticaDAO;
import Persistence.SqlConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import javax.ws.rs.PathParam;


/**
 * REST Web Service
 *
 * @author Michele Lanza
 */
@Path("Stats")
public class EPEstadistica {
    
   private final String MESSAGE_ERROR_INTERN = "Error Interno";
   Gson gson  = new Gson();
    
    
    /**
     * Creates a new instance of EPEstadistica
     */
    public EPEstadistica() {
    }

 
    @GET
    @Path("/mensual/{year}")
    @Produces("application/json")
    public Response statMonth ( @PathParam("year") int _year ){
        
         Connection _conn = SqlConn.getConnection();
         ArrayList<Estadistica> _list = new ArrayList<>();
         //EstadisticaDAO _stat = new EstadisticaDAO();
         //_list = _stat.getByYear(_year);
         
         String PRUEBA = "select  DATENAME(MONTH,I.inc_date) as Mes,\n"+
                      "count(I.inc_id) as Cuenta from Incidencia as I where\n"+
                      "YEAR(I.inc_date ) = ? Group By  MONTH(I.inc_date),\n"+
                      "DATENAME(MONTH,I.inc_date)"; 

         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;
                          
         try{
         PreparedStatement _ps = _conn.prepareCall( PRUEBA );
         _ps.setInt(1, _year);

          ResultSet _result = _ps.executeQuery();
          while ( _result.next() ){
            Estadistica  stat = new Estadistica( _result.getString("Mes"),     
                                                 _result.getInt ("Cuenta"));
              _list.add( stat );
           
                 
             }
             
        _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    
 /*   @GET
    @Path("/mensual/{year}")
    @Produces("application/json")
    public Response statMonth2 ( @PathParam("month") int _month ){
        
         ArrayList<Estadistica> _list = new ArrayList<>();
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;                 
         try{
         //EstadisticaDAO _stat = new EstadisticaDAO();
         //_list = _stat.getBymonth( _year );            
         _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }*/
    
    
    
    
    
    
    
    
   /* 
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    */
    
    
    
}
