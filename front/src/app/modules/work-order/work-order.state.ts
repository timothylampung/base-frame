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
    workOrderRecordResultReducer,
    workOrderReducer,
    workOrderResultReducer,
    workOrderTaskReducer,
    workOrderTaskResultReducer,
    workOrderActivitiesReducer,
    workOrderLogsReducer,
    workOrderCommentsReducer,
} from './work-order.reducer';
import {WorkOrderActivity} from './work-order-activity.model';
import {WorkOrderLog} from "./work-order-log.model";
import {WorkOrderComment} from "./work-order-comment.model";

export const FEATURE_NAME = 'workOrder';
export const selectWorkOrderState = createFeatureSelector<State, WorkOrderState>(FEATURE_NAME);

export const reducers: ActionReducerMap<WorkOrderState> = {
    workOrderTaskResult: workOrderTaskResultReducer,
    workOrderTask: workOrderTaskReducer,
    workOrderCountTask: workOrderCountTaskReducer,
    workOrderRecordResult: workOrderRecordResultReducer,
    workOrderResult: workOrderResultReducer,
    workOrder: workOrderReducer,
    workOrderActivities: workOrderActivitiesReducer,
    workOrderLogs: workOrderLogsReducer,
    workOrderComments: workOrderCommentsReducer,
};

export interface WorkOrderState {
    workOrderTaskResult: WorkOrderTaskSummaryResult;
    workOrderTask: WorkOrderTaskSummary;
    workOrderCountTask: any,
    workOrderRecordResult: WorkOrderRecordSummaryResult;
    workOrderResult: WorkOrderResult;
    workOrder: WorkOrder;
    workOrderActivities: WorkOrderActivity[];
    workOrderComments: WorkOrderComment[];
    workOrderLogs: WorkOrderLog[];
}

export interface State extends AppState {
    workOrder: WorkOrderState;
}
