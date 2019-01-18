import {Action} from "@ngrx/store";
import {User} from "./user.model";
import {ApplicationError} from "../../../../models/application-error.model";
import {UserResult} from "./user-result.model";

export const FIND_ALL_USERS = '[User] Find All Users';
export const FIND_ALL_USERS_SUCCESS = '[User] Find All Users Success';
export const FIND_ALL_USERS_ERROR = '[User] Find All Users Error';
export const FIND_PAGED_USERS = '[User] Find Paged Users';
export const FIND_PAGED_USERS_SUCCESS = '[User] Find Paged Users Success';
export const FIND_PAGED_USERS_ERROR = '[User] Find Paged Users Error';
export const SAVE_USER = '[User] Save User';
export const SAVE_USER_SUCCESS = '[User] Save User Success';
export const SAVE_USER_ERROR = '[User] Save User Error';
export const UPDATE_USER = '[User] Update User';
export const UPDATE_USER_SUCCESS = '[User] Update User Success';
export const UPDATE_USER_ERROR = '[User] Update User Error';
export const REMOVE_USER = '[User] Remove User';
export const REMOVE_USER_SUCCESS = '[User] Remove User Success';
export const REMOVE_USER_ERROR = '[User] Remove User Error';

export class FindAllUsersAction implements Action {
    readonly type: string = FIND_ALL_USERS;

    constructor() {
    }
}

export class FindAllUsersSuccessAction implements Action {
    readonly type: string = FIND_ALL_USERS_SUCCESS;

    constructor(public payload: User[]) {
    }
}

export class FindAllUsersErrorAction implements Action {
    readonly type: string = FIND_ALL_USERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedUsersAction implements Action {
    readonly type: string = FIND_PAGED_USERS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedUsersSuccessAction implements Action {
    readonly type: string = FIND_PAGED_USERS_SUCCESS;

    constructor(public payload: UserResult) {
    }
}

export class FindPagedUsersErrorAction implements Action {
    readonly type: string = FIND_PAGED_USERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveUserAction implements Action {
    readonly type: string = SAVE_USER;

    constructor(public payload: User) {
    }
}

export class SaveUserSuccessAction implements Action {
    readonly type: string = SAVE_USER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveUserErrorAction implements Action {
    readonly type: string = SAVE_USER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateUserAction implements Action {
    readonly type: string = UPDATE_USER;

    constructor(public payload: User) {
    }
}

export class UpdateUserSuccessAction implements Action {
    readonly type: string = UPDATE_USER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateUserErrorAction implements Action {
    readonly type: string = UPDATE_USER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveUserAction implements Action {
    readonly type: string = REMOVE_USER;

    constructor(public payload: User) {
    }
}

export class RemoveUserSuccessAction implements Action {
    readonly type: string = REMOVE_USER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveUserErrorAction implements Action {
    readonly type: string = REMOVE_USER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type BankActions
    = SaveUserAction
    | SaveUserSuccessAction
    | SaveUserErrorAction
    | UpdateUserAction
    | UpdateUserSuccessAction
    | UpdateUserErrorAction
    | RemoveUserAction
    | RemoveUserSuccessAction
    | RemoveUserErrorAction;
