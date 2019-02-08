import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {MaintenanceRequest, MaintenanceRequestResult} from "./maintenance-request/maintenance-request.model";
import {AppState} from "../../core/core.state";
import {
    maintenanceRequestResultReducer,
    maintenanceRequestsReducer
} from "./maintenance-request/maintenance-request.reducer";


export const FEATURE_NAME = 'maintenanceRequest';
export const selectMaintenanceRequest = createFeatureSelector<State, MaintenanceRequestState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<MaintenanceRequestState> = {
    maintenanceRequestResult: maintenanceRequestResultReducer,
    maintenanceRequest: maintenanceRequestsReducer,
};

export interface MaintenanceRequestState {
    maintenanceRequestResult: MaintenanceRequestResult;
    maintenanceRequest: MaintenanceRequest[];
}

export interface State extends AppState {
    maintenanceRequest: MaintenanceRequestState;
}
