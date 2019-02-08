import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface Part extends MetaObject {

    code: string;
    description: string;

}

export interface PartResult {

    totalSize: number;
    data: Part[];

}
