import {MetaObject} from '../../../models/meta-object.model';
export interface Configuration extends MetaObject {
    key: string;
    value: string;
    description: string;
}

export interface ConfigurationResult {
    totalSize: number;
    data: Configuration[];
}
