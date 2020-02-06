/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Logic.DTOIncidenciaWOS;
import Logic.DTOUpdate;
import Logic.DTOfullincidencia;
import Model.Archivo;
import Model.Area;
import Model.Incidencia;
import Model.Plataforma;
import Persistence.DAO;
import Persistence.docDAO;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import java.io.InputStream;//
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 * REST Web Service
 *
 * @author Michele Lanza
 */
@Path("/Incidencias")
public class EPincidencia {

   private final String MESSAGE_ERROR_INTERN = "Error Interno";
   Gson gson  = new Gson();
 
   
    
    @GET
    @Path("/GetALL")
    @Produces("application/json")
    public Response getAllIncidencia(){ 
      Error error;
      ArrayList<Incidencia> _incList = new ArrayList<>();
      Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try{    
        DAO _incidencia = new DAO();           
       _incList = _incidencia.incidenciaList();
       _rb.entity( gson.toJson( _incList ) );           
    }catch(Exception e){
       error = new Error( MESSAGE_ERROR_INTERN );
       return Response.status(500).entity(error).build();
    }
       return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
        
    @GET
    @Path("/GetIncidencia/{incId}")
    @Produces("application/json")
    public Response getIncidencia( @PathParam("incId") int id ){ 
      Error error;
      Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try{ 
            
        Incidencia _inc = new Incidencia();
        DAO _incidencia = new DAO();           
       _inc = _incidencia.readIncidencia(id);
       
       _rb.entity( gson.toJson( _inc ) );           
    }catch(Exception e){
       error = new Error( MESSAGE_ERROR_INTERN );
       return Response.status(500).entity(error).build();
    }
       return _rb.header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @Path("/AddFull")
    @Consumes("application/json")
    @Produces("application/json")
     public Response addIncidenciaFULL( DTOfullincidencia _dto ){   
         Error error;
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
         NewCrossOriginResourceSharingFilter hd =
                 new NewCrossOriginResourceSharingFilter();
      
        _rb.header("Access-Control-Allow-Methods","POST"); 
        _rb.header( "Access-Control-Allow-Origin","*" );
     try{                   
           
           Incidencia _inc = new Incidencia(  _dto._incNombre,
                                              _dto._incDesc, _dto._incFecha,
                                              _dto._solDesc, _dto._solFecha,
                                              _dto._idArea,  _dto._idPlat );
    
        DAO _incidencia = new DAO();
        _incidencia.createIncidencia( _inc );

     
       return _rb.header( "Access-Control-Allow-Origin","*" ).build();
        }     
     catch ( Exception e ) {
           error = new Error( MESSAGE_ERROR_INTERN );
           return Response.status(500).entity(error).build();
        }
    }
    
    
    @POST
    @Path("/AddWOS")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addIncidenciaWOS( DTOIncidenciaWOS _dto ){   
         Error error;
         Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        _rb.header("Access-Control-Allow-Methods","POST"); 
        _rb.header( "Access-Control-Allow-Origin","*" );
     try{   
      
             
        Incidencia _inc = new Incidencia(  _dto._incNombre,
                                           _dto._incDesc, _dto._incFecha, 
                                           _dto._idArea,
                                           _dto._idPlat );
        

        DAO _incidencia = new DAO();    
        _incidencia.createIncidencia( _inc );    
       


       return _rb.build();
        }     
     catch ( Exception e ) {
           error = new Error( MESSAGE_ERROR_INTERN );
           return Response.status(500).entity(error).build();
        }
    }
   
    @PUT
    @Path("/Edit/AddSol")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addSolucion( DTOUpdate _dto ) {
        
        Error error;
        Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
        try{                   
         
        if ( _dto._solDescripcion.equals("Por solucionar") ){   
    
   
        Incidencia _inc = new Incidencia( _dto._id,_dto._nombre,
                                _dto._descripcion,  _dto._solDescripcion,
                                _dto._fechaOcurrencia,  _dto._are, _dto._plat );
        
        DAO _incidencia = new DAO();
        _incidencia.updateIncidencia( _inc );
        } else {
     
        Incidencia _inc = new Incidencia(  _dto._id,_dto._nombre,
                                    _dto._descripcion, _dto._fechaOcurrencia,
                                    _dto._solDescripcion, _dto._fechaResolucion,
                                    _dto._are, _dto._plat );
 
        DAO _incidencia = new DAO();
        _incidencia.updateIncidencia( _inc );
 
        }  
       return _rb.header("Access-Control-Allow-Origin", "*").build();
        }   
        catch ( Exception e ) {
           error = new Error( MESSAGE_ERROR_INTERN );
           return Response.status(500).entity(error).build();
        }
    }

    @GET
    @Path("/GetArea")
    @Produces("application/json")
    public Response getArea (){
    Error error;
    ArrayList<Area> _areaList = new ArrayList<>();
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
    try {
        DAO _are = new DAO();
       _areaList = _are.areaList();
       _rb.entity( gson.toJson( _areaList ) );    
       
    }catch(Exception e){
        error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
       }
    
    return _rb.header("Access-Control-Allow-Origin", "*").build();
    
    }
   
    
    
    
    @GET
    @Path("/GetPlataforma")
    @Produces("application/json")
    public Response getPlataforma (){
    Error error;
    ArrayList<Plataforma> _platList = new ArrayList<>();
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
    try {
        DAO _plat = new DAO();
       _platList = _plat.platList();
       _rb.entity( gson.toJson( _platList ) );    
       
    }catch(Exception e){
        error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
       }
    
       return _rb.header("Access-Control-Allow-Origin", "*").build();
    
    }

    
   
    @DELETE     
    @Path("/DeleteInc/{incId}/{fileId}")
    @Produces("application/json")
    public Response deleteIncidencia ( @PathParam("incId") int id,
                                       @PathParam("fileId") int idF ){
    Error error;
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
    try {
            
        DAO _incidencia = new DAO();         
        int _success =  _incidencia.deleteIncidencia(id); 
        docDAO _doc = new docDAO();   
        
       if (idF != 0){
        int _success2 =  _doc.deleteFile(idF);
       }
        
       if ( _success == 1) {
           
         return _rb.header("Access-Control-Allow-Origin", "*").build();
      
       }else {
           
        error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
        
       }
       
    }catch(Exception e){
        error = new Error ( MESSAGE_ERROR_INTERN );
        return Response.status( 500 ).entity( error ).build();
       }
    }
    

       
    @POST
    @Path("/AddFile/{_incNombre}")   
    @Produces("application/json") 
    @Consumes("multipart/form-data") 
    public Response addFile( @FormDataParam("file") InputStream fis,
                        @FormDataParam("file") FormDataContentDisposition fd, 
                        @PathParam("_incNombre") String _incName ){
    Error error;
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
     
     try{
         
        String _fileName = fd.getFileName();
        docDAO _doc = new docDAO( );
        DAO _inc = new DAO();
        _doc.insert( fis, _fileName );
        int _id =  _doc.selectId( _fileName );
        _inc.InsertDocInc( _id , _incName );
     
        return _rb.header( "Access-Control-Allow-Origin","*" ).build();
        } 
     
     catch ( Exception e ) {
        error = new Error( MESSAGE_ERROR_INTERN );
        
        return Response.status(500).entity(error).build();
        }
    }
    
        
    @POST
    @Path("/updateFile/{id}")   
    @Produces("application/json") 
    @Consumes("multipart/form-data") 
    public Response updateFile( @FormDataParam("file") InputStream fis,
                        @FormDataParam("file") FormDataContentDisposition fd, 
                        @PathParam("id") int idInc ){
    Error error;
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
     
     try{
         
        String _fileName = fd.getFileName();
        docDAO _doc = new docDAO( );
        DAO _inc = new DAO();
        _doc.insert( fis, _fileName );
        int _id =  _doc.selectId( _fileName );
        _inc.InsertDocInc( _id , idInc );
    
        return _rb.header( "Access-Control-Allow-Origin","*" ).build();
        } 
     
     catch ( Exception e ) {
        error = new Error( MESSAGE_ERROR_INTERN );
        
        return Response.status(500).entity(error).build();
        }
    }
    
    

    @GET
    @Path("/DownloadFile/{id}")   
    @Produces(MediaType.APPLICATION_OCTET_STREAM) 
    public Response getFile(  @PathParam("id") int _id ){
    Error error;
    Response.ResponseBuilder _rb = Response.status( Response.Status.OK );
    Archivo _arch = new Archivo();   
    
     try{    
         
       docDAO _doc = new docDAO( );
       _arch = _doc.select( _id );
       _rb.entity( _arch.file );
       _rb.header("Access-Control-Expose-Headers", "Content-Disposition");
      
     return _rb.header( "Access-Control-Allow-Origin","*")
               .header( "Content-Disposition",
                        "attachment; filename="+_arch.nombre+"" ).build();
        }     
     catch ( Exception e ) {
       error = new Error( MESSAGE_ERROR_INTERN );
     return Response.status(500).entity(error).build();
        }
    }
 
    
    
    
    }

