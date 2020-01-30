import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';



//const endpoint = 'http://localhost:8080/BDconocimiento/Incidencias/';
const endpoint = 'http://10.60.102.103:8080/BDconocimiento/Incidencias/';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-Type':  'application/json'
  })};

@Injectable({
  providedIn: 'root'
})


export class AddIncidenciaService {

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  
  addIncidencia (incidencia): Observable<any> {
    console.log(incidencia)
    return this.http.post<any>(endpoint + 'AddFull',incidencia).pipe(
      tap((incidencia) => console.log(`Incidencia added w/`)),
    );
  }

  addIncidenciaWOS (incidencia): Observable<any> {
    console.log(incidencia)
    return this.http.post<any>(endpoint + 'AddWOS',incidencia).pipe(
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

  addFile (file, name): Observable<any> {

    return this.http.post<any>(endpoint + 'AddFile/' + name ,file).pipe(
      tap((file) => console.log('archivo subido')),
    );
  }

}

