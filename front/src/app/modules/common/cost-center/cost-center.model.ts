import {MetaObject} from "../../../models/meta-object.model";

export interface CostCenter extends MetaObject {
    code: string;
    description: string;
}

export interface CostCenterResult {
    totalSize: number;
    data: CostCenter[];
}

