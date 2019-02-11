import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {PartCode, PartCodeResult} from "./part-code-model";

export const FIND_ALL_PART_CODES = '[PartCode] Find All PartCodes';
export const FIND_ALL_PART_CODES_SUCCESS = '[PartCode] Find All PartCodes Success';
export const FIND_ALL_PART_CODES_ERROR = '[PartCode] Find All PartCodes Error';
export const FIND_PAGED_PART_CODES = '[PartCode] Find Paged PartCodes';
export const FIND_PAGED_PART_CODES_SUCCESS = '[PartCode] Find Paged PartCodes Success';
export const FIND_PAGED_PART_CODES_ERROR = '[PartCode] Find Paged PartCodes Error';
export const SAVE_PART_CODE = '[PartCode] Save PartCode';
export const SAVE_PART_CODE_SUCCESS = '[PartCode] Save PartCode Success';
export const SAVE_PART_CODE_ERROR = '[PartCode] Save PartCode Error';
export const UPDATE_PART_CODE = '[PartCode] Update PartCode';
export const UPDATE_PART_CODE_SUCCESS = '[PartCode] Update PartCode Success';
export const UPDATE_PART_CODE_ERROR = '[PartCode] Update PartCode Error';
export const REMOVE_PART_CODE = '[PartCode] Remove PartCode';
export const REMOVE_PART_CODE_SUCCESS = '[PartCode] Remove PartCode Success';
export const REMOVE_PART_CODE_ERROR = '[PartCode] Remove PartCode Error';

export class FindAllPartCodesAction implements Action {
    readonly type: string = FIND_ALL_PART_CODES;

    constructor() {
    }
}

export class FindAllPartCodesSuccessAction implements Action {
    readonly type: string = FIND_ALL_PART_CODES_SUCCESS;

    constructor(public payload: PartCode[]) {
    }
}

export class FindAllPartCodesErrorAction implements Action {
    readonly type: string = FIND_ALL_PART_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedPartCodesAction implements Action {
    readonly type: string = FIND_PAGED_PART_CODES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedPartCodesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_PART_CODES_SUCCESS;

    constructor(public payload: PartCodeResult) {
    }
}

export class FindPagedPartCodesErrorAction implements Action {
    readonly type: string = FIND_PAGED_PART_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SavePartCodeAction implements Action {
    readonly type: string = SAVE_PART_CODE;

    constructor(public payload: PartCode) {
    }
}

export class SavePartCodeSuccessAction implements Action {
    readonly type: string = SAVE_PART_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SavePartCodeErrorAction implements Action {
    readonly type: string = SAVE_PART_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdatePartCodeAction implements Action {
    readonly type: string = UPDATE_PART_CODE;

    constructor(public payload: PartCode) {
    }
}

export class UpdatePartCodeSuccessAction implements Action {
    readonly type: string = UPDATE_PART_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdatePartCodeErrorAction implements Action {
    readonly type: string = UPDATE_PART_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemovePartCodeAction implements Action {
    readonly type: string = REMOVE_PART_CODE;

    constructor(public payload: PartCode) {
    }
}

export class RemovePartCodeSuccessAction implements Action {
    readonly type: string = REMOVE_PART_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemovePartCodeErrorAction implements Action {
    readonly type: string = REMOVE_PART_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
