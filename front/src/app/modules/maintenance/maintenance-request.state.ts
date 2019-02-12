import {AppState} from '../../core/core.state';
import {ActionReducerMap, createFeatureSelector} from '@ngrx/store';
import {
    MaintenanceRequest,
    MaintenanceRequestRecordSummaryResult,
    MaintenanceRequestResult,
    MaintenanceRequestTaskSummary,
    MaintenanceRequestTaskSummaryResult
} from './maintenance-request.model';
import {
    maintenanceRequestCountTaskReducer,
    maintenanceRequestRecordResultReducer,
    maintenanceRequestReducer,
    maintenanceRequestResultReducer,
    maintenanceRequestTaskReducer,
    maintenanceRequestTaskResultReducer,
} from './maintenance-request.reducer';

export const FEATURE_NAME = 'maintenanceRequest';
export const selectMaintenanceRequestState = createFeatureSelector<State, MaintenanceRequestState>(FEATURE_NAME);

export const reducers: ActionReducerMap<MaintenanceRequestState> = {
    maintenanceRequestTaskResult: maintenanceRequestTaskResultReducer,
    maintenanceRequestTask: maintenanceRequestTaskReducer,
    maintenanceRequestCountTask: maintenanceRequestCountTaskReducer,
    maintenanceRequestRecordResult: maintenanceRequestRecordResultReducer,
    maintenanceRequestResult: maintenanceRequestResultReducer,
    maintenanceRequest: maintenanceRequestReducer,
};

export interface MaintenanceRequestState {
    maintenanceRequestTaskResult: MaintenanceRequestTaskSummaryResult;
    maintenanceRequestTask: MaintenanceRequestTaskSummary;
    maintenanceRequestCountTask: any,
    maintenanceRequestRecordResult: MaintenanceRequestRecordSummaryResult;
    maintenanceRequestResult: MaintenanceRequestResult;
    maintenanceRequest: MaintenanceRequest;
}

export interface State extends AppState {
    maintenanceRequest: MaintenanceRequestState;
}
