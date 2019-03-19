import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models";
import {AssetCode} from "../asset-codes/asset-code-model";
import {AssetCategory} from "./asset-category.enum";

export  interface Asset extends MetaObject {
    code: string;
    description: string;
    assetCode:AssetCode;
    location: Location;
    quantity: number;
    cost: number;
    category: AssetCategory;

    // transient
    summary: string;
}

export interface AssetResult {
    totalSize: number;
    data: Asset[];
}
