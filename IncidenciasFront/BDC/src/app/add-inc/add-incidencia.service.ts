import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';


//const endpoint = 'http://localhost:8080/BDconocimiento/Incidencias/';
const endpoint = 'http://10.60.102.103:8080/BDconocimiento/Incidencias/';

  const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin':'*'});
const options = { headers: headers };


@Injectable({
  providedIn: 'root'
})


export class AddIncidenciaService {

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  
  addIncidencia (incidencia): Observable<any> {
    console.log(incidencia)
    return this.http.post<any>(endpoint + 'AddFull',incidencia ).pipe(
        catchError((err: HttpErrorResponse) => of (
        this.toastr.error("Ocurrió un error ")
      ))
    );
  }

  addIncidenciaWOS (incidencia): Observable<any> {
  
   console.log(incidencia)
    return this.http.post<any>(endpoint + 'AddWOS',incidencia ).pipe(
      catchError((err: HttpErrorResponse) => of (
      this.toastr.error("Ocurrió un error ")
    ))
  )
  }
 
  getArea(): Observable<any> {
    return this.http.get(endpoint + 'GetArea').pipe(
      map(this.extractData));
  }

  getPlataforma(): Observable<any> {
    return this.http.get(endpoint + 'GetPlataforma').pipe(
      map(this.extractData));
  }

  addFile (file, name): Observable<any> {

    return this.http.post<any>(endpoint + 'AddFile/' + name ,file).pipe(
      catchError((err: HttpErrorResponse) => of (
      this.toastr.error("Ocurrió un error ")
    ))
  )
  }

}

