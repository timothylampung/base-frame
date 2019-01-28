import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {MaintenanceRequest, MaintenanceRequestResult} from "./maintenance-request.model";

export const FIND_ALL_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find All Maintenance Requests';
export const FIND_ALL_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find All Maintenance Requests Success';
export const FIND_ALL_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find All Maintenance Requests Error';
export const FIND_PAGED_MAINTENANCE_REQUESTS = '[MaintenanceRequest] Find Paged Maintenance Requests';
export const FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS = '[MaintenanceRequest] Find Paged Maintenance Requests Success';
export const FIND_PAGED_MAINTENANCE_REQUESTS_ERROR = '[MaintenanceRequest] Find Paged Maintenance Requests Error';
export const SAVE_MAINTENANCE_REQUEST = '[MaintenanceRequest] Save Maintenance Request';
export const SAVE_MAINTENANCE_REQUEST_SUCCESS = '[MaintenanceRequest] Save Maintenance Request Success';
export const SAVE_MAINTENANCE_REQUEST_ERROR = '[MaintenanceRequest] Save Maintenance Request Error';
export const UPDATE_MAINTENANCE_REQUEST = '[MaintenanceRequest] Update Maintenance Request';
export const UPDATE_MAINTENANCE_REQUEST_SUCCESS = '[MaintenanceRequest] Update Maintenance Request Success';
export const UPDATE_MAINTENANCE_REQUEST_ERROR = '[MaintenanceRequest] Update Maintenance Request Error';
export const REMOVE_MAINTENANCE_REQUEST = '[MaintenanceRequest] Remove Maintenance Request';
export const REMOVE_MAINTENANCE_REQUEST_SUCCESS = '[MaintenanceRequest] Remove Maintenance Request Success';
export const REMOVE_MAINTENANCE_REQUEST_ERROR = '[MaintenanceRequest] Remove Maintenance Request Error';

export class FindAllMaintenanceRequestsAction implements Action {
    readonly type: string = FIND_ALL_MAINTENANCE_REQUESTS;

    constructor() {
    }
}

export class FindAllMaintenanceRequestsSuccessAction implements Action {
    readonly type: string = FIND_ALL_MAINTENANCE_REQUESTS_SUCCESS;

    constructor(public payload: MaintenanceRequest[]) {
    }
}

export class FindAllMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_ALL_MAINTENANCE_REQUESTS_ERROR;

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
    }
}

export class FindPagedMaintenanceRequestsErrorAction implements Action {
    readonly type: string = FIND_PAGED_MAINTENANCE_REQUESTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveMaintenanceRequestAction implements Action {
    readonly type: string = SAVE_MAINTENANCE_REQUEST;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class SaveMaintenanceRequestSuccessAction implements Action {
    readonly type: string = SAVE_MAINTENANCE_REQUEST_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveMaintenanceRequestErrorAction implements Action {
    readonly type: string = SAVE_MAINTENANCE_REQUEST_ERROR;

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

export class RemoveMaintenanceRequestAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST;

    constructor(public payload: MaintenanceRequest) {
    }
}

export class RemoveMaintenanceRequestSuccessAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveMaintenanceRequestErrorAction implements Action {
    readonly type: string = REMOVE_MAINTENANCE_REQUEST_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
