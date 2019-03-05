import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";
import {AssetCode} from "../asset-codes/asset-code-model";


export  interface Asset extends MetaObject {

    code: string;
    description: string;
    assetCode:AssetCode;
    location: Location;
    category: string;
    quantity:string;
    cost: string;

}

export interface AssetResult {

    totalSize: number;
    data: Asset[];

}
