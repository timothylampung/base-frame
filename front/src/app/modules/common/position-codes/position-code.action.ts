import {Action} from '@ngrx/store';
import {PositionCode, PositionCodeResult} from './position-code.model';
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_PAGED_POSITION_CODES = '[PositionCode] Find Paged PositionCodes';
export const FIND_PAGED_POSITION_CODES_SUCCESS = '[PositionCode] Find Paged PositionCodes Success';
export const FIND_PAGED_POSITION_CODES_ERROR = '[PositionCode] Find Paged PositionCodes Error';
export const SAVE_POSITION_CODE = '[PositionCode] Save PositionCode';
export const SAVE_POSITION_CODE_SUCCESS = '[PositionCode] Save PositionCode Success';
export const SAVE_POSITION_CODE_ERROR = '[PositionCode] Save PositionCode Error';
export const UPDATE_POSITION_CODE = '[PositionCode] Update PositionCode';
export const UPDATE_POSITION_CODE_SUCCESS = '[PositionCode] Update PositionCode Success';
export const UPDATE_POSITION_CODE_ERROR = '[PositionCode] Update PositionCode Error';
export const REMOVE_POSITION_CODE = '[PositionCode] Remove PositionCode';
export const REMOVE_POSITION_CODE_SUCCESS = '[PositionCode] Remove PositionCode Success';
export const REMOVE_POSITION_CODE_ERROR = '[PositionCode] Remove PositionCode Error';

export class FindPagedPositionCodesAction implements Action {
    readonly type: string = FIND_PAGED_POSITION_CODES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedPositionCodesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_POSITION_CODES_SUCCESS;

    constructor(public payload: PositionCodeResult) {
    }
}

export class FindPagedPositionCodesErrorAction implements Action {
    readonly type: string = FIND_PAGED_POSITION_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SavePositionCodeAction implements Action {
    readonly type: string = SAVE_POSITION_CODE;

    constructor(public payload: PositionCode) {
    }
}

export class SavePositionCodeSuccessAction implements Action {
    readonly type: string = SAVE_POSITION_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SavePositionCodeErrorAction implements Action {
    readonly type: string = SAVE_POSITION_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdatePositionCodeAction implements Action {
    readonly type: string = UPDATE_POSITION_CODE;

    constructor(public payload: PositionCode) {
    }
}

export class UpdatePositionCodeSuccessAction implements Action {
    readonly type: string = UPDATE_POSITION_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdatePositionCodeErrorAction implements Action {
    readonly type: string = UPDATE_POSITION_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemovePositionCodeAction implements Action {
    readonly type: string = REMOVE_POSITION_CODE;

    constructor(public payload: PositionCode) {
    }
}

export class RemovePositionCodeSuccessAction implements Action {
    readonly type: string = REMOVE_POSITION_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemovePositionCodeErrorAction implements Action {
    readonly type: string = REMOVE_POSITION_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type PositionCodeActions
    = SavePositionCodeAction
    | SavePositionCodeSuccessAction
    | SavePositionCodeErrorAction
    | UpdatePositionCodeAction
    | UpdatePositionCodeSuccessAction
    | UpdatePositionCodeErrorAction
    | RemovePositionCodeAction
    | RemovePositionCodeSuccessAction
    | RemovePositionCodeErrorAction;
