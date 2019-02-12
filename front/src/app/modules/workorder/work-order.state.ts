import {ActionReducerMap, createFeatureSelector} from '@ngrx/store';
import {AppState} from '../../core/core.state';
import {WorkOrder, WorkOrderResult} from "./orders/work-order.model";
import {workOrderResultReducer, workOrdersReducer} from "./orders/work-order.reducer";

export const FEATURE_NAME = 'workOrder';
export const selectCommonState = createFeatureSelector<State, WorkOrderState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<WorkOrderState> = {
    workOrderResult: workOrderResultReducer,
    workOrders: workOrdersReducer,
};

export interface WorkOrderState {
    workOrderResult: WorkOrderResult;
    workOrders: WorkOrder[];
}

export interface State extends AppState {
    workOrder: WorkOrderState;
}
