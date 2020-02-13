import { Component, OnInit } from '@angular/core';
import { AddIncidenciaService } from './add-incidencia.service';
import {Incidencia} from '../../model/incidencia-model';
import {Plataforma} from '../../model/plataforma-model';
import {Area} from '../../model/area-model';
import { ToastrService } from 'ngx-toastr';




@Component({
  selector: 'app-add-inc',
  templateUrl: './add-inc.component.html',
  styleUrls: ['./add-inc.component.scss']
})
export class AddIncComponent implements OnInit {
 
  newIncidencia: Incidencia = new Incidencia;
  area: any = [];
  plat: any = [];
  plataforma: any = [];
  private vacio: boolean;
  areaList = Array<Area>();
  platList = Array<Plataforma>();
  fileToUpload: File;
  formData = new FormData();
  

  ngOnInit() {
    
    this. addService.getArea().subscribe((data) => {
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

      this. addService.getPlataforma().subscribe((data) => {
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
  constructor(public addService: AddIncidenciaService, private toastr: ToastrService  ) {   
    addService.getArea().subscribe(data => {
    this.area = data;
    addService.getPlataforma().subscribe(data => {
      this.plataforma = data;
    });
     });
     }
  
  addIncidencia(){
    
  /*this.newIncidencia._incNombre ="error"
    this.newIncidencia._incDesc = "error"
    this.newIncidencia._incFecha = "2020-02-11" 
    this.newIncidencia._idArea = 1
    this.newIncidencia._idPlat = 3
    this.newIncidencia._solDesc = "error"
    this.newIncidencia._solFecha =   "2020-02-11"*/

        if (  (this.newIncidencia._incNombre != null ) && ( this.newIncidencia._incDesc != null  ) &&
              (this.newIncidencia._incFecha != null ) && (this.newIncidencia._idArea != null)&&
              (this.newIncidencia._idPlat != null) && (this.newIncidencia._solDesc != null) &&
              (this.newIncidencia._solFecha != null) && (this.fileToUpload == null) && 
              (this.newIncidencia._solDesc != "")) {
         
          if ((this.newIncidencia._incFecha) <= (this.newIncidencia._solFecha))
        {
          var IDate =  new Date(this.newIncidencia._incFecha);
          var SDate =  new Date(this.newIncidencia._solFecha);
        
          this.newIncidencia._incFecha = IDate.toISOString().slice(0,10);
          this.newIncidencia._solFecha = SDate.toISOString().slice(0,10);
          this.addService.addIncidencia(this.newIncidencia).toPromise().then(res =>{
            if( res == null ) {  
              this.toastr.success("Se ha añadido la incidencia con éxito");
              }
            console.log(this.newIncidencia);
            this.newIncidencia = new Incidencia();
        });
         }
         else this.toastr.error("La fecha de solución no puede ser menor a la de ocurrencia");

  } else if ( (this.newIncidencia._incNombre != null ) && (this.newIncidencia._incDesc != null  ) &&
              (this.newIncidencia._incFecha != null ) && (this.newIncidencia._idArea != null)&&
              (this.newIncidencia._idPlat != null )  && (this.newIncidencia._solDesc == null) &&
              (this.newIncidencia._solFecha == null) && (this.fileToUpload == null) ){

            var IDate =  new Date(this.newIncidencia._incFecha);
            this.newIncidencia._incFecha = IDate.toISOString().slice(0,10);
            this.addService.addIncidenciaWOS(this.newIncidencia).toPromise().then(res =>{
              console.log(res);
            if( res == null ) {  
            this.toastr.success("Se ha añadido la incidencia con éxito");
            }
        
            console.log(this.newIncidencia);
            this.newIncidencia = new Incidencia();
        });
 

  } else if ( (this.newIncidencia._incNombre != null ) && ( this.newIncidencia._incDesc != null  ) &&
              (this.newIncidencia._incFecha != null ) && (this.newIncidencia._idArea != null)&&
              (this.newIncidencia._idPlat != null )  && (this.newIncidencia._solFecha != null) &&
              (this.fileToUpload != null)  ){
            
          if ((this.newIncidencia._incFecha) <= (this.newIncidencia._solFecha))
          {   
                var IDate =  new Date(this.newIncidencia._incFecha);
                var SDate =  new Date(this.newIncidencia._solFecha);
                if ((this.newIncidencia._solDesc == null) || (this.newIncidencia._solDesc == ""))
                {
                this.newIncidencia._solDesc  = "Ver archivo adjunto";
                }
                this.newIncidencia._incFecha = IDate.toISOString().slice(0,10);
                this.newIncidencia._solFecha = SDate.toISOString().slice(0,10);
                this.addService.addIncidencia(this.newIncidencia).toPromise().then(res =>{
                console.log(this.newIncidencia);
                if( res == null ) {  
                  this.toastr.success("Se ha añadido la incidencia con éxito");
                  }
                this.newIncidencia = new Incidencia();
             });
          console.log(this.formData.get('file'));
          this.addService.addFile( this.formData, this.newIncidencia._incNombre  ).subscribe();
         // 
        }
        else this.toastr.error("La fecha de solución no puede ser menor a la de ocurrencia");

    } else 
          this.toastr.error("Hubo un error, algún campo está vacío");

  }

 
  
  addarchivo( event ){

   this.fileToUpload = event.target.files[0]
   console.log(this.fileToUpload );
  
   this.formData.append('file', this.fileToUpload ,this.fileToUpload.name);

  }
    

  }
