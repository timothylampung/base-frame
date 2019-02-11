import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface PartCode extends MetaObject {

    code: string;
    description: string;

}

export interface PartCodeResult {

    totalSize: number;
    data: PartCode[];

}
