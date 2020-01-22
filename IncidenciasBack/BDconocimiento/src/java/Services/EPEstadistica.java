/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Estadistica;
import Persistence.EstadisticaDAO;
import java.util.ArrayList;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PUT;
//import javax.ws.rs.core.MediaType;
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
        
     ArrayList<Estadistica> _list = new ArrayList<>();
     Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
     Error error;                         
     try{
     EstadisticaDAO _stat = new EstadisticaDAO();
     _list = _stat.getByYear( _year );         
     _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    
  @GET
    @Path("/byplat/{year}")
    @Produces("application/json")
    public Response statPlat ( @PathParam("year") int _year ){
        
         ArrayList<Estadistica> _list = new ArrayList<>();
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;                 
         try{
         EstadisticaDAO _stat = new EstadisticaDAO();
         _list = _stat.getByPlat( _year );      
         _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    
    @GET
    @Path("/byare/{year}")
    @Produces("application/json")
    public Response statArea ( @PathParam("year") int _year  ){
        
         ArrayList<Estadistica> _list = new ArrayList<>();
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;                 
         try{
         EstadisticaDAO _stat = new EstadisticaDAO();
         _list = _stat.getByArea( _year );            
         _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    
    
    @GET
    @Path("/bysol/{year}")
    @Produces("application/json")
    public Response statSol ( @PathParam("year") int _year  ){
        
         ArrayList<Estadistica> _list = new ArrayList<>();
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;                 
         try{
         EstadisticaDAO _stat = new EstadisticaDAO();
         _list = _stat.getBySol( _year );            
         _rb.entity( gson.toJson( _list ) );
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    @GET
    @Path("/status/{year}")
    @Produces("application/json")
    public Response statNoSol ( @PathParam("year") int _year  ){
        
         ArrayList<Estadistica> _list ;
         ArrayList<Estadistica> _list2 ;

         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         Error error;                 
         try{
         EstadisticaDAO _stat = new EstadisticaDAO();
         _list = _stat.sol(_year );  
         _list2 = _stat.nosol(_year);
         //
         for (int i = 0; i < _list.size(); i++) {
          for (int j = 0; j < _list2.size(); j++){
          System.out.println(_list.get(i).x +""+_list2.get(j).x );
           if(_list.get(i).x.equals(_list2.get(j).x)){
               _list.get(i).yS = _list2.get(j).yS;
               _list2.remove(j);
           }       
              }
        
             }
         _list.addAll(_list2);
         _rb.entity( gson.toJson( _list ) ); 
        
    }catch(Exception e){
         error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
    }
     return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    
    

    
    
}
