import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';


const endpoint = 'http://localhost:8080/BDconocimiento/Stats';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-Type':  'application/json'
  })};


@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private http: HttpClient) { }



  private extractData( res: Response ) {
    let body = res;
    return body || { };
  }

  getStats(year: any): Observable<any>{
    return this.http.get(endpoint+ '/mensual/'+year,httpOptions).
    pipe(map(this.extractData));
  }

}
