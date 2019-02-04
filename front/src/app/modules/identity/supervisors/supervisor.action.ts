import {Action} from "@ngrx/store";
import {ApplicationError} from "../../../models/application-error.model";
import {Supervisor, SupervisorResult} from "./supervisor.model";

export const FIND_ALL_SUPERVISORS = '[Supervisor] Find All Supervisors';
export const FIND_ALL_SUPERVISORS_SUCCESS = '[Supervisor] Find All Supervisors Success';
export const FIND_ALL_SUPERVISORS_ERROR = '[Supervisor] Find All Supervisors Error';
export const FIND_PAGED_SUPERVISORS = '[Supervisor] Find Paged Supervisors';
export const FIND_PAGED_SUPERVISORS_SUCCESS = '[Supervisor] Find Paged Supervisors Success';
export const FIND_PAGED_SUPERVISORS_ERROR = '[Supervisor] Find Paged Supervisors Error';
export const SAVE_SUPERVISOR = '[Supervisor] Save Supervisor';
export const SAVE_SUPERVISOR_SUCCESS = '[Supervisor] Save Supervisor Success';
export const SAVE_SUPERVISOR_ERROR = '[Supervisor] Save Supervisor Error';
export const UPDATE_SUPERVISOR = '[Supervisor] Update Supervisor';
export const UPDATE_SUPERVISOR_SUCCESS = '[Supervisor] Update Supervisor Success';
export const UPDATE_SUPERVISOR_ERROR = '[Supervisor] Update Supervisor Error';
export const REMOVE_SUPERVISOR = '[Supervisor] Remove Supervisor';
export const REMOVE_SUPERVISOR_SUCCESS = '[Supervisor] Remove Supervisor Success';
export const REMOVE_SUPERVISOR_ERROR = '[Supervisor] Remove Supervisor Error';

export class FindAllSupervisorsAction implements Action {
    readonly type: string = FIND_ALL_SUPERVISORS;

    constructor() {
    }
}

export class FindAllSupervisorsSuccessAction implements Action {
    readonly type: string = FIND_ALL_SUPERVISORS_SUCCESS;

    constructor(public payload: Supervisor[]) {
    }
}

export class FindAllSupervisorsErrorAction implements Action {
    readonly type: string = FIND_ALL_SUPERVISORS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedSupervisorsAction implements Action {
    readonly type: string = FIND_PAGED_SUPERVISORS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedSupervisorsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_SUPERVISORS_SUCCESS;

    constructor(public payload: SupervisorResult) {
    }
}

export class FindPagedSupervisorsErrorAction implements Action {
    readonly type: string = FIND_PAGED_SUPERVISORS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveSupervisorAction implements Action {
    readonly type: string = SAVE_SUPERVISOR;

    constructor(public payload: Supervisor) {
    }
}

export class SaveSupervisorSuccessAction implements Action {
    readonly type: string = SAVE_SUPERVISOR_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveSupervisorErrorAction implements Action {
    readonly type: string = SAVE_SUPERVISOR_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateSupervisorAction implements Action {
    readonly type: string = UPDATE_SUPERVISOR;

    constructor(public payload: Supervisor) {
    }
}

export class UpdateSupervisorSuccessAction implements Action {
    readonly type: string = UPDATE_SUPERVISOR_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateSupervisorErrorAction implements Action {
    readonly type: string = UPDATE_SUPERVISOR_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveSupervisorAction implements Action {
    readonly type: string = REMOVE_SUPERVISOR;

    constructor(public payload: Supervisor) {
    }
}

export class RemoveSupervisorSuccessAction implements Action {
    readonly type: string = REMOVE_SUPERVISOR_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveSupervisorErrorAction implements Action {
    readonly type: string = REMOVE_SUPERVISOR_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type SupervisorActions
    = SaveSupervisorAction
    | SaveSupervisorSuccessAction
    | SaveSupervisorErrorAction
    | UpdateSupervisorAction
    | UpdateSupervisorSuccessAction
    | UpdateSupervisorErrorAction
    | RemoveSupervisorAction
    | RemoveSupervisorSuccessAction
    | RemoveSupervisorErrorAction;
