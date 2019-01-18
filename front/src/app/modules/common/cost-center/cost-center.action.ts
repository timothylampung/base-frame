import {Action} from "@ngrx/store";
import {CostCenter, CostCenterResult} from "./cost-center.model";
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_COST_CENTERS = '[CostCenter] Find All CostCenters';
export const FIND_ALL_COST_CENTERS_SUCCESS = '[CostCenter] Find All CostCenters Success';
export const FIND_ALL_COST_CENTERS_ERROR = '[CostCenter] Find All CostCenters Error';
export const FIND_PAGED_COST_CENTERS = '[CostCenter] Find Paged CostCenters';
export const FIND_PAGED_COST_CENTERS_SUCCESS = '[CostCenter] Find Paged CostCenters Success';
export const FIND_PAGED_COST_CENTERS_ERROR = '[CostCenter] Find Paged CostCenters Error';
export const SAVE_COST_CENTER = '[CostCenter] Save CostCenter';
export const SAVE_COST_CENTER_SUCCESS = '[CostCenter] Save CostCenter Success';
export const SAVE_COST_CENTER_ERROR = '[CostCenter] Save CostCenter Error';
export const UPDATE_COST_CENTER = '[CostCenter] Update CostCenter';
export const UPDATE_COST_CENTER_SUCCESS = '[CostCenter] Update CostCenter Success';
export const UPDATE_COST_CENTER_ERROR = '[CostCenter] Update CostCenter Error';
export const REMOVE_COST_CENTER = '[CostCenter] Remove CostCenter';
export const REMOVE_COST_CENTER_SUCCESS = '[CostCenter] Remove CostCenter Success';
export const REMOVE_COST_CENTER_ERROR = '[CostCenter] Remove CostCenter Error';

export class FindAllCostCentersAction implements Action {
    readonly type: string = FIND_ALL_COST_CENTERS;

    constructor() {
    }
}

export class FindAllCostCentersSuccessAction implements Action {
    readonly type: string = FIND_ALL_COST_CENTERS_SUCCESS;

    constructor(public payload: CostCenter[]) {
    }
}

export class FindAllCostCentersErrorAction implements Action {
    readonly type: string = FIND_ALL_COST_CENTERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedCostCentersAction implements Action {
    readonly type: string = FIND_PAGED_COST_CENTERS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedCostCentersSuccessAction implements Action {
    readonly type: string = FIND_PAGED_COST_CENTERS_SUCCESS;

    constructor(public payload: CostCenterResult) {
    }
}

export class FindPagedCostCentersErrorAction implements Action {
    readonly type: string = FIND_PAGED_COST_CENTERS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveCostCenterAction implements Action {
    readonly type: string = SAVE_COST_CENTER;

    constructor(public payload: CostCenter) {
    }
}

export class SaveCostCenterSuccessAction implements Action {
    readonly type: string = SAVE_COST_CENTER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveCostCenterErrorAction implements Action {
    readonly type: string = SAVE_COST_CENTER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateCostCenterAction implements Action {
    readonly type: string = UPDATE_COST_CENTER;

    constructor(public payload: CostCenter) {
    }
}

export class UpdateCostCenterSuccessAction implements Action {
    readonly type: string = UPDATE_COST_CENTER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateCostCenterErrorAction implements Action {
    readonly type: string = UPDATE_COST_CENTER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveCostCenterAction implements Action {
    readonly type: string = REMOVE_COST_CENTER;

    constructor(public payload: CostCenter) {
    }
}

export class RemoveCostCenterSuccessAction implements Action {
    readonly type: string = REMOVE_COST_CENTER_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveCostCenterErrorAction implements Action {
    readonly type: string = REMOVE_COST_CENTER_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type CostCenterActions
    = SaveCostCenterAction
    | SaveCostCenterSuccessAction
    | SaveCostCenterErrorAction
    | UpdateCostCenterAction
    | UpdateCostCenterSuccessAction
    | UpdateCostCenterErrorAction
    | RemoveCostCenterAction
    | RemoveCostCenterSuccessAction
    | RemoveCostCenterErrorAction;
