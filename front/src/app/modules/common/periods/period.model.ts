import {MetaObject} from '../../../models/meta-object.model';

export interface Period extends MetaObject {
    code: string;
    description: string;
    year: number;
    current: boolean;
}

export interface PeriodResult {
    totalSize: number;
    data: Period[];
}

export interface File {

}

export const initialStatePeriodResult: PeriodResult = {
    data: [], totalSize: 0
};

export const initStatePeriod: Period = {
    id: null,
    code: null,
    description: null,
    year: new Date().getFullYear(),
    current: true
};
