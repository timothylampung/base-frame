import {
    COUNT_ASSIGNED_WORK_ORDERS_SUCCESS,
    COUNT_POOLED_WORK_ORDERS_SUCCESS,
    CountAssignedWorkOrdersSuccessAction,
    CountPooledWorkOrdersSuccessAction,
    FIND_ARCHIVED_WORK_ORDERS_SUCCESS,
    FIND_ASSIGNED_WORK_ORDERS_SUCCESS,
    FIND_WORK_ORDER_BY_REFERENCE_NO_SUCCESS,
    FIND_ACTIVITIES_SUCCESS,
    FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS,
    FIND_WORK_ORDER_TRANSACTIONS_SUCCESS,
    FIND_PAGED_WORK_ORDERS_SUCCESS,
    FIND_POOLED_WORK_ORDERS_SUCCESS,
    FindArchivedWorkOrdersSuccessAction,
    FindAssignedWorkOrdersSuccessAction,
    FindPagedWorkOrdersSuccessAction,
    FindPooledWorkOrdersSuccessAction,
    NEW_WORK_ORDER_TASK,
    SELECT_WORK_ORDER
} from "./work-order.action";
import {
    initStateWorkOrder,
    initStateWorkOrderRecordResult,
    initStateWorkOrderResult,
    initStateWorkOrderTaskResult,
    initStateWorkOrderTaskSummary,
    WorkOrder,
    WorkOrderRecordSummaryResult,
    WorkOrderResult,
    WorkOrderTaskSummary,
    WorkOrderTaskSummaryResult
} from "./work-order.model";
import {Activity} from "./activity.model";

export function workOrderResultReducer(state = initStateWorkOrderResult,
                                     action: FindPagedWorkOrdersSuccessAction): WorkOrderResult {
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

export function workOrderTaskResultReducer(state = initStateWorkOrderTaskResult,
                                         action: FindAssignedWorkOrdersSuccessAction | FindPooledWorkOrdersSuccessAction): WorkOrderTaskSummaryResult {
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

export function workOrderCountTaskReducer(state = {},
                                        action: CountAssignedWorkOrdersSuccessAction | CountPooledWorkOrdersSuccessAction) {
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

export function workOrderTaskReducer(state = initStateWorkOrderTaskSummary,
                                   action): WorkOrderTaskSummary {
    switch (action.type) {
        case FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS:
            return {
                ...state,
                ...action.payload
            };
        case NEW_WORK_ORDER_TASK:
            return initStateWorkOrderTaskSummary;
        default: {
            return state;
        }
    }
}

export function workOrderRecordResultReducer(state = initStateWorkOrderRecordResult,
                                           action: FindArchivedWorkOrdersSuccessAction): WorkOrderRecordSummaryResult {
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

export function workOrderReducer(state = initStateWorkOrder, action): WorkOrder {
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

export function activitiesReducer(state = [], action): Activity[] {
    switch (action.type) {
        case FIND_ACTIVITIES_SUCCESS:
            return [...action.payload];
        default: {
            return state;
        }
    }
}
