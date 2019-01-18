import {Pipe, PipeTransform} from '@angular/core';
import {PrincipalType} from "../modules/identity/principals/principal-type.enum";

@Pipe({name: 'principalTypePipe'})
export class PrincipalTypePipe implements PipeTransform {

  transform(value: PrincipalType): any {
    if (!value) {
      return value;
    }
    switch (PrincipalType[value.toString()]) {
      case PrincipalType.USER : {
        return 'PENGGUNA';
      }
      case PrincipalType.GROUP : {
        return 'KUMPULAN';
      }
      default: {
        return value;
      }
    }
  }
}
