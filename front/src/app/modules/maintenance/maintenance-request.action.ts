import {Action} from '@ngrx/store';
import {
    MaintenanceRequest,
    MaintenanceRequestRecordSummary,
    MaintenanceRequestRecordSummaryResult,
    MaintenanceRequestResult,
    MaintenanceRequestTaskSummary,
    MaintenanceRequestTaskSummaryResult
} from './maintenance-request.model';
import {ApplicationError} from '../../models/application-error.model';

export const FIND_ASSIGNED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find Assigned MaintenanceRequests';
export const FIND_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find Assigned MaintenanceRequests Success';
export const FIND_ASSIGNED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find Assigned MaintenanceRequests Error';
export const FIND_POOLED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find Pooled MaintenanceRequests';
export const FIND_POOLED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find Pooled MaintenanceRequests Success';
export const FIND_POOLED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find Pooled MaintenanceRequests Error';
export const FIND_ARCHIVED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find Archived MaintenanceRequests';
export const FIND_ARCHIVED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find Archived MaintenanceRequests Success';
export const FIND_ARCHIVED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find Archived MaintenanceRequests Error';

export const COUNT_ASSIGNED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Count Assigned MaintenanceRequests';
export const COUNT_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Count Assigned MaintenanceRequests Success';
export const COUNT_ASSIGNED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Count Assigned MaintenanceRequests Error';
export const COUNT_POOLED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Count Pooled MaintenanceRequests';
export const COUNT_POOLED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Count Pooled MaintenanceRequests Success';
export const COUNT_POOLED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Count Pooled MaintenanceRequests Error';

export const FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID = '[MaintenanceRequest] Find MaintenanceRequest Task By Task Id';
export const FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_SUCCESS = '[MaintenanceRequest] Find MaintenanceRequest Task By Task Id Success';
export const FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_ERROR = '[MaintenanceRequest] Find MaintenanceRequest Task By Task Id Error';
export const FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID = '[MaintenanceRequest] Find MaintenanceRequest Record By Record Id';
export const FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID_SUCCESS = '[MaintenanceRequest] Find MaintenanceRequest Record By Record Id Success';
export const FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID_ERROR = '[MaintenanceRequest] Find MaintenanceRequest Record By Record Id Error';
export const START_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Start maintenanceRequest task';
export const START_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Start maintenanceRequest task success';
export const START_MAINTENANCE_REQUEST_TASK_ERROR = '[MaintenanceRequest] Start maintenanceRequest task error';
export const CLAIM_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Claim MaintenanceRequest Task';
export const CLAIM_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Claim MaintenanceRequest Task Success';
export const RELEASE_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Release MaintenanceRequest Task';
export const RELEASE_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Release MaintenanceRequest Task Success';
export const COMPLETE_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Complete MaintenanceRequest Task';
export const COMPLETE_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Complete MaintenanceRequest Task Success';
export const COMPLETE_MAINTENANCE_REQUEST_TASK_ERROR = '[MaintenanceRequest] Complete MaintenanceRequest Task Error';
export const CANCEL_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Cancel MaintenanceRequest Task';
export const CANCEL_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Cancel MaintenanceRequest Task Success';
export const CANCEL_MAINTENANCE_REQUEST_TASK_ERROR = '[MaintenanceRequest] Cancel MaintenanceRequest Task Error';
export const REMOVE_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] Remove maintenanceRequest task';
export const REMOVE_MAINTENANCE_REQUEST_TASK_SUCCESS = '[MaintenanceRequest] Remove maintenanceRequest task success';
export const REMOVE_MAINTENANCE_REQUEST_TASK_ERROR = '[MaintenanceRequest] Remove maintenanceRequest task error';

export const FIND_PAGED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find Paged MaintenanceRequests';
export const FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find Paged MaintenanceRequests Success';
export const FIND_PAGED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find Paged MaintenanceRequests Error';
export const FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO = '[MaintenanceRequest] Find MaintenanceRequest by reference_no';
export const FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_SUCCESS = '[MaintenanceRequest] Find MaintenanceRequest by reference no Success';
export const FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_ERROR = '[MaintenanceRequest] Find MaintenanceRequest by reference no Error';
export const UPDATE_MAINTENANCE_REQUEST = '[MaintenanceRequest] Update MaintenanceRequest';
export const UPDATE_MAINTENANCE_REQUEST_SUCCESS = '[MaintenanceRequest] Update MaintenanceRequest Success';
export const UPDATE_MAINTENANCE_REQUEST_ERROR = '[MaintenanceRequest] Update MaintenanceRequest Error';

export const SELECT_MAINTENANCE_REQUEST = '[MaintenanceRequest] Select maintenanceRequest';
export const NEW_MAINTENANCE_REQUEST_TASK = '[MaintenanceRequest] New MaintenanceRequest';
export const RELOAD_MAINTENANCE_REQUEST_PAGE = '[MaintenanceRequest] Reload maintenanceRequest page';

export class FindMaintenanceRequestByReferenceNoAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO;

    constructor(public referenceNo: string) {
    }
}

export class FindMaintenanceRequestByReferenceNoSuccessAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_SUCCESS;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class FindMaintenanceRequestByReferenceNoErrorAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_BY_REFERENCE_NO_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindAssignedMaintenanceRequestsAction implements Action {
    readonly type: string = FIND_ASSIGNED_MAINTENANCE_REQUESTS;

    constructor(public payload: { filter?: string, page: number }) {
    }
}

export class FindAssignedMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = FIND_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: MaintenanceRequestTaskSummaryResult) {
    }
}

export class FindAssignedMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_ASSIGNED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPooledMaintenanceRequestsAction implements Action {
    readonly type: string = FIND_POOLED_MAINTENANCE_REQUESTS;

    constructor(public payload: { filter?: string, page: number }) {
    }
}

export class FindPooledMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = FIND_POOLED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: MaintenanceRequestTaskSummaryResult) {
    }
}

export class FindPooledMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_POOLED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindArchivedMaintenanceRequestsAction implements Action {
    readonly type: string = FIND_ARCHIVED_MAINTENANCE_REQUESTS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindArchivedMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = FIND_ARCHIVED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: MaintenanceRequestRecordSummaryResult) {
    }
}

export class FindArchivedMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_ARCHIVED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedMaintenanceRequestsAction implements Action {
    readonly type: string = FIND_PAGED_MAINTENANCE_REQUESTS;

    constructor(public payload: { filter: string, page: number }) {

    }
}

export class FindPagedMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: MaintenanceRequestResult) {
        console.log(payload);

    }
}

export class FindPagedMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_PAGED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindMaintenanceRequestTaskByTaskIdAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID;

    constructor(public payload: { taskId: string }) {
    }
}

export class FindMaintenanceRequestTaskByTaskIdSuccessAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_SUCCESS;

    constructor(public payload: MaintenanceRequestTaskSummary) {
    }
}

export class FindMaintenanceRequestTaskByTaskIdErrorAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_TASK_BY_TASK_ID_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindMaintenanceRequestRecordByRecordIdAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID;

    constructor(public payload: { recordId: string }) {
    }
}

export class FindMaintenanceRequestRecordByRecordIdSuccessAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID_SUCCESS;

    constructor(public payload: MaintenanceRequestRecordSummary) {
    }
}

export class FindMaintenanceRequestRecordByRecordIdErrorAction implements Action {
    readonly type: string = FIND_MAINTENANCE_REQUEST_RECORD_BY_RECORD_ID_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CountAssignedMaintenanceRequestsAction implements Action {
    readonly type: string = COUNT_ASSIGNED_MAINTENANCE_REQUESTS;

    constructor() {
    }
}

export class CountAssignedMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = COUNT_ASSIGNED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: any) {
    }
}

export class CountAssignedMaintenanceRequestsErrorAction implements Action {
    readonly type: string = COUNT_ASSIGNED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CountPooledMaintenanceRequestsAction implements Action {
    readonly type: string = COUNT_POOLED_MAINTENANCE_REQUESTS;

    constructor() {
    }
}

export class CountPooledMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = COUNT_POOLED_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: number) {
    }
}

export class CountPooledMaintenanceRequestsErrorAction implements Action {
    readonly type: string = COUNT_POOLED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class ClaimMaintenanceRequestTaskAction implements Action {
    readonly type: string = CLAIM_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: { taskIds: string[] }) {
    }
}

export class ClaimMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = CLAIM_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class ReleaseMaintenanceRequestTaskAction implements Action {
    readonly type: string = RELEASE_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class ReleaseMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = RELEASE_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CompleteMaintenanceRequestTaskAction implements Action {
    readonly type: string = COMPLETE_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class CompleteMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = COMPLETE_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CompleteMaintenanceRequestTaskErrorAction implements Action {
    readonly type: string = COMPLETE_MAINTENANCE_REQUEST_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class CancelMaintenanceRequestTaskAction implements Action {
    readonly type: string = CANCEL_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: MaintenanceRequestTaskSummary) {
    }
}

export class CancelMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = CANCEL_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class CancelMaintenanceRequestTaskErrorAction implements Action {
    readonly type: string = CANCEL_MAINTENANCE_REQUEST_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class StartMaintenanceRequestTaskAction implements Action {
    readonly type: string = START_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class StartMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = START_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class StartMaintenanceRequestTaskErrorAction implements Action {
    readonly type: string = START_MAINTENANCE_REQUEST_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateMaintenanceRequestAction implements Action {
    readonly type: string = UPDATE_MAINTENANCE_REQUEST;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class UpdateMaintenanceRequestSuccessAction implements Action {
    readonly type: string = UPDATE_MAINTENANCE_REQUEST_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateMaintenanceRequestErrorAction implements Action {
    readonly type: string = UPDATE_MAINTENANCE_REQUEST_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveMaintenanceRequestTaskAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST_TASK;

    constructor(public payload: { taskId: string }) {
    }
}

export class RemoveMaintenanceRequestTaskSuccessAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST_TASK_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveMaintenanceRequestTaskErrorAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST_TASK_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class NewMaintenanceRequestTaskAction implements Action {
    readonly type: string = NEW_MAINTENANCE_REQUEST_TASK;

    constructor() {
    }
}

export class SelectMaintenanceRequestAction implements Action {
    readonly type: string = SELECT_MAINTENANCE_REQUEST;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class ReloadMaintenanceRequestPageAction implements Action {
    readonly type: string = RELOAD_MAINTENANCE_REQUEST_PAGE;

    constructor() {
    }
}

