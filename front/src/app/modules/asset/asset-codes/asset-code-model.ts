import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface AssetCode extends MetaObject {

    code: string;
    description: string;

}

export interface AssetCodeResult {

    totalSize: number;
    data: AssetCode[];

}
