import {Action} from '@ngrx/store';
import {BankCode, BankCodeResult} from './bank-code.model';
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_BANK_CODES = '[BankCode] Find All Bank Codes';
export const FIND_ALL_BANK_CODES_SUCCESS = '[BankCode] Find All Bank Codes Success';
export const FIND_ALL_BANK_CODES_ERROR = '[BankCode] Find All Bank Codes Error';
export const FIND_PAGED_BANK_CODES = '[BankCode] Find Paged Bank Codes';
export const FIND_PAGED_BANK_CODES_SUCCESS = '[BankCode] Find Paged Bank Codes Success';
export const FIND_PAGED_BANK_CODES_ERROR = '[BankCode] Find Paged Bank Codes Error';
export const SAVE_BANK_CODE = '[BankCode] Save Bank Code';
export const SAVE_BANK_CODE_SUCCESS = '[BankCode] Save Bank Code Success';
export const SAVE_BANK_CODE_ERROR = '[BankCode] Save Bank Code Error';
export const UPDATE_BANK_CODE = '[BankCode] Update Bank Code';
export const UPDATE_BANK_CODE_SUCCESS = '[BankCode] Update Bank Code Success';
export const UPDATE_BANK_CODE_ERROR = '[BankCode] Update Bank Code Error';
export const REMOVE_BANK_CODE = '[BankCode] Remove Bank Code';
export const REMOVE_BANK_CODE_SUCCESS = '[BankCode] Remove Bank Code Success';
export const REMOVE_BANK_CODE_ERROR = '[BankCode] Remove Bank Code Error';

export class FindAllBankCodesAction implements Action {
    readonly type: string = FIND_ALL_BANK_CODES;

    constructor() {
    }
}

export class FindAllBankCodesSuccessAction implements Action {
    readonly type: string = FIND_ALL_BANK_CODES_SUCCESS;

    constructor(public payload: BankCode[]) {
    }
}

export class FindAllBankCodesErrorAction implements Action {
    readonly type: string = FIND_ALL_BANK_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedBankCodesAction implements Action {
    readonly type: string = FIND_PAGED_BANK_CODES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedBankCodesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_BANK_CODES_SUCCESS;

    constructor(public payload: BankCodeResult) {
    }
}

export class FindPagedBankCodesErrorAction implements Action {
    readonly type: string = FIND_PAGED_BANK_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveBankCodeAction implements Action {
    readonly type: string = SAVE_BANK_CODE;

    constructor(public payload: BankCode) {
    }
}

export class SaveBankCodeSuccessAction implements Action {
    readonly type: string = SAVE_BANK_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveBankCodeErrorAction implements Action {
    readonly type: string = SAVE_BANK_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateBankCodeAction implements Action {
    readonly type: string = UPDATE_BANK_CODE;

    constructor(public payload: BankCode) {
    }
}

export class UpdateBankCodeSuccessAction implements Action {
    readonly type: string = UPDATE_BANK_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateBankCodeErrorAction implements Action {
    readonly type: string = UPDATE_BANK_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveBankCodeAction implements Action {
    readonly type: string = REMOVE_BANK_CODE;

    constructor(public payload: BankCode) {
    }
}

export class RemoveBankCodeSuccessAction implements Action {
    readonly type: string = REMOVE_BANK_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveBankCodeErrorAction implements Action {
    readonly type: string = REMOVE_BANK_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
