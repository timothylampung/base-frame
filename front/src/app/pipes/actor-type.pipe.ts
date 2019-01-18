import {Pipe, PipeTransform} from '@angular/core';
import {ActorType} from "../modules/identity/actors/actor-type.enum";

@Pipe({name: 'actorTypePipe'})
export class ActorTypePipe implements PipeTransform {

  transform(value: ActorType): any {
    if (!value) {
      return value;
    }
    switch (ActorType[value.toString()]) {
      default: {
        return value;
      }
    }
  }
}
