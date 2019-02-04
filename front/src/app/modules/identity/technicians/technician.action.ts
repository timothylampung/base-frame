import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {Technician, TechnicianResult} from "./technician.model";

export const FIND_ALL_TECHNICIANS = '[Technician] Find All Technicians';
export const FIND_ALL_TECHNICIANS_SUCCESS = '[Technician] Find All Technicians Success';
export const FIND_ALL_TECHNICIANS_ERROR = '[Technician] Find All Technicians Error';
export const FIND_PAGED_TECHNICIANS = '[Technician] Find Paged Technicians';
export const FIND_PAGED_TECHNICIANS_SUCCESS = '[Technician] Find Paged Technicians Success';
export const FIND_PAGED_TECHNICIANS_ERROR = '[Technician] Find Paged Technicians Error';
export const SAVE_TECHNICIAN = '[Technician] Save Technician';
export const SAVE_TECHNICIAN_SUCCESS = '[Technician] Save Technician Success';
export const SAVE_TECHNICIAN_ERROR = '[Technician] Save Technician Error';
export const UPDATE_TECHNICIAN = '[Technician] Update Technician';
export const UPDATE_TECHNICIAN_SUCCESS = '[Technician] Update Technician Success';
export const UPDATE_TECHNICIAN_ERROR = '[Technician] Update Technician Error';
export const REMOVE_TECHNICIAN = '[Technician] Remove Technician';
export const REMOVE_TECHNICIAN_SUCCESS = '[Technician] Remove Technician Success';
export const REMOVE_TECHNICIAN_ERROR = '[Technician] Remove Technician Error';

export class FindAllTechniciansAction implements Action {
    readonly type: string = FIND_ALL_TECHNICIANS;

    constructor() {
    }
}

export class FindAllTechniciansSuccessAction implements Action {
    readonly type: string = FIND_ALL_TECHNICIANS_SUCCESS;

    constructor(public payload: Technician[]) {
        console.log(payload)
    }
}

export class FindAllTechniciansErrorAction implements Action {
    readonly type: string = FIND_ALL_TECHNICIANS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedTechniciansAction implements Action {
    readonly type: string = FIND_PAGED_TECHNICIANS;

    constructor(public payload: { filter: string, page: number }) {

    }
}

export class FindPagedTechniciansSuccessAction implements Action {
    readonly type: string = FIND_PAGED_TECHNICIANS_SUCCESS;

    constructor(public payload: TechnicianResult) {
        console.log(payload)
    }
}

export class FindPagedTechniciansErrorAction implements Action {
    readonly type: string = FIND_PAGED_TECHNICIANS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveTechnicianAction implements Action {
    readonly type: string = SAVE_TECHNICIAN;

    constructor(public payload: Technician) {
    }
}

export class SaveTechnicianSuccessAction implements Action {
    readonly type: string = SAVE_TECHNICIAN_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveTechnicianErrorAction implements Action {
    readonly type: string = SAVE_TECHNICIAN_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateTechnicianAction implements Action {
    readonly type: string = UPDATE_TECHNICIAN;

    constructor(public payload: Technician) {
    }
}

export class UpdateTechnicianSuccessAction implements Action {
    readonly type: string = UPDATE_TECHNICIAN_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateTechnicianErrorAction implements Action {
    readonly type: string = UPDATE_TECHNICIAN_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveTechnicianAction implements Action {
    readonly type: string = REMOVE_TECHNICIAN;

    constructor(public payload: Technician) {
    }
}

export class RemoveTechnicianSuccessAction implements Action {
    readonly type: string = REMOVE_TECHNICIAN_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveTechnicianErrorAction implements Action {
    readonly type: string = REMOVE_TECHNICIAN_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export type TechnicianActions
    = SaveTechnicianAction
    | SaveTechnicianSuccessAction
    | SaveTechnicianErrorAction
    | UpdateTechnicianAction
    | UpdateTechnicianSuccessAction
    | UpdateTechnicianErrorAction
    | RemoveTechnicianAction
    | RemoveTechnicianSuccessAction
    | RemoveTechnicianErrorAction;
