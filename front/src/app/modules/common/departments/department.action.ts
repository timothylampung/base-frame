import {Action} from '@ngrx/store';
import {Department, DepartmentResult} from './department.model';
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_DEPARTMENTS = '[Department] Find All Departments';
export const FIND_ALL_DEPARTMENTS_SUCCESS = '[Department] Find All Departments Success';
export const FIND_ALL_DEPARTMENTS_ERROR = '[Department] Find All Departments Error';
export const FIND_PAGED_DEPARTMENTS = '[Department] Find Paged Departments';
export const FIND_PAGED_DEPARTMENTS_SUCCESS = '[Department] Find Paged Departments Success';
export const FIND_PAGED_DEPARTMENTS_ERROR = '[Department] Find Paged Departments Error';
export const SAVE_DEPARTMENT = '[Department] Save Department';
export const SAVE_DEPARTMENT_SUCCESS = '[Department] Save Department Success';
export const SAVE_DEPARTMENT_ERROR = '[Department] Save Department Error';
export const UPDATE_DEPARTMENT = '[Department] Update Department';
export const UPDATE_DEPARTMENT_SUCCESS = '[Department] Update Department Success';
export const UPDATE_DEPARTMENT_ERROR = '[Department] Update Department Error';
export const REMOVE_DEPARTMENT = '[Department] Remove Department';
export const REMOVE_DEPARTMENT_SUCCESS = '[Department] Remove Department Success';
export const REMOVE_DEPARTMENT_ERROR = '[Department] Remove Department Error';

export class FindAllDepartmentsAction implements Action {
    readonly type: string = FIND_ALL_DEPARTMENTS;

    constructor() {
    }
}

export class FindAllDepartmentsSuccessAction implements Action {
    readonly type: string = FIND_ALL_DEPARTMENTS_SUCCESS;

    constructor(public payload: Department[]) {
    }
}

export class FindAllDepartmentsErrorAction implements Action {
    readonly type: string = FIND_ALL_DEPARTMENTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedDepartmentsAction implements Action {
    readonly type: string = FIND_PAGED_DEPARTMENTS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedDepartmentsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_DEPARTMENTS_SUCCESS;

    constructor(public payload: DepartmentResult) {
    }
}

export class FindPagedDepartmentsErrorAction implements Action {
    readonly type: string = FIND_PAGED_DEPARTMENTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveDepartmentAction implements Action {
    readonly type: string = SAVE_DEPARTMENT;

    constructor(public payload: Department) {
    }
}

export class SaveDepartmentSuccessAction implements Action {
    readonly type: string = SAVE_DEPARTMENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveDepartmentErrorAction implements Action {
    readonly type: string = SAVE_DEPARTMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateDepartmentAction implements Action {
    readonly type: string = UPDATE_DEPARTMENT;

    constructor(public payload: Department) {
    }
}

export class UpdateDepartmentSuccessAction implements Action {
    readonly type: string = UPDATE_DEPARTMENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateDepartmentErrorAction implements Action {
    readonly type: string = UPDATE_DEPARTMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveDepartmentAction implements Action {
    readonly type: string = REMOVE_DEPARTMENT;

    constructor(public payload: Department) {
    }
}

export class RemoveDepartmentSuccessAction implements Action {
    readonly type: string = REMOVE_DEPARTMENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveDepartmentErrorAction implements Action {
    readonly type: string = REMOVE_DEPARTMENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type DepartmentActions
    = SaveDepartmentAction
    | SaveDepartmentSuccessAction
    | SaveDepartmentErrorAction
    | UpdateDepartmentAction
    | UpdateDepartmentSuccessAction
    | UpdateDepartmentErrorAction
    | RemoveDepartmentAction
    | RemoveDepartmentSuccessAction
    | RemoveDepartmentErrorAction;
