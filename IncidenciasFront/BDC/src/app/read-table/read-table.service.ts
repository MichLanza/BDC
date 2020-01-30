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

export class ReadTableService {

  constructor(private http: HttpClient) { }

  
  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

getAll(): Observable<any>{

  return this.http.get(endpoint + 'GetALL').pipe(
    map(this.extractData));
}
getArea(): Observable<any> {
  return this.http.get(endpoint + 'GetArea').pipe(
    map(this.extractData));
}

getPlataforma(): Observable<any> {
  return this.http.get(endpoint + 'GetPlataforma').pipe(
    map(this.extractData));
}




}
