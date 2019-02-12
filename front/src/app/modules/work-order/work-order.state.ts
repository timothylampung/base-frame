import {AppState} from '../../core/core.state';
import {ActionReducerMap, createFeatureSelector} from '@ngrx/store';
import {
    WorkOrder,
    WorkOrderRecordSummaryResult,
    WorkOrderResult,
    WorkOrderTaskSummary,
    WorkOrderTaskSummaryResult
} from './work-order.model';
import {
    workOrderCountTaskReducer,
    activitiesReducer,
    workOrderRecordResultReducer,
    workOrderReducer,
    workOrderResultReducer,
    workOrderTaskReducer,
    workOrderTaskResultReducer,
} from './work-order.reducer';
import {Activity} from './activity.model';

export const FEATURE_NAME = 'workOrder';
export const selectWorkOrderState = createFeatureSelector<State, WorkOrderState>(FEATURE_NAME);

export const reducers: ActionReducerMap<WorkOrderState> = {
    workOrderTaskResult: workOrderTaskResultReducer,
    workOrderTask: workOrderTaskReducer,
    workOrderCountTask: workOrderCountTaskReducer,
    workOrderRecordResult: workOrderRecordResultReducer,
    workOrderResult: workOrderResultReducer,
    workOrder: workOrderReducer,
    activities: activitiesReducer,
};

export interface WorkOrderState {
    workOrderTaskResult: WorkOrderTaskSummaryResult;
    workOrderTask: WorkOrderTaskSummary;
    workOrderCountTask: any,
    workOrderRecordResult: WorkOrderRecordSummaryResult;
    workOrderResult: WorkOrderResult;
    workOrder: WorkOrder;
    activities: Activity[];
}

export interface State extends AppState {
    workOrder: WorkOrderState;
}
