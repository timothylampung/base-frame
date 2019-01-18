import {MetaObject} from '../../../models/meta-object.model';

export interface PositionCode extends MetaObject {
    code: string;
    description: string;
}

export interface PositionCodeResult {
    totalSize: number;
    data: PositionCode[];
}

