import {MetaObject} from '../../../models/meta-object.model';

export interface AccountCode extends MetaObject {
    code: string;
    description: string;
}

export interface AccountCodeResult {
    totalSize: number;
    data: AccountCode[];
}



