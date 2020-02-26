import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {Incidencia} from '../../model/incidencia-model';
import {Plataforma} from '../../model/plataforma-model';
import {Area} from '../../model/area-model';
import {ReadTableService} from './read-table.service';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';

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
  areaList = Array<Area>();
  platList = Array<Plataforma>();
  inc: any= [];
  fecha: any;
  incList = Array <any>();
  private vacio: boolean;
  page = 1;
  pageSize = 5;
  FechaInicio: Date;
  FechaFin: Date;
  ar: Area;
  pla;
  sol: any;
  
  ngOnInit() {

      this.tableService.getAll().subscribe((data) => {
      this.vacio = true;
      this.incList = data;
      this.inc = this.incList;
 

      data.forEach(element => {
      
      this.incDateTrans(element);
      this.fechaTransform(element);
      
      
      
      if ( (element._fechaResolucion != null )  ){

        element["status"] = "Solucionado"
        this.datesolTransform(element);
        this.fechaSolTransform(element);
      
      } else {

      element["status"] = "Por solucionar"}
   
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
    this.fecha = data._fechaOcurrencia.day +"-"+data._fechaOcurrencia.month+"-"+data._fechaOcurrencia.year;
    data._fechaOcurrencia = this.fecha;

  
   }
  
   fechaSolTransform(data:any){
    this.fecha = data._fechaResolucion.day +"-"+data._fechaResolucion.month+"-"+data._fechaResolucion.year;
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

   fileType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
   fileExtension = '.xlsx';
   newJson = Array <any>();

  public exportExcel(): void {
    this.newJsonBuild();
    let fileName = 'Datos exportados'
    const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet( this.newJson);
    const wb: XLSX.WorkBook = { Sheets: { 'data': ws }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
    this.saveExcelFile(excelBuffer, fileName);
  }

  private saveExcelFile(buffer: any, fileName: string): void {
    const data: Blob = new Blob([buffer], {type: this.fileType});
    FileSaver.saveAs(data, fileName + this.fileExtension);
    this.newJson = [];
  }

  private  newJsonBuild(){
   
      this.incList.forEach( element=>{
      this.newJson.push({'ID':element._id,
                         'Nombre': element._nombre,
                         'Descripción': element._descripcion,
                         'Fecha Ocurrencia' : element._fechaOcurrencia,
                         'Descripcion de la solución': element._solDescripcion,
                         'Fecha Resolución' : element._fechaResolucion,
                         'Area': element._areName,
                         'Plataforma': element._plName,
                         'Estatus':element.status})
       });
     /* this.newJson = this.newJson.filter(singleItem =>
        singleItem["campo"].toLowerCase().includes(filtro.toLowerCase())
      ); 
       console.log( this.newJson);*/
  }
}
