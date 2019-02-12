import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {WorkOrder, WorkOrderResult} from "./work-order.model";
export const FIND_ALL_WORK_ORDERS = '[WorkOrder] Find All WorkOrders';
export const FIND_ALL_WORK_ORDERS_SUCCESS = '[WorkOrder] Find All WorkOrders Success';
export const FIND_ALL_WORK_ORDERS_ERROR = '[WorkOrder] Find All WorkOrders Error';
export const FIND_PAGED_WORK_ORDERS = '[WorkOrder] Find Paged WorkOrders';
export const FIND_PAGED_WORK_ORDERS_SUCCESS = '[WorkOrder] Find Paged WorkOrders Success';
export const FIND_PAGED_WORK_ORDERS_ERROR = '[WorkOrder] Find Paged WorkOrders Error';
export const SAVE_WORK_ORDER = '[WorkOrder] Save WorkOrder';
export const SAVE_WORK_ORDER_SUCCESS = '[WorkOrder] Save WorkOrder Success';
export const SAVE_WORK_ORDER_ERROR = '[WorkOrder] Save WorkOrder Error';
export const UPDATE_WORK_ORDER = '[WorkOrder] Update WorkOrder';
export const UPDATE_WORK_ORDER_SUCCESS = '[WorkOrder] Update WorkOrder Success';
export const UPDATE_WORK_ORDER_ERROR = '[WorkOrder] Update WorkOrder Error';
export const REMOVE_WORK_ORDER = '[WorkOrder] Remove WorkOrder';
export const REMOVE_WORK_ORDER_SUCCESS = '[WorkOrder] Remove WorkOrder Success';
export const REMOVE_WORK_ORDER_ERROR = '[WorkOrder] Remove WorkOrder Error';

export class FindAllWorkOrdersAction implements Action {
    readonly type: string = FIND_ALL_WORK_ORDERS;

    constructor() {
    }
}

export class FindAllWorkOrdersSuccessAction implements Action {
    readonly type: string = FIND_ALL_WORK_ORDERS_SUCCESS;

    constructor(public payload: WorkOrder[]) {
    }
}

export class FindAllWorkOrdersErrorAction implements Action {
    readonly type: string = FIND_ALL_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedWorkOrdersAction implements Action {
    readonly type: string = FIND_PAGED_WORK_ORDERS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedWorkOrdersSuccessAction implements Action {
    readonly type: string = FIND_PAGED_WORK_ORDERS_SUCCESS;

    constructor(public payload: WorkOrderResult) {
    }
}

export class FindPagedWorkOrdersErrorAction implements Action {
    readonly type: string = FIND_PAGED_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveWorkOrderAction implements Action {
    readonly type: string = SAVE_WORK_ORDER;

    constructor(public payload: WorkOrder) {
    }
}

export class SaveWorkOrderSuccessAction implements Action {
    readonly type: string = SAVE_WORK_ORDER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveWorkOrderErrorAction implements Action {
    readonly type: string = SAVE_WORK_ORDER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateWorkOrderAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER;

    constructor(public payload: WorkOrder) {
    }
}

export class UpdateWorkOrderSuccessAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateWorkOrderErrorAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveWorkOrderAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER;

    constructor(public payload: WorkOrder) {
    }
}

export class RemoveWorkOrderSuccessAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveWorkOrderErrorAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
