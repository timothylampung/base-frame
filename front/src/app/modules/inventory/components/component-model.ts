import {Meta} from "@angular/platform-browser";
import {MetaObject} from "../../../models/meta-object.model";
import {Asset} from "../../asset/assets/asset.model";
import {PartCode} from "../part-codes/part-code-model";


//FIXME @Uda change to part component? Component reserve by angular
export  interface PartComponent extends MetaObject {

    code: string;
    description: string;
    asset : Asset;
    partCode: PartCode;


}

export interface ComponentResult {

    totalSize: number;
    data: PartComponent[];

}
