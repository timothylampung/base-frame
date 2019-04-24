import {Pipe, PipeTransform} from "@angular/core";

@Pipe({ name: 'statusTypePipe' })
export class StatusTypePipe implements PipeTransform {
    transform(value: boolean): string {
        return value == true ? 'Active' : 'Inactive'
    };
}
