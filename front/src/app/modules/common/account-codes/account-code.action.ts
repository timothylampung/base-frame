import {Action} from '@ngrx/store';
import {AccountCode, AccountCodeResult} from './account-code.model';
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_ACCOUNT_CODES = '[AccountCode] Find All Account Codes';
export const FIND_ALL_ACCOUNT_CODES_SUCCESS = '[AccountCode] Find All Account Codes Success';
export const FIND_ALL_ACCOUNT_CODES_ERROR = '[AccountCode] Find All Account Codes Error';
export const FIND_PAGED_ACCOUNT_CODES = '[AccountCode] Find Paged Account Codes';
export const FIND_PAGED_ACCOUNT_CODES_SUCCESS = '[AccountCode] Find Paged Account Codes Success';
export const FIND_PAGED_ACCOUNT_CODES_ERROR = '[AccountCode] Find Paged Account Codes Error';
export const SAVE_ACCOUNT_CODE = '[AccountCode] Save Account Code';
export const SAVE_ACCOUNT_CODE_SUCCESS = '[AccountCode] Save Account Code Success';
export const SAVE_ACCOUNT_CODE_ERROR = '[AccountCode] Save Account Code Error';
export const UPDATE_ACCOUNT_CODE = '[AccountCode] Update Account Code';
export const UPDATE_ACCOUNT_CODE_SUCCESS = '[AccountCode] Update Account Code Success';
export const UPDATE_ACCOUNT_CODE_ERROR = '[AccountCode] Update Account Code Error';
export const REMOVE_ACCOUNT_CODE = '[AccountCode] Remove Account Code';
export const REMOVE_ACCOUNT_CODE_SUCCESS = '[AccountCode] Remove Account Code Success';
export const REMOVE_ACCOUNT_CODE_ERROR = '[AccountCode] Remove Account Code Error';

export class FindAllAccountCodesAction implements Action {
    readonly type: string = FIND_ALL_ACCOUNT_CODES;

    constructor() {
    }
}

export class FindAllAccountCodesSuccessAction implements Action {
    readonly type: string = FIND_ALL_ACCOUNT_CODES_SUCCESS;

    constructor(public payload: AccountCode[]) {
    }
}

export class FindAllAccountCodesErrorAction implements Action {
    readonly type: string = FIND_ALL_ACCOUNT_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedAccountCodesAction implements Action {
    readonly type: string = FIND_PAGED_ACCOUNT_CODES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedAccountCodesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_ACCOUNT_CODES_SUCCESS;

    constructor(public payload: AccountCodeResult) {
    }
}

export class FindPagedAccountCodesErrorAction implements Action {
    readonly type: string = FIND_PAGED_ACCOUNT_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveAccountCodeAction implements Action {
    readonly type: string = SAVE_ACCOUNT_CODE;

    constructor(public payload: AccountCode) {
    }
}

export class SaveAccountCodeSuccessAction implements Action {
    readonly type: string = SAVE_ACCOUNT_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveAccountCodeErrorAction implements Action {
    readonly type: string = SAVE_ACCOUNT_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateAccountCodeAction implements Action {
    readonly type: string = UPDATE_ACCOUNT_CODE;

    constructor(public payload: AccountCode) {
    }
}

export class UpdateAccountCodeSuccessAction implements Action {
    readonly type: string = UPDATE_ACCOUNT_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateAccountCodeErrorAction implements Action {
    readonly type: string = UPDATE_ACCOUNT_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveAccountCodeAction implements Action {
    readonly type: string = REMOVE_ACCOUNT_CODE;

    constructor(public payload: AccountCode) {
    }
}

export class RemoveAccountCodeSuccessAction implements Action {
    readonly type: string = REMOVE_ACCOUNT_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveAccountCodeErrorAction implements Action {
    readonly type: string = REMOVE_ACCOUNT_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
