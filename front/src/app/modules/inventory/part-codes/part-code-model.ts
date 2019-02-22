import {MetaObject} from "../../../models";

export  interface PartCode extends MetaObject {

    code: string;
    description: string;

}

export interface PartCodeResult {

    totalSize: number;
    data: PartCode[];

}
