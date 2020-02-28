import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';


//const endpoint = 'http://localhost:8080/BDconocimiento/Stats';
const endpoint = 'http://10.60.102.103:8080/BDconocimiento/Stats';

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
    return this.http.get(endpoint+ '/mensual/'+year ).pipe(
      map(this.extractData));
  }
  
  getStatsByPlat(year: any): Observable<any>{
    return this.http.get( endpoint + '/byplat/'+year ).pipe(
      map(this.extractData));
  }
  getStatsByArea(year: any): Observable<any>{
    return this.http.get( endpoint + '/byare/'+year ).pipe(
      map(this.extractData));
  }

  getStatsBySol(year: any): Observable<any>{
    return this.http.get( endpoint + '/bysol/'+year ).pipe(
      map(this.extractData));
  }
  getStatsByNoSol(year: any): Observable<any>{
    return this.http.get( endpoint + '/bynosol/'+year ).pipe(
      map(this.extractData));
  }

  getStatsByC(year: any): Observable<any>{
    return this.http.get( endpoint + '/status/'+year ).pipe(
      map(this.extractData));
  }

}
