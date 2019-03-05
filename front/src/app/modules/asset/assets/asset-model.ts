import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";
import {AssetCode} from "../asset-codes/asset-code-model";
import {AssetCategory} from "./asset-category.enum";


export  interface Asset extends MetaObject {

    code: string;
    description: string;
    assetCode:AssetCode;
    location: Location;
    quantity:string;
    cost: string;
    category: AssetCategory;

}

export interface AssetResult {

    totalSize: number;
    data: Asset[];

}
