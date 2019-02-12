import {MetaObject} from '../../../models/meta-object.model';

export interface WorkOrder extends MetaObject {
    code: string;
    description: string;
}

export interface WorkOrderResult {
    totalSize: number;
    data: WorkOrder[];
}

export const initialStateWorkOrderResult: WorkOrderResult = {
    data: [], totalSize: 0
};

export const initStateWorkOrder: WorkOrder = {
    id: null,
    code: null,
    description: null,
};
