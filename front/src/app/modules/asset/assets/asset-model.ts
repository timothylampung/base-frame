import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";


export  interface Asset extends MetaObject {

    code: string;
    description: string;

}

export interface AssetResult {

    totalSize: number;
    data: Asset[];

}
