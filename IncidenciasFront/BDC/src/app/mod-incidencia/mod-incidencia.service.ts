import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';


//const endpoint = 'http://localhost:8080/BDconocimiento/Incidencias/';
const endpoint = 'http://10.60.102.103:8080/BDconocimiento/Incidencias/';



const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-Type':  'application/json',
   
  })};

@Injectable({
  providedIn: 'root'
})
export class ModService {

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  updateIncidencia (incidencia): Observable<any> {
    console.log(incidencia)
    return this.http.put<any>(endpoint + 'Edit/AddSol',incidencia).pipe(
      tap((incidencia) => console.log(`Incidencia added w/`)),
    );
  }
  getArea(): Observable<any> {
    return this.http.get(endpoint + 'GetArea').pipe(
      map(this.extractData));
  }

  getPlataforma(): Observable<any> {
    return this.http.get(endpoint + 'GetPlataforma').pipe(
      map(this.extractData));
  }

  getIncidencia(): Observable<any>{


    return this.http.get(endpoint+ 'GetIncidencia/'+localStorage.getItem('incID'))
    .pipe(map(this.extractData));
  }

  deleteIncidencia(id): Observable<any>{

    return this.http.delete(endpoint + 'DeleteInc/'+localStorage.getItem('incID')+'/'+id)
    .pipe(map(this.extractData));
    
  }
  
  addFile (file): Observable<any> {
    console.log(localStorage.getItem('incID'))
    return this.http.post<any>(endpoint + 'updateFile/'+localStorage.getItem('incID') ,file).pipe(
      tap((file) => console.log('archivo subido')),
    );
  }


  download(id):Observable<any>{
    return  this.http.get(endpoint +'DownloadFile/'+ id, {
       observe: 'response',
       responseType: 'blob' });
        
      
    
  }
  
  

}
