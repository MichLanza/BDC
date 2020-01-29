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
  //areaList: any;
  plataforma: any = [];
  //platList: any;
  private vacio: boolean;
  private areaList = Array<Area>();
  private platList = Array<Plataforma>();

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
    if( (this.newIncidencia._incNombre != null ) && ( this.newIncidencia._incDesc != null  ) &&
        (this.newIncidencia._incFecha != null ) && (this.newIncidencia._idArea != null)&&
         (this.newIncidencia._idPlat != null) && (this.newIncidencia._solDesc != null) &&
          (this.newIncidencia._solFecha != null)) {

          var IDate =  new Date(this.newIncidencia._incFecha);
          var SDate =  new Date(this.newIncidencia._solFecha);
        
          this.newIncidencia._incFecha = IDate.toISOString().slice(0,10);
          this.newIncidencia._solFecha = SDate.toISOString().slice(0,10);
          this.addService.addIncidencia(this.newIncidencia).toPromise().then(res =>{
          console.log(this.newIncidencia);

          this.newIncidencia = new Incidencia();
       });
       this.toastr.success("Se ha añadido la incidencia con éxito");


  } else if ( (this.newIncidencia._incNombre != null ) && ( this.newIncidencia._incDesc != null  ) &&
              (this.newIncidencia._incFecha != null ) && (this.newIncidencia._idArea != null)&&
              (this.newIncidencia._idPlat != null ) && (this.newIncidencia._solDesc == null) &&
              (this.newIncidencia._solFecha == null)){

      console.log("hola");
      var IDate =  new Date(this.newIncidencia._incFecha);
      this.newIncidencia._incFecha = IDate.toISOString().slice(0,10);
      this.addService.addIncidenciaWOS(this.newIncidencia).toPromise().then(res =>{
      console.log(this.newIncidencia);
      this.newIncidencia = new Incidencia();
      });
      this.toastr.success("Se ha añadido la incidencia con éxito");

  } else 
      this.toastr.error("Hubo un error, algún campo está vacío");

    
  }

  fileToUpload: File;
  
  addarchivo( event ){
   this.fileToUpload = event.target.files[0]
   console.log(this.fileToUpload );
   const formData = new FormData();
   formData.append('file', this.fileToUpload ,this.fileToUpload.name);
   console.log(formData.get('file'));
   this.addService.addFile( formData ).subscribe(  res => {
    console.log (res)
    });
  
  }
    



  }
