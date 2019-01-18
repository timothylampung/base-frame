import {MetaObject} from '../../../models/meta-object.model';

export interface Department extends MetaObject {
    code: string;
    description: string;
}

export interface DepartmentResult {
    totalSize: number;
    data: Department[];
}

