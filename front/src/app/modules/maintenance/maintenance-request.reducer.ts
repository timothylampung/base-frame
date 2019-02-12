import {
    COUNT_ASSIGNED_WORK_ORDERS_SUCCESS,
    COUNT_POOLED_WORK_ORDERS_SUCCESS,
    CountAssignedMaintenanceRequestsSuccessAction,
    CountPooledMaintenanceRequestsSuccessAction,
    FIND_ARCHIVED_WORK_ORDERS_SUCCESS,
    FIND_ASSIGNED_WORK_ORDERS_SUCCESS,
    FIND_WORK_ORDER_BY_REFERENCE_NO_SUCCESS,
    FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS,
    FIND_PAGED_WORK_ORDERS_SUCCESS,
    FIND_POOLED_WORK_ORDERS_SUCCESS,
    FindArchivedMaintenanceRequestsSuccessAction,
    FindAssignedMaintenanceRequestsSuccessAction,
    FindPagedMaintenanceRequestsSuccessAction,
    FindPooledMaintenanceRequestsSuccessAction,
    NEW_WORK_ORDER_TASK,
    SELECT_WORK_ORDER
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
        case FIND_PAGED_WORK_ORDERS_SUCCESS:
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
        case FIND_ASSIGNED_WORK_ORDERS_SUCCESS:
            return {
                data: [...action.payload.data],
                totalSize: action.payload.totalSize
            };
        case FIND_POOLED_WORK_ORDERS_SUCCESS:
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
        case COUNT_ASSIGNED_WORK_ORDERS_SUCCESS:
            return {...state, ...action.payload};
        case COUNT_POOLED_WORK_ORDERS_SUCCESS:
            return {...state, ...action.payload};
        default: {
            return state;
        }
    }
}

export function maintenanceRequestTaskReducer(state = initStateMaintenanceRequestTaskSummary,
                                   action): MaintenanceRequestTaskSummary {
    switch (action.type) {
        case FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS:
            return {
                ...state,
                ...action.payload
            };
        case NEW_WORK_ORDER_TASK:
            return initStateMaintenanceRequestTaskSummary;
        default: {
            return state;
        }
    }
}

export function maintenanceRequestRecordResultReducer(state = initStateMaintenanceRequestRecordResult,
                                           action: FindArchivedMaintenanceRequestsSuccessAction): MaintenanceRequestRecordSummaryResult {
    switch (action.type) {
        case FIND_ARCHIVED_WORK_ORDERS_SUCCESS:
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
        case FIND_WORK_ORDER_BY_REFERENCE_NO_SUCCESS:
            return {
                ...state,
                ...action.payload
            };
        case SELECT_WORK_ORDER:
            return {
                ...state,
                ...action.payload
            };
        default: {
            return state;
        }
    }
}
