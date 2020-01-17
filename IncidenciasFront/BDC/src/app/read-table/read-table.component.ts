import { Component, OnInit } from '@angular/core';
import {Incidencia} from '../../model/incidencia-model';
import {Plataforma} from '../../model/plataforma-model';
import {Area} from '../../model/area-model';
import {ReadTableService} from './read-table.service';



@Component({
  selector: 'app-read-table',
  templateUrl: './read-table.component.html',
  styleUrls: ['./read-table.component.scss']
})


export class ReadTableComponent implements OnInit {
  

  newIncidencia: Incidencia = new Incidencia;
  nome:string;
  area: any = [];
  plat: any = [];
  plataforma: any = [];
  private areaList = Array<Area>();
  private platList = Array<Plataforma>();
  inc: any= [];
  fecha: any;
  incList = Array <any>();
  private vacio: boolean;
  page = 1;
  pageSize = 5;
  
  ngOnInit() {

      this.tableService.getAll().subscribe((data) => {
      this.vacio = true;
      this.incList = data;
      this.inc = this.incList;
     

      data.forEach(element => {
        
      this.incDateTrans(element);
      this.fechaTransform(element);
      
      
      if (element._fechaResolucion != null ){
        let status = {status: "Por solucionar" };
        this.datesolTransform(element);
        this.fechaSolTransform(element);
        //this.incList[i].push( status )
      }
      });
      
      console.log(this.incList);


  
      localStorage.setItem('incID', data._incId);
      if ( this.incList.length > 0 ) {
      this.vacio = false;
      }
      }, (err) => {
      console.log(err);
      })

      this. tableService.getArea().subscribe((data) => {
        this.vacio = true;
        this.areaList = data;
        console.log(this.areaList);
        localStorage.setItem('areID', data._nombre);
        if (this.areaList.length > 0) {
        this.vacio = false;
        }
        }, (err) => {
        console.log(err);
        })
  
        this. tableService.getPlataforma().subscribe((data) => {
          this.vacio = true;
          this.platList = data;
          console.log(this.platList);
          localStorage.setItem('platID', data._nombre);
          if (this.platList.length > 0) {
          this.vacio = false;
          }
          }, (err) => {
          console.log(err);
          })


          
    
  }
  constructor( public tableService: ReadTableService ) {
    tableService.getAll().subscribe(data => {
      this.inc = data
   });
  }


  idIncidencia (id: any){
    console.log(id);
    localStorage.setItem('incID', id);
    console.log(localStorage);
  } 

  fechaTransform(data:any){
    this.fecha = data._fechaOcurrencia.year +"-"+data._fechaOcurrencia.month+"-"+data._fechaOcurrencia.day;
    data._fechaOcurrencia = this.fecha;

  
   }
  
   fechaSolTransform(data:any){
    this.fecha = data._fechaResolucion.year +"-"+data._fechaResolucion.month+"-"+data._fechaResolucion.day;
    data._fechaResolucion = this.fecha;

   }
  
  
  



  incDateTrans(data: any){
 
  if ( (data._fechaOcurrencia.day.toString().length == 1 ) ){
    data._fechaOcurrencia.day = "0"+ data._fechaOcurrencia.day;
    this.newIncidencia = data;

  }  if ( (data._fechaOcurrencia.month.toString().length == 1 )){
    data._fechaOcurrencia.month = "0"+ data._fechaOcurrencia.month;  
    this.newIncidencia = data;
  

  }  if ( (data._fechaOcurrencia.month.toString().length == 1 ) &&
              (data._fechaOcurrencia.day.toString().length == 1 ) ){
    data._fechaOcurrencia.day = "0"+ data._fechaOcurrencia.day;
    data._fechaOcurrencia.month = "0"+ data._fechaOcurrencia.month;  
    this.newIncidencia = data;

  }
  


}  
  

datesolTransform(data: any){

  if ( (data._fechaResolucion.day.toString().length == 1 ) ){
      data._fechaResolucion.day = "0"+ data._fechaResolucion.day;
     this.newIncidencia = data;
  
    }  if ( (data._fechaResolucion.month.toString().length == 1 )){
      data._fechaResolucion.month = "0"+ data._fechaResolucion.month;  
      this.newIncidencia = data;
    
  
    }  if ( (data._fechaResolucion.month.toString().length == 1 ) &&
                (data._fechaResolucion.day.toString().length == 1 ) ){
      data._fechaResolucion.day = "0"+ data._fechaResolucion.day;
      data._fechaResolucion.month = "0"+ data._fechaResolucion.month;  
      this.newIncidencia = data;
  
    }
  
  
   }


}
