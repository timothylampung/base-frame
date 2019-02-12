import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


//FIXME @Uda change to part component? Component reserve by angular
export  interface PartComponent extends MetaObject {

    code: string;
    description: string;

}

export interface ComponentResult {

    totalSize: number;
    data: PartComponent[];

}
