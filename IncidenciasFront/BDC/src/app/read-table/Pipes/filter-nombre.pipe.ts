import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterNombre'
})
export class FilterNombrePipe implements PipeTransform {

  transform( list : any, campo: string, valor: string): any {

    console.log (campo);
    console.log (valor);
    if (!list) {
      return [];
    }
    if (!list || !valor) {
      return list;
    }

    return list.filter(singleItem =>
      singleItem[campo].toLowerCase().includes(valor.toLowerCase())
    );  }

}
