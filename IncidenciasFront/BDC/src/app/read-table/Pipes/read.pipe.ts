import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'tableFilter'
})
export class ReadPipe implements PipeTransform {

  transform(list: any[], filters: Object ): any {
      


    const clave = Object.keys(filters).filter(key => filters[key]);
    const filterInc = inc => clave.every(key => inc[key] === filters[key]);

    
    return clave.length ? list.filter(filterInc) : list; 
  }

}






