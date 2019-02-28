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
import {WorkOrderActivity} from "./work-order-activity.model";
import {WorkOrderLog} from "./work-order-log.model";
import {WorkOrderComment} from "./work-order-comment.model";

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

export const FIND_WORK_ORDER_ACTIVITIES = '[WorkOrder] Find workOrder workOrderActivities';
export const FIND_WORK_ORDER_ACTIVITIES_SUCCESS = '[WorkOrder] Find workOrder workOrderActivities success';
export const FIND_WORK_ORDER_ACTIVITIES_ERROR = '[WorkOrder] Find workOrder workOrderActivities error';

export const FIND_WORK_ORDER_COMMENTS = '[WorkOrder] Find workOrder comments';
export const FIND_WORK_ORDER_COMMENTS_SUCCESS = '[WorkOrder] Find workOrder comments success';
export const FIND_WORK_ORDER_COMMENTS_ERROR = '[WorkOrder] Find workOrder comments error';
export const ADD_WORK_ORDER_COMMENT = '[WorkOrder] Add workOrder item';
export const ADD_WORK_ORDER_COMMENT_SUCCESS = '[WorkOrder] Add workOrder comments success';
export const ADD_WORK_ORDER_COMMENT_ERROR = '[WorkOrder] Add workOrder comments error';
export const UPDATE_WORK_ORDER_COMMENT = '[WorkOrder] Update workOrder comments';
export const UPDATE_WORK_ORDER_COMMENT_SUCCESS = '[WorkOrder] Update workOrder comments success';
export const UPDATE_WORK_ORDER_COMMENT_ERROR = '[WorkOrder] Update workOrder comments error';
export const REMOVE_WORK_ORDER_COMMENT = '[WorkOrder] Remove workOrder comments';
export const REMOVE_WORK_ORDER_COMMENT_SUCCESS = '[WorkOrder] Remove workOrder comments success';
export const REMOVE_WORK_ORDER_COMMENT_ERROR = '[WorkOrder] Remove workOrder comments error';

export const FIND_WORK_ORDER_LOGS = '[WorkOrder] Find workOrder items';
export const FIND_WORK_ORDER_LOGS_SUCCESS = '[WorkOrder] Find workOrder items success';
export const FIND_WORK_ORDER_LOGS_ERROR = '[WorkOrder] Find workOrder items error';
export const START_WORK_ORDER_LOG = '[WorkOrder] Start workOrder item';
export const START_WORK_ORDER_LOG_SUCCESS = '[WorkOrder] Start workOrder item success';
export const START_WORK_ORDER_LOG_ERROR = '[WorkOrder] Start workOrder item error';
export const STOP_WORK_ORDER_LOG = '[WorkOrder] Stop workOrder item';
export const STOP_WORK_ORDER_LOG_SUCCESS = '[WorkOrder] Stop workOrder item success';
export const STOP_WORK_ORDER_LOG_ERROR = '[WorkOrder] Stop workOrder item error';

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

export class StartWorkOrderLogAction implements Action {
    readonly type: string = START_WORK_ORDER_LOG;

    constructor(public payload: WorkOrder) {
    }
}

export class StartWorkOrderLogSuccessAction implements Action {
    readonly type: string = START_WORK_ORDER_LOG_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class StartWorkOrderLogErrorAction implements Action {
    readonly type: string = START_WORK_ORDER_LOG_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class StopWorkOrderLogAction implements Action {
    readonly type: string = STOP_WORK_ORDER_LOG;

    constructor(public payload: WorkOrder) {
    }
}

export class StopWorkOrderLogSuccessAction implements Action {
    readonly type: string = STOP_WORK_ORDER_LOG_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class StopWorkOrderLogErrorAction implements Action {
    readonly type: string = STOP_WORK_ORDER_LOG_ERROR;

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

export class FindWorkOrderActivitiesAction implements Action {
    readonly type: string = FIND_WORK_ORDER_ACTIVITIES;

    constructor(public payload: { workOrder: WorkOrder }) {
    }
}

export class FindWorkOrderActivitiesSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_ACTIVITIES_SUCCESS;

    constructor(public payload: WorkOrderActivity[]) {
    }
}

export class FindWorkOrderActivitiesErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_ACTIVITIES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindWorkOrderLogsAction implements Action {
    readonly type: string = FIND_WORK_ORDER_LOGS;

    constructor(public payload: { workOrder: WorkOrder }) {
    }
}

export class FindWorkOrderLogsSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_LOGS_SUCCESS;

    constructor(public payload: WorkOrderLog[]) {
    }
}

export class FindWorkOrderLogsErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_LOGS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindWorkOrderCommentsAction implements Action {
    readonly type: string = FIND_WORK_ORDER_COMMENTS;

    constructor(public payload: { workOrder: WorkOrder }) {
    }
}

export class FindWorkOrderCommentsSuccessAction implements Action {
    readonly type: string = FIND_WORK_ORDER_COMMENTS_SUCCESS;

    constructor(public payload: WorkOrderComment[]) {
    }
}

export class FindWorkOrderCommentsErrorAction implements Action {
    readonly type: string = FIND_WORK_ORDER_COMMENTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class AddWorkOrderCommentAction implements Action {
    readonly type: string = ADD_WORK_ORDER_COMMENT;

    constructor(public payload: { workOrder: WorkOrder, comment: WorkOrderComment }) {
    }
}

export class AddWorkOrderCommentSuccessAction implements Action {
    readonly type: string = ADD_WORK_ORDER_COMMENT_SUCCESS;

    constructor(public payload: WorkOrderComment) {
    }
}

export class AddWorkOrderCommentErrorAction implements Action {
    readonly type: string = ADD_WORK_ORDER_COMMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateWorkOrderCommentAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER_COMMENT;

    constructor(public payload: { workOrder: WorkOrder, comment: WorkOrderComment }) {
    }
}

export class UpdateWorkOrderCommentSuccessAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER_COMMENT_SUCCESS;

    constructor(public payload: WorkOrderComment) {
    }
}

export class UpdateWorkOrderCommentErrorAction implements Action {
    readonly type: string = UPDATE_WORK_ORDER_COMMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveWorkOrderCommentAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_COMMENT;

    constructor(public payload: { workOrder: WorkOrder, comment: WorkOrderComment }) {
    }
}

export class RemoveWorkOrderCommentSuccessAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_COMMENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveWorkOrderCommentErrorAction implements Action {
    readonly type: string = REMOVE_WORK_ORDER_COMMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

