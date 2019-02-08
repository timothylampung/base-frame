import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface Component extends MetaObject {

    code: string;
    description: string;

}

export interface ComponentResult {

    totalSize: number;
    data: Component[];

}
