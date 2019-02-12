import {Action} from '@ngrx/store';
import {
    WorkOrder,
    WorkOrderRecordSummary,
    WorkOrderRecordSummaryResult,
    WorkOrderResult,
    WorkOrderTaskSummary,
    WorkOrderTaskSummaryResult
} from './work-order.model';
import {ApplicationError} from '../../models/application-error.model';
import {Activity} from "./activity.model";

export const FIND_ASSIGNED_WORK_ORDERS = '[WorkOrder] Find Assigned WorkOrders';
export const FIND_ASSIGNED_WORK_ORDERS_SUCCESS = '[WorkOrder] Find Assigned WorkOrders Success';
export const FIND_ASSIGNED_WORK_ORDERS_ERROR = '[WorkOrder] Find Assigned WorkOrders Error';
export const FIND_POOLED_WORK_ORDERS = '[WorkOrder] Find Pooled WorkOrders';
export const FIND_POOLED_WORK_ORDERS_SUCCESS = '[WorkOrder] Find Pooled WorkOrders Success';
export const FIND_POOLED_WORK_ORDERS_ERROR = '[WorkOrder] Find Pooled WorkOrders Error';
export const FIND_ARCHIVED_WORK_ORDERS = '[WorkOrder] Find Archived WorkOrders';
export const FIND_ARCHIVED_WORK_ORDERS_SUCCESS = '[WorkOrder] Find Archived WorkOrders Success';
export const FIND_ARCHIVED_WORK_ORDERS_ERROR = '[WorkOrder] Find Archived WorkOrders Error';

export const COUNT_ASSIGNED_WORK_ORDERS = '[WorkOrder] Count Assigned WorkOrders';
export const COUNT_ASSIGNED_WORK_ORDERS_SUCCESS = '[WorkOrder] Count Assigned WorkOrders Success';
export const COUNT_ASSIGNED_WORK_ORDERS_ERROR = '[WorkOrder] Count Assigned WorkOrders Error';
export const COUNT_POOLED_WORK_ORDERS = '[WorkOrder] Count Pooled WorkOrders';
export const COUNT_POOLED_WORK_ORDERS_SUCCESS = '[WorkOrder] Count Pooled WorkOrders Success';
export const COUNT_POOLED_WORK_ORDERS_ERROR = '[WorkOrder] Count Pooled WorkOrders Error';

export const FIND_WORK_ORDER_TASK_BY_TASK_ID = '[WorkOrder] Find WorkOrder Task By Task Id';
export const FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS = '[WorkOrder] Find WorkOrder Task By Task Id Success';
export const FIND_WORK_ORDER_TASK_BY_TASK_ID_ERROR = '[WorkOrder] Find WorkOrder Task By Task Id Error';
export const FIND_WORK_ORDER_RECORD_BY_RECORD_ID = '[WorkOrder] Find WorkOrder Record By Record Id';
export const FIND_WORK_ORDER_RECORD_BY_RECORD_ID_SUCCESS = '[WorkOrder] Find WorkOrder Record By Record Id Success';
export const FIND_WORK_ORDER_RECORD_BY_RECORD_ID_ERROR = '[WorkOrder] Find WorkOrder Record By Record Id Error';
export const START_WORK_ORDER_TASK = '[WorkOrder] Start workOrder task';
export const START_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Start workOrder task success';
export const START_WORK_ORDER_TASK_ERROR = '[WorkOrder] Start workOrder task error';
export const CLAIM_WORK_ORDER_TASK = '[WorkOrder] Claim WorkOrder Task';
export const CLAIM_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Claim WorkOrder Task Success';
export const RELEASE_WORK_ORDER_TASK = '[WorkOrder] Release WorkOrder Task';
export const RELEASE_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Release WorkOrder Task Success';
export const COMPLETE_WORK_ORDER_TASK = '[WorkOrder] Complete WorkOrder Task';
export const COMPLETE_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Complete WorkOrder Task Success';
export const COMPLETE_WORK_ORDER_TASK_ERROR = '[WorkOrder] Complete WorkOrder Task Error';
export const CANCEL_WORK_ORDER_TASK = '[WorkOrder] Cancel WorkOrder Task';
export const CANCEL_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Cancel WorkOrder Task Success';
export const CANCEL_WORK_ORDER_TASK_ERROR = '[WorkOrder] Cancel WorkOrder Task Error';
export const REMOVE_WORK_ORDER_TASK = '[WorkOrder] Remove workOrder task';
export const REMOVE_WORK_ORDER_TASK_SUCCESS = '[WorkOrder] Remove workOrder task success';
export const REMOVE_WORK_ORDER_TASK_ERROR = '[WorkOrder] Remove workOrder task error';

export const FIND_PAGED_WORK_ORDERS = '[WorkOrder] Find Paged WorkOrders';
export const FIND_PAGED_WORK_ORDERS_SUCCESS = '[WorkOrder] Find Paged WorkOrders Success';
export const FIND_PAGED_WORK_ORDERS_ERROR = '[WorkOrder] Find Paged WorkOrders Error';
export const FIND_WORK_ORDER_BY_REFERENCE_NO = '[WorkOrder] Find WorkOrder by reference_no';
export const FIND_WORK_ORDER_BY_REFERENCE_NO_SUCCESS = '[WorkOrder] Find WorkOrder by reference no Success';
export const FIND_WORK_ORDER_BY_REFERENCE_NO_ERROR = '[WorkOrder] Find WorkOrder by reference no Error';
export const UPDATE_WORK_ORDER = '[WorkOrder] Update WorkOrder';
export const UPDATE_WORK_ORDER_SUCCESS = '[WorkOrder] Update WorkOrder Success';
export const UPDATE_WORK_ORDER_ERROR = '[WorkOrder] Update WorkOrder Error';

export const FIND_ACTIVITIES = '[WorkOrder] Find workOrder items';
export const FIND_ACTIVITIES_SUCCESS = '[WorkOrder] Find workOrder items success';
export const FIND_ACTIVITIES_ERROR = '[WorkOrder] Find workOrder items error';
export const ADD_ACTIVITY = '[WorkOrder] Add workOrder item';
export const ADD_ACTIVITY_SUCCESS = '[WorkOrder] Add workOrder item success';
export const ADD_ACTIVITY_ERROR = '[WorkOrder] Add workOrder item error';
export const UPDATE_ACTIVITY = '[WorkOrder] Update workOrder item';
export const UPDATE_ACTIVITY_SUCCESS = '[WorkOrder] Update workOrder item success';
export const UPDATE_ACTIVITY_ERROR = '[WorkOrder] Update workOrder item error';
export const REMOVE_ACTIVITY = '[WorkOrder] Remove workOrder item';
export const REMOVE_ACTIVITY_SUCCESS = '[WorkOrder] Remove workOrder item success';
export const REMOVE_ACTIVITY_ERROR = '[WorkOrder] Remove workOrder item error';

export const SELECT_WORK_ORDER = '[WorkOrder] Select workOrder';
export const NEW_WORK_ORDER_TASK = '[WorkOrder] New WorkOrder';
export const RELOAD_WORK_ORDER_PAGE = '[WorkOrder] Reload workOrder page';

export class FindWorkOrderByReferenceNoAction implements Action {
    readonly type: string = FIND_WORK_ORDER_BY_REFERENCE_NO;

    constructor(public referenceNo: string) {
    }
}

export class FindWorkOrderByReferenceNoSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_BY_REFERENCE_NO_SUCCESS;

    constructor(public payload: WorkOrder) {
    }
}

export class FindWorkOrderByReferenceNoErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_BY_REFERENCE_NO_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindActivitiesAction implements Action {
    readonly type: string = FIND_ACTIVITIES;

    constructor(public payload: { workOrder: WorkOrder }) {
    }
}

export class FindActivitiesSuccessAction implements Action {
    readonly type: string = FIND_ACTIVITIES_SUCCESS;

    constructor(public payload: Activity[]) {
    }
}

export class FindActivitiesErrorAction implements Action {
    readonly type: string = FIND_ACTIVITIES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindAssignedWorkOrdersAction implements Action {
    readonly type: string = FIND_ASSIGNED_WORK_ORDERS;

    constructor(public payload: { filter?: string, page: number }) {
    }
}

export class FindAssignedWorkOrdersSuccessAction implements Action {
    readonly type: string = FIND_ASSIGNED_WORK_ORDERS_SUCCESS;

    constructor(public payload: WorkOrderTaskSummaryResult) {
    }
}

export class FindAssignedWorkOrdersErrorAction implements Action {
    readonly type: string = FIND_ASSIGNED_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPooledWorkOrdersAction implements Action {
    readonly type: string = FIND_POOLED_WORK_ORDERS;

    constructor(public payload: { filter?: string, page: number }) {
    }
}

export class FindPooledWorkOrdersSuccessAction implements Action {
    readonly type: string = FIND_POOLED_WORK_ORDERS_SUCCESS;

    constructor(public payload: WorkOrderTaskSummaryResult) {
    }
}

export class FindPooledWorkOrdersErrorAction implements Action {
    readonly type: string = FIND_POOLED_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindArchivedWorkOrdersAction implements Action {
    readonly type: string = FIND_ARCHIVED_WORK_ORDERS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindArchivedWorkOrdersSuccessAction implements Action {
    readonly type: string = FIND_ARCHIVED_WORK_ORDERS_SUCCESS;

    constructor(public payload: WorkOrderRecordSummaryResult) {
    }
}

export class FindArchivedWorkOrdersErrorAction implements Action {
    readonly type: string = FIND_ARCHIVED_WORK_ORDERS_ERROR;

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

export class FindWorkOrderTaskByTaskIdAction implements Action {
    readonly type: string = FIND_WORK_ORDER_TASK_BY_TASK_ID;

    constructor(public payload: { taskId: string }) {
    }
}

export class FindWorkOrderTaskByTaskIdSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_TASK_BY_TASK_ID_SUCCESS;

    constructor(public payload: WorkOrderTaskSummary) {
    }
}

export class FindWorkOrderTaskByTaskIdErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_TASK_BY_TASK_ID_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindWorkOrderRecordByRecordIdAction implements Action {
    readonly type: string = FIND_WORK_ORDER_RECORD_BY_RECORD_ID;

    constructor(public payload: { recordId: string }) {
    }
}

export class FindWorkOrderRecordByRecordIdSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_RECORD_BY_RECORD_ID_SUCCESS;

    constructor(public payload: WorkOrderRecordSummary) {
    }
}

export class FindWorkOrderRecordByRecordIdErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_RECORD_BY_RECORD_ID_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CountAssignedWorkOrdersAction implements Action {
    readonly type: string = COUNT_ASSIGNED_WORK_ORDERS;

    constructor() {
    }
}

export class CountAssignedWorkOrdersSuccessAction implements Action {
    readonly type: string = COUNT_ASSIGNED_WORK_ORDERS_SUCCESS;

    constructor(public payload: any) {
    }
}

export class CountAssignedWorkOrdersErrorAction implements Action {
    readonly type: string = COUNT_ASSIGNED_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CountPooledWorkOrdersAction implements Action {
    readonly type: string = COUNT_POOLED_WORK_ORDERS;

    constructor() {
    }
}

export class CountPooledWorkOrdersSuccessAction implements Action {
    readonly type: string = COUNT_POOLED_WORK_ORDERS_SUCCESS;

    constructor(public payload: number) {
    }
}

export class CountPooledWorkOrdersErrorAction implements Action {
    readonly type: string = COUNT_POOLED_WORK_ORDERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class ClaimWorkOrderTaskAction implements Action {
    readonly type: string = CLAIM_WORK_ORDER_TASK;

    constructor(public payload: { taskIds: string[] }) {
    }
}

export class ClaimWorkOrderTaskSuccessAction implements Action {
    readonly type: string = CLAIM_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class ReleaseWorkOrderTaskAction implements Action {
    readonly type: string = RELEASE_WORK_ORDER_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class ReleaseWorkOrderTaskSuccessAction implements Action {
    readonly type: string = RELEASE_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CompleteWorkOrderTaskAction implements Action {
    readonly type: string = COMPLETE_WORK_ORDER_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class CompleteWorkOrderTaskSuccessAction implements Action {
    readonly type: string = COMPLETE_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CompleteWorkOrderTaskErrorAction implements Action {
    readonly type: string = COMPLETE_WORK_ORDER_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CancelWorkOrderTaskAction implements Action {
    readonly type: string = CANCEL_WORK_ORDER_TASK;

    constructor(public payload: WorkOrderTaskSummary) {
    }
}

export class CancelWorkOrderTaskSuccessAction implements Action {
    readonly type: string = CANCEL_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CancelWorkOrderTaskErrorAction implements Action {
    readonly type: string = CANCEL_WORK_ORDER_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class StartWorkOrderTaskAction implements Action {
    readonly type: string = START_WORK_ORDER_TASK;

    constructor(public payload: WorkOrder) {
    }
}

export class StartWorkOrderTaskSuccessAction implements Action {
    readonly type: string = START_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class StartWorkOrderTaskErrorAction implements Action {
    readonly type: string = START_WORK_ORDER_TASK_ERROR;

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

export class RemoveWorkOrderTaskAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class RemoveWorkOrderTaskSuccessAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveWorkOrderTaskErrorAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class AddActivityAction implements Action {
    readonly type: string = ADD_ACTIVITY;

    constructor(public payload: { workOrder: WorkOrder, workOrderItem: Activity }) {
    }
}

export class AddActivitySuccessAction implements Action {
    readonly type: string = ADD_ACTIVITY_SUCCESS;

    constructor(public payload: Activity) {
    }
}

export class AddActivityErrorAction implements Action {
    readonly type: string = ADD_ACTIVITY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateActivityAction implements Action {
    readonly type: string = UPDATE_ACTIVITY;

    constructor(public payload: { workOrder: WorkOrder, workOrderItem: Activity }) {
    }
}

export class UpdateActivitySuccessAction implements Action {
    readonly type: string = UPDATE_ACTIVITY_SUCCESS;

    constructor(public payload: Activity) {
    }
}

export class UpdateActivityErrorAction implements Action {
    readonly type: string = UPDATE_ACTIVITY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveActivityAction implements Action {
    readonly type: string = REMOVE_ACTIVITY;

    constructor(public payload: { workOrder: WorkOrder, workOrderItem: Activity }) {
    }
}

export class RemoveActivitySuccessAction implements Action {
    readonly type: string = REMOVE_ACTIVITY_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveActivityErrorAction implements Action {
    readonly type: string = REMOVE_ACTIVITY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class NewWorkOrderTaskAction implements Action {
    readonly type: string = NEW_WORK_ORDER_TASK;

    constructor() {
    }
}

export class SelectWorkOrderAction implements Action {
    readonly type: string = SELECT_WORK_ORDER;

    constructor(public payload: WorkOrder) {
    }
}

export class ReloadWorkOrderPageAction implements Action {
    readonly type: string = RELOAD_WORK_ORDER_PAGE;

    constructor() {
    }
}

