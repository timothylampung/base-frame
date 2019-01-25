import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {AssetCode, AssetCodeResult} from "./asset-code-model";

export const FIND_ALL_ASSET_CODES = '[AssetCode] Find All Asset Codes';
export const FIND_ALL_ASSET_CODES_SUCCESS = '[AssetCode] Find All Asset Codes Success';
export const FIND_ALL_ASSET_CODES_ERROR = '[AssetCode] Find All Asset Codes Error';
export const FIND_PAGED_ASSET_CODES = '[AssetCode] Find Paged Asset Codes';
export const FIND_PAGED_ASSET_CODES_SUCCESS = '[AssetCode] Find Paged Asset Codes Success';
export const FIND_PAGED_ASSET_CODES_ERROR = '[AssetCode] Find Paged Asset Codes Error';
export const SAVE_ASSET_CODE = '[AssetCode] Save Asset Code';
export const SAVE_ASSET_CODE_SUCCESS = '[AssetCode] Save Asset Code Success';
export const SAVE_ASSET_CODE_ERROR = '[AssetCode] Save Asset Code Error';
export const UPDATE_ASSET_CODE = '[AssetCode] Update Asset Code';
export const UPDATE_ASSET_CODE_SUCCESS = '[AssetCode] Update Asset Code Success';
export const UPDATE_ASSET_CODE_ERROR = '[AssetCode] Update Asset Code Error';
export const REMOVE_ASSET_CODE = '[AssetCode] Remove Asset Code';
export const REMOVE_ASSET_CODE_SUCCESS = '[AssetCode] Remove Asset Code Success';
export const REMOVE_ASSET_CODE_ERROR = '[AssetCode] Remove Asset Code Error';

export class FindAllAssetCodesAction implements Action {
    readonly type: string = FIND_ALL_ASSET_CODES;

    constructor() {
    }
}

export class FindAllAssetCodesSuccessAction implements Action {
    readonly type: string = FIND_ALL_ASSET_CODES_SUCCESS;

    constructor(public payload: AssetCode[]) {
    }
}

export class FindAllAssetCodesErrorAction implements Action {
    readonly type: string = FIND_ALL_ASSET_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedAssetCodesAction implements Action {
    readonly type: string = FIND_PAGED_ASSET_CODES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedAssetCodesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_ASSET_CODES_SUCCESS;

    constructor(public payload: AssetCodeResult) {
    }
}

export class FindPagedAssetCodesErrorAction implements Action {
    readonly type: string = FIND_PAGED_ASSET_CODES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveAssetCodeAction implements Action {
    readonly type: string = SAVE_ASSET_CODE;

    constructor(public payload: AssetCode) {
    }
}

export class SaveAssetCodeSuccessAction implements Action {
    readonly type: string = SAVE_ASSET_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveAssetCodeErrorAction implements Action {
    readonly type: string = SAVE_ASSET_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateAssetCodeAction implements Action {
    readonly type: string = UPDATE_ASSET_CODE;

    constructor(public payload: AssetCode) {
    }
}

export class UpdateAssetCodeSuccessAction implements Action {
    readonly type: string = UPDATE_ASSET_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateAssetCodeErrorAction implements Action {
    readonly type: string = UPDATE_ASSET_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveAssetCodeAction implements Action {
    readonly type: string = REMOVE_ASSET_CODE;

    constructor(public payload: AssetCode) {
    }
}

export class RemoveAssetCodeSuccessAction implements Action {
    readonly type: string = REMOVE_ASSET_CODE_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveAssetCodeErrorAction implements Action {
    readonly type: string = REMOVE_ASSET_CODE_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
