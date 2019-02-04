import {Action} from '@ngrx/store';
import {Staff, StaffResult} from './staff.model';
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_STAFFS = '[Staff] Find All Staffs';
export const FIND_ALL_STAFFS_SUCCESS = '[Staff] Find All Staffs Success';
export const FIND_ALL_STAFFS_ERROR = '[Staff] Find All Staffs Error';
export const FIND_PAGED_STAFFS = '[Staff] Find Paged Staffs';
export const FIND_PAGED_STAFFS_SUCCESS = '[Staff] Find Paged Staffs Success';
export const FIND_PAGED_STAFFS_ERROR = '[Staff] Find Paged Staffs Error';
export const SAVE_STAFF = '[Staff] Save Staff';
export const SAVE_STAFF_SUCCESS = '[Staff] Save Staff Success';
export const SAVE_STAFF_ERROR = '[Staff] Save Staff Error';
export const UPDATE_STAFF = '[Staff] Update Staff';
export const UPDATE_STAFF_SUCCESS = '[Staff] Update Staff Success';
export const UPDATE_STAFF_ERROR = '[Staff] Update Staff Error';
export const REMOVE_STAFF = '[Staff] Remove Staff';
export const REMOVE_STAFF_SUCCESS = '[Staff] Remove Staff Success';
export const REMOVE_STAFF_ERROR = '[Staff] Remove Staff Error';

export class FindAllStaffsAction implements Action {
    readonly type: string = FIND_ALL_STAFFS;

    constructor() {
    }
}

export class FindAllStaffsSuccessAction implements Action {
    readonly type: string = FIND_ALL_STAFFS_SUCCESS;

    constructor(public payload: Staff[]) {
        console.log(payload)
    }
}

export class FindAllStaffsErrorAction implements Action {
    readonly type: string = FIND_ALL_STAFFS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedStaffsAction implements Action {
    readonly type: string = FIND_PAGED_STAFFS;

    constructor(public payload: { filter: string, page: number }) {

    }
}

export class FindPagedStaffsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_STAFFS_SUCCESS;

    constructor(public payload: StaffResult) {
        console.log(payload)
    }
}

export class FindPagedStaffsErrorAction implements Action {
    readonly type: string = FIND_PAGED_STAFFS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveStaffAction implements Action {
    readonly type: string = SAVE_STAFF;

    constructor(public payload: Staff) {
    }
}

export class SaveStaffSuccessAction implements Action {
    readonly type: string = SAVE_STAFF_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveStaffErrorAction implements Action {
    readonly type: string = SAVE_STAFF_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateStaffAction implements Action {
    readonly type: string = UPDATE_STAFF;

    constructor(public payload: Staff) {
    }
}

export class UpdateStaffSuccessAction implements Action {
    readonly type: string = UPDATE_STAFF_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateStaffErrorAction implements Action {
    readonly type: string = UPDATE_STAFF_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveStaffAction implements Action {
    readonly type: string = REMOVE_STAFF;

    constructor(public payload: Staff) {
    }
}

export class RemoveStaffSuccessAction implements Action {
    readonly type: string = REMOVE_STAFF_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveStaffErrorAction implements Action {
    readonly type: string = REMOVE_STAFF_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type StaffActions
    = SaveStaffAction
    | SaveStaffSuccessAction
    | SaveStaffErrorAction
    | UpdateStaffAction
    | UpdateStaffSuccessAction
    | UpdateStaffErrorAction
    | RemoveStaffAction
    | RemoveStaffSuccessAction
    | RemoveStaffErrorAction;
