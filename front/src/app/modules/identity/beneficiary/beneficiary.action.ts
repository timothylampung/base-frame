import {Action} from "@ngrx/store";
import {Beneficiary, BeneficiaryResult} from "./beneficiary.model";
import {ApplicationError} from "../../../models/application-error.model";

export const FIND_ALL_BENEFICIARIES = '[Beneficiary] Find All Beneficiaries';
export const FIND_ALL_BENEFICIARIES_SUCCESS = '[Beneficiary] Find All Beneficiaries Success';
export const FIND_ALL_BENEFICIARIES_ERROR = '[Beneficiary] Find All Beneficiaries Error';
export const FIND_PAGED_BENEFICIARIES = '[Beneficiary] Find Paged Beneficiaries';
export const FIND_PAGED_BENEFICIARIES_SUCCESS = '[Beneficiary] Find Paged Beneficiaries Success';
export const FIND_PAGED_BENEFICIARIES_ERROR = '[Beneficiary] Find Paged Beneficiaries Error';
export const SAVE_BENEFICIARY = '[Beneficiary] Save Beneficiary';
export const SAVE_BENEFICIARY_SUCCESS = '[Beneficiary] Save Beneficiary Success';
export const SAVE_BENEFICIARY_ERROR = '[Beneficiary] Save Beneficiary Error';
export const UPDATE_BENEFICIARY = '[Beneficiary] Update Beneficiary';
export const UPDATE_BENEFICIARY_SUCCESS = '[Beneficiary] Update Beneficiary Success';
export const UPDATE_BENEFICIARY_ERROR = '[Beneficiary] Update Beneficiary Error';
export const REMOVE_BENEFICIARY = '[Beneficiary] Remove Beneficiary';
export const REMOVE_BENEFICIARY_SUCCESS = '[Beneficiary] Remove Beneficiary Success';
export const REMOVE_BENEFICIARY_ERROR = '[Beneficiary] Remove Beneficiary Error';

export class FindAllBeneficiariesAction implements Action {
    readonly type: string = FIND_ALL_BENEFICIARIES;

    constructor() {
    }
}

export class FindAllBeneficiariesSuccessAction implements Action {
    readonly type: string = FIND_ALL_BENEFICIARIES_SUCCESS;

    constructor(public payload: Beneficiary[]) {
    }
}

export class FindAllBeneficiariesErrorAction implements Action {
    readonly type: string = FIND_ALL_BENEFICIARIES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedBeneficiariesAction implements Action {
    readonly type: string = FIND_PAGED_BENEFICIARIES;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedBeneficiariesSuccessAction implements Action {
    readonly type: string = FIND_PAGED_BENEFICIARIES_SUCCESS;

    constructor(public payload: BeneficiaryResult) {
    }
}

export class FindPagedBeneficiariesErrorAction implements Action {
    readonly type: string = FIND_PAGED_BENEFICIARIES_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveBeneficiaryAction implements Action {
    readonly type: string = SAVE_BENEFICIARY;

    constructor(public payload: Beneficiary) {
    }
}

export class SaveBeneficiarySuccessAction implements Action {
    readonly type: string = SAVE_BENEFICIARY_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveBeneficiaryErrorAction implements Action {
    readonly type: string = SAVE_BENEFICIARY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateBeneficiaryAction implements Action {
    readonly type: string = UPDATE_BENEFICIARY;

    constructor(public payload: Beneficiary) {
    }
}

export class UpdateBeneficiarySuccessAction implements Action {
    readonly type: string = UPDATE_BENEFICIARY_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateBeneficiaryErrorAction implements Action {
    readonly type: string = UPDATE_BENEFICIARY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveBeneficiaryAction implements Action {
    readonly type: string = REMOVE_BENEFICIARY;

    constructor(public payload: Beneficiary) {
    }
}

export class RemoveBeneficiarySuccessAction implements Action {
    readonly type: string = REMOVE_BENEFICIARY_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveBeneficiaryErrorAction implements Action {
    readonly type: string = REMOVE_BENEFICIARY_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type BeneficiaryActions
    = SaveBeneficiaryAction
    | SaveBeneficiarySuccessAction
    | SaveBeneficiaryErrorAction
    | UpdateBeneficiaryAction
    | UpdateBeneficiarySuccessAction
    | UpdateBeneficiaryErrorAction
    | RemoveBeneficiaryAction
    | RemoveBeneficiarySuccessAction
    | RemoveBeneficiaryErrorAction;
