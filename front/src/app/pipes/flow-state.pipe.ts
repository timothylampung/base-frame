import {Pipe, PipeTransform} from '@angular/core';
import {FlowState} from "../models/flow-state.enum";

@Pipe({name: 'flowStatePipe'})
export class FlowStatePipe implements PipeTransform {

  transform(value: FlowState): any {
    if (!value) {
      return value;
    }
    switch (FlowState[value.toString()]) {
      case FlowState.DRAFTED : {
        return 'BARU';
      }
      case FlowState.REGISTERED : {
        return 'DAFTAR';
      }
      case FlowState.VERIFIED : {
        return 'SAH';
      }
      case FlowState.APPROVED : {
        return 'LULUS';
      }
      case FlowState.COMPLETED : {
        return 'SELESAI';
      }
      case FlowState.REMOVED : {
        return 'HAPUS';
      }
      default: {
        return value;
      }
    }
  }
}
