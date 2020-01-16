import { Pipe, PipeTransform } from '@angular/core';



@Pipe({
  name: 'rangoFechas'
})
export class RangoFechasPipe implements PipeTransform {

  transform(list: any[], start:any, end:any  ): any {
      
      
    return start ? list.filter(
      row => {
      return ( new Date ( row._fechaOcurrencia ) >= new Date ( start ) 
              && new Date( row._fechaOcurrencia ) <= new Date( end ) ) } ): list; 
  }

}

