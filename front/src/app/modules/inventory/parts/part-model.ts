import {MetaObject} from "../../../models";
import {PartCode} from "../part-codes/part-code-model";

export  interface Part extends MetaObject {

    code: string;
    description: string;
    partCode: PartCode;

}

export interface PartResult {

    totalSize: number;
    data: Part[];

}
