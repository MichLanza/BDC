import { Component, OnInit } from '@angular/core';
import { ModService } from './mod-incidencia.service';
import {Incidencia} from '../../model/incidencia-model';
import {Plataforma} from '../../model/plataforma-model';
import {Area} from '../../model/area-model';
import { ToastrService } from 'ngx-toastr';
import { NgbModal, ModalDismissReasons  } from '@ng-bootstrap/ng-bootstrap';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-mod-incidencia',
  templateUrl: './mod-incidencia.component.html',
  styleUrls: ['./mod-incidencia.component.scss']
})
export class ModIncidenciaComponent implements OnInit {

  newIncidencia: Incidencia = new Incidencia;
  area: any = [];
  plat: any = [];
  fecha: any;
  plataforma: any = [];
  private vacio: boolean;
  public areaList = Array<Area>();
  public platList = Array<Plataforma>();
  public toggle :  boolean = true;
  fileToUpload: File;
  formData = new FormData();

  constructor(public modService: ModService, public toast: ToastrService, public modalService: NgbModal ) {   

    modService.getArea().subscribe(data => {
    this.area = data;

    modService.getPlataforma().subscribe(data => {
      this.plataforma = data;
    });
     });

     modService.getIncidencia().subscribe(data => {
       this.newIncidencia = data
     });
  
     }

  ngOnInit() {
     

    this.modService.getArea().subscribe((data) => {
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

      this. modService.getPlataforma().subscribe((data) => {
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


        this.modService.getIncidencia().subscribe((data) => {
          this.vacio = true;
          this.newIncidencia = data;
          console.log(this.newIncidencia);    
          this.dateIncTransform(data);
          this.fechaTransform(data);
          

          if (data._fechaResolucion != null ){
            this.datesolTransform(data);
            this.fechaSolTransform(data);

          }

          console.log(this.newIncidencia);
          localStorage.setItem('incID', data._id);
          if (this.platList.length > 0) {
          this.vacio = false;
          }
          }, (err) => {
          console.log(err);

        


        })

  }
 
   
    modIncidencia(){
      
     console.log(this.newIncidencia);
      console.log(this.fileToUpload);
  
        if(  (this.newIncidencia._nombre != "" ) && (this.newIncidencia._nombre != null ) &&
              ( this.newIncidencia._descripcion != null  ) &&
              (this.newIncidencia._fechaOcurrencia != null ) && (this.newIncidencia._are != null)&&
              (this.newIncidencia._plat != null) && (this.newIncidencia._solDescripcion != "") && 
              (this.newIncidencia._fechaResolucion != null)  && !(this.newIncidencia._fechaResolucion == "" ) && 
              (this.fileToUpload == null)  ) {

          if ((this.newIncidencia._fechaOcurrencia) <= (this.newIncidencia._fechaResolucion)){   
          this.modService.updateIncidencia(this.newIncidencia).toPromise().then(res =>{
          console.log(this.newIncidencia);
          this.toast.success("Se han modificado los datos con éxito");
          });
        

           }else 
             this.toast.error("La fecha de solución no puede ser menor a la de ocurrencia");
  
  } else if ( (this.newIncidencia._nombre != null ) && ( this.newIncidencia._descripcion != null  ) &&
              (this.newIncidencia._fechaOcurrencia != null ) && (this.newIncidencia._are != null)&&
              (this.newIncidencia._plat != null ) && (this.newIncidencia._solDescripcion == "Por solucionar") &&
              (this.newIncidencia._fechaResolucion == null) && !(this.newIncidencia._fechaResolucion == "" ) && 
              (this.fileToUpload == null) ){

      console.log("hola");
      this.modService.updateIncidencia(this.newIncidencia).toPromise().then(res =>{
      console.log(this.newIncidencia);
        
      });
     this.toast.success("Se han modificado los datos con éxito");

  } else if ( (this.newIncidencia._nombre != null ) && ( this.newIncidencia._descripcion != null  ) &&
              (this.newIncidencia._fechaOcurrencia != null ) && (this.newIncidencia._are != null) &&
              (this.newIncidencia._plat != null )   && (this.newIncidencia._fechaResolucion != null )  &&
              (this.fileToUpload != null) && (this.newIncidencia._fechaResolucion != "" ) &&
              (this.newIncidencia._solDescripcion != "" )  ){
                
      console.log("hola 2");
      if( (this.newIncidencia._solDescripcion == "Por solucionar" ) ) {
      this.newIncidencia._solDescripcion  = "Ver archivo adjunto";
      }
      this.modService.updateIncidencia(this.newIncidencia).toPromise().then(res =>{
      console.log(this.newIncidencia);
      this.toast.success("Se ha modificado la incidencia con éxito");
      this.newIncidencia = new Incidencia();
  });
      console.log(this.formData.get('file'));
      this.modService.addFile( this.formData  ).toPromise().then(res =>{
        console.log(this.newIncidencia);
  
        this.newIncidencia = new Incidencia();
    });
  
    
  
  } else {

      this.toast.error("Hubo un error, algún campo está vacío");
      this.toggle = !this.toggle;
    }
    
    }

  
    

 dateIncTransform(data:any){

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

 datesolTransform(data:any){

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

 fechaTransform(data:any){
  console.log(data._fechaOcurrencia);
  this.fecha = data._fechaOcurrencia.year +"-"+data._fechaOcurrencia.month+"-"+data._fechaOcurrencia.day
  data._fechaOcurrencia = this.fecha
  console.log(this.fecha)

 }

 fechaSolTransform(data:any){
  console.log(data._fechaResolucion);
  this.fecha = data._fechaResolucion.year +"-"+data._fechaResolucion.month+"-"+data._fechaResolucion.day
  data._fechaResolucion = this.fecha
  console.log(this.fecha)
 }


 enabledMode(){
  this.toggle = !this.toggle;

}

//probar elminando esto
closeResult: string;

delete(){
let id = this.newIncidencia._idFile;
console.log(localStorage.incID);
this.modService.deleteIncidencia(id).toPromise().then(res =>  console.log('eliminando') );
this.toast.success("Se ha eliminado la incidencia con éxito");

}

open(content: any){
 this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'})
}


addarchivo( event ){

  this.fileToUpload = event.target.files[0]
  console.log(this.fileToUpload );
  this.formData.append('file', this.fileToUpload ,this.fileToUpload.name);

 }
   


downloadFile(){

    let id =   this.newIncidencia._idFile;
    this.modService.download( id ).subscribe((res) => {
    const data = res.body
    const contentDisposition = res.headers.get('content-disposition');
    const fileName = contentDisposition.split(';')[1].split('filename')[1].split('=')[1].trim();  
    const blob = new Blob( [data], { type: 'application/octet-stream' } ); 
    saveAs( blob,fileName);
  })

}



}
