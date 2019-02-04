import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {Asset, AssetResult} from "./asset-model";

export const FIND_ALL_ASSETS = '[Asset] Find All Assets';
export const FIND_ALL_ASSETS_SUCCESS = '[Asset] Find All Assets Success';
export const FIND_ALL_ASSETS_ERROR = '[Asset] Find All Assets Error';
export const FIND_PAGED_ASSETS = '[Asset] Find Paged Assets';
export const FIND_PAGED_ASSETS_SUCCESS = '[Asset] Find Paged Assets Success';
export const FIND_PAGED_ASSETS_ERROR = '[Asset] Find Paged Assets Error';
export const SAVE_ASSET = '[Asset] Save Asset';
export const SAVE_ASSET_SUCCESS = '[Asset] Save Asset Success';
export const SAVE_ASSET_ERROR = '[Asset] Save Asset Error';
export const UPDATE_ASSET = '[Asset] Update Asset';
export const UPDATE_ASSET_SUCCESS = '[Asset] Update Asset Success';
export const UPDATE_ASSET_ERROR = '[Asset] Update Asset Error';
export const REMOVE_ASSET = '[Asset] Remove Asset';
export const REMOVE_ASSET_SUCCESS = '[Asset] Remove Asset Success';
export const REMOVE_ASSET_ERROR = '[Asset] Remove Asset Error';

export class FindAllAssetsAction implements Action {
    readonly type: string = FIND_ALL_ASSETS;

    constructor() {
    }
}

export class FindAllAssetsSuccessAction implements Action {
    readonly type: string = FIND_ALL_ASSETS_SUCCESS;

    constructor(public payload: Asset[]) {
    }
}

export class FindAllAssetsErrorAction implements Action {
    readonly type: string = FIND_ALL_ASSETS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedAssetsAction implements Action {
    readonly type: string = FIND_PAGED_ASSETS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedAssetsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_ASSETS_SUCCESS;

    constructor(public payload: AssetResult) {
    }
}

export class FindPagedAssetsErrorAction implements Action {
    readonly type: string = FIND_PAGED_ASSETS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveAssetAction implements Action {
    readonly type: string = SAVE_ASSET;

    constructor(public payload: Asset) {
    }
}

export class SaveAssetSuccessAction implements Action {
    readonly type: string = SAVE_ASSET_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveAssetErrorAction implements Action {
    readonly type: string = SAVE_ASSET_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateAssetAction implements Action {
    readonly type: string = UPDATE_ASSET;

    constructor(public payload: Asset) {
    }
}

export class UpdateAssetSuccessAction implements Action {
    readonly type: string = UPDATE_ASSET_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateAssetErrorAction implements Action {
    readonly type: string = UPDATE_ASSET_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveAssetAction implements Action {
    readonly type: string = REMOVE_ASSET;

    constructor(public payload: Asset) {
    }
}

export class RemoveAssetSuccessAction implements Action {
    readonly type: string = REMOVE_ASSET_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveAssetErrorAction implements Action {
    readonly type: string = REMOVE_ASSET_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
