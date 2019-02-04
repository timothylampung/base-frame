import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {FacilityManager, FacilityManagerResult} from "./facility-manager.model";

export const FIND_ALL_FACILITY_MANAGERS = '[FacilityManager] Find All FacilityManagers';
export const FIND_ALL_FACILITY_MANAGERS_SUCCESS = '[FacilityManager] Find All FacilityManagers Success';
export const FIND_ALL_FACILITY_MANAGERS_ERROR = '[FacilityManager] Find All FacilityManagers Error';
export const FIND_PAGED_FACILITY_MANAGERS = '[FacilityManager] Find Paged FacilityManagers';
export const FIND_PAGED_FACILITY_MANAGERS_SUCCESS = '[FacilityManager] Find Paged FacilityManagers Success';
export const FIND_PAGED_FACILITY_MANAGERS_ERROR = '[FacilityManager] Find Paged FacilityManagers Error';
export const SAVE_FACILITY_MANAGER = '[FacilityManager] Save FacilityManager';
export const SAVE_FACILITY_MANAGER_SUCCESS = '[FacilityManager] Save FacilityManager Success';
export const SAVE_FACILITY_MANAGER_ERROR = '[FacilityManager] Save FacilityManager Error';
export const UPDATE_FACILITY_MANAGER = '[FacilityManager] Update FacilityManager';
export const UPDATE_FACILITY_MANAGER_SUCCESS = '[FacilityManager] Update FacilityManager Success';
export const UPDATE_FACILITY_MANAGER_ERROR = '[FacilityManager] Update FacilityManager Error';
export const REMOVE_FACILITY_MANAGER = '[FacilityManager] Remove FacilityManager';
export const REMOVE_FACILITY_MANAGER_SUCCESS = '[FacilityManager] Remove FacilityManager Success';
export const REMOVE_FACILITY_MANAGER_ERROR = '[FacilityManager] Remove FacilityManager Error';

export class FindAllFacilityManagersAction implements Action {
    readonly type: string = FIND_ALL_FACILITY_MANAGERS;

    constructor() {
    }
}

export class FindAllFacilityManagersSuccessAction implements Action {
    readonly type: string = FIND_ALL_FACILITY_MANAGERS_SUCCESS;

    constructor(public payload: FacilityManager[]) {
        console.log(payload)
    }
}

export class FindAllFacilityManagersErrorAction implements Action {
    readonly type: string = FIND_ALL_FACILITY_MANAGERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedFacilityManagersAction implements Action {
    readonly type: string = FIND_PAGED_FACILITY_MANAGERS;

    constructor(public payload: { filter: string, page: number }) {

    }
}

export class FindPagedFacilityManagersSuccessAction implements Action {
    readonly type: string = FIND_PAGED_FACILITY_MANAGERS_SUCCESS;

    constructor(public payload: FacilityManagerResult) {
        console.log(payload)
    }
}

export class FindPagedFacilityManagersErrorAction implements Action {
    readonly type: string = FIND_PAGED_FACILITY_MANAGERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveFacilityManagerAction implements Action {
    readonly type: string = SAVE_FACILITY_MANAGER;

    constructor(public payload: FacilityManager) {
    }
}

export class SaveFacilityManagerSuccessAction implements Action {
    readonly type: string = SAVE_FACILITY_MANAGER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveFacilityManagerErrorAction implements Action {
    readonly type: string = SAVE_FACILITY_MANAGER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateFacilityManagerAction implements Action {
    readonly type: string = UPDATE_FACILITY_MANAGER;

    constructor(public payload: FacilityManager) {
    }
}

export class UpdateFacilityManagerSuccessAction implements Action {
    readonly type: string = UPDATE_FACILITY_MANAGER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateFacilityManagerErrorAction implements Action {
    readonly type: string = UPDATE_FACILITY_MANAGER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveFacilityManagerAction implements Action {
    readonly type: string = REMOVE_FACILITY_MANAGER;

    constructor(public payload: FacilityManager) {
    }
}

export class RemoveFacilityManagerSuccessAction implements Action {
    readonly type: string = REMOVE_FACILITY_MANAGER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveFacilityManagerErrorAction implements Action {
    readonly type: string = REMOVE_FACILITY_MANAGER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type FacilityManagerActions
    = SaveFacilityManagerAction
    | SaveFacilityManagerSuccessAction
    | SaveFacilityManagerErrorAction
    | UpdateFacilityManagerAction
    | UpdateFacilityManagerSuccessAction
    | UpdateFacilityManagerErrorAction
    | RemoveFacilityManagerAction
    | RemoveFacilityManagerSuccessAction
    | RemoveFacilityManagerErrorAction;
