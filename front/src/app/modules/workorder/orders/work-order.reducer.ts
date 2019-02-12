
import {initialStateWorkOrderResult, WorkOrder, WorkOrderResult} from "./work-order.model";
import {
    FIND_ALL_WORK_ORDERS_SUCCESS,
    FIND_PAGED_WORK_ORDERS_SUCCESS,
    FindAllWorkOrdersSuccessAction, FindPagedWorkOrdersSuccessAction
} from "./work-order.action";

export function workOrderResultReducer(state = initialStateWorkOrderResult, action: FindPagedWorkOrdersSuccessAction): WorkOrderResult {
    switch (action.type) {
        case FIND_PAGED_WORK_ORDERS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function workOrdersReducer(state = [], action: FindAllWorkOrdersSuccessAction): WorkOrder[] {
    switch (action.type) {
        case FIND_ALL_WORK_ORDERS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
