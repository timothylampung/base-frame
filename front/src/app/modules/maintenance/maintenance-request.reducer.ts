import {
    COUNT_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS,
    COUNT_POOLED_MAINTENANCE_REQUESTS_SUCCESS,
    CountAssignedMaintenanceRequestsSuccessAction,
    CountPooledMaintenanceRequestsSuccessAction,
    FIND_ARCHIVED_MAINTENANCE_REQUESTS_SUCCESS,
    FIND_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS,
    FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_SUCCESS,
    FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_SUCCESS,
    FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS,
    FIND_POOLED_MAINTENANCE_REQUESTS_SUCCESS,
    FindArchivedMaintenanceRequestsSuccessAction,
    FindAssignedMaintenanceRequestsSuccessAction,
    FindPagedMaintenanceRequestsSuccessAction,
    FindPooledMaintenanceRequestsSuccessAction,
    NEW_MAINTENANCE_REQUEST_TASK,
    SELECT_MAINTENANCE_REQUEST
} from "./maintenance-request.action";
import {
    initStateMaintenanceRequest,
    initStateMaintenanceRequestRecordResult,
    initStateMaintenanceRequestResult,
    initStateMaintenanceRequestTaskResult,
    initStateMaintenanceRequestTaskSummary,
    MaintenanceRequest,
    MaintenanceRequestRecordSummaryResult,
    MaintenanceRequestResult,
    MaintenanceRequestTaskSummary,
    MaintenanceRequestTaskSummaryResult
} from "./maintenance-request.model";

export function maintenanceRequestResultReducer(state = initStateMaintenanceRequestResult,
                                     action: FindPagedMaintenanceRequestsSuccessAction): MaintenanceRequestResult {
    switch (action.type) {
        case FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS:
            return {
                data: [...action.payload.data],
                totalSize: action.payload.totalSize
            };
        default: {
            return state;
        }
    }
}

export function maintenanceRequestTaskResultReducer(state = initStateMaintenanceRequestTaskResult,
                                         action: FindAssignedMaintenanceRequestsSuccessAction | FindPooledMaintenanceRequestsSuccessAction): MaintenanceRequestTaskSummaryResult {
    switch (action.type) {
        case FIND_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS:
            return {
                data: [...action.payload.data],
                totalSize: action.payload.totalSize
            };
        case FIND_POOLED_MAINTENANCE_REQUESTS_SUCCESS:
            return {
                data: [...action.payload.data],
                totalSize: action.payload.totalSize
            };
        default: {
            return state;
        }
    }
}

export function maintenanceRequestCountTaskReducer(state = {},
                                        action: CountAssignedMaintenanceRequestsSuccessAction | CountPooledMaintenanceRequestsSuccessAction) {
    switch (action.type) {
        case COUNT_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS:
            return {...state, ...action.payload};
        case COUNT_POOLED_MAINTENANCE_REQUESTS_SUCCESS:
            return {...state, ...action.payload};
        default: {
            return state;
        }
    }
}

export function maintenanceRequestTaskReducer(state = initStateMaintenanceRequestTaskSummary,
                                   action): MaintenanceRequestTaskSummary {
    switch (action.type) {
        case FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_SUCCESS:
            return {
                ...state,
                ...action.payload
            };
        case NEW_MAINTENANCE_REQUEST_TASK:
            return initStateMaintenanceRequestTaskSummary;
        default: {
            return state;
        }
    }
}

export function maintenanceRequestRecordResultReducer(state = initStateMaintenanceRequestRecordResult,
                                           action: FindArchivedMaintenanceRequestsSuccessAction): MaintenanceRequestRecordSummaryResult {
    switch (action.type) {
        case FIND_ARCHIVED_MAINTENANCE_REQUESTS_SUCCESS:
            return {
                data: [...action.payload.data],
                totalSize: action.payload.totalSize
            };
        default: {
            return state;
        }
    }
}

export function maintenanceRequestReducer(state = initStateMaintenanceRequest, action): MaintenanceRequest {
    switch (action.type) {
        case FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_SUCCESS:
            return {
                ...state,
                ...action.payload
            };
        case SELECT_MAINTENANCE_REQUEST:
            return {
                ...state,
                ...action.payload
            };
        default: {
            return state;
        }
    }
}
