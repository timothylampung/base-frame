import {MetaObject} from "../../../models/meta-object.model";

export interface BankCode extends MetaObject {
    code: string;
    description: string;
}

export interface BankCodeResult {
    totalSize: number;
    data: BankCode[];
}



