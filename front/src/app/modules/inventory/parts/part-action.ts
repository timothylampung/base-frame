import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {Part, PartResult} from "./part-model";

export const FIND_ALL_PARTS = '[Part] Find All Parts';
export const FIND_ALL_PARTS_SUCCESS = '[Part] Find All Parts Success';
export const FIND_ALL_PARTS_ERROR = '[Part] Find All Parts Error';
export const FIND_PAGED_PARTS = '[Part] Find Paged Parts';
export const FIND_PAGED_PARTS_SUCCESS = '[Part] Find Paged Parts Success';
export const FIND_PAGED_PARTS_ERROR = '[Part] Find Paged Parts Error';
export const SAVE_PART = '[Part] Save Part';
export const SAVE_PART_SUCCESS = '[Part] Save Part Success';
export const SAVE_PART_ERROR = '[Part] Save Part Error';
export const UPDATE_PART = '[Part] Update Part';
export const UPDATE_PART_SUCCESS = '[Part] Update Part Success';
export const UPDATE_PART_ERROR = '[Part] Update Part Error';
export const REMOVE_PART = '[Part] Remove Part';
export const REMOVE_PART_SUCCESS = '[Part] Remove Part Success';
export const REMOVE_PART_ERROR = '[Part] Remove Part Error';

export class FindAllPartsAction implements Action {
    readonly type: string = FIND_ALL_PARTS;

    constructor() {
    }
}

export class FindAllPartsSuccessAction implements Action {
    readonly type: string = FIND_ALL_PARTS_SUCCESS;

    constructor(public payload: Part[]) {
    }
}

export class FindAllPartsErrorAction implements Action {
    readonly type: string = FIND_ALL_PARTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedPartsAction implements Action {
    readonly type: string = FIND_PAGED_PARTS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedPartsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_PARTS_SUCCESS;

    constructor(public payload: PartResult) {
    }
}

export class FindPagedPartsErrorAction implements Action {
    readonly type: string = FIND_PAGED_PARTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SavePartAction implements Action {
    readonly type: string = SAVE_PART;

    constructor(public payload: Part) {
    }
}

export class SavePartSuccessAction implements Action {
    readonly type: string = SAVE_PART_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SavePartErrorAction implements Action {
    readonly type: string = SAVE_PART_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdatePartAction implements Action {
    readonly type: string = UPDATE_PART;

    constructor(public payload: Part) {
    }
}

export class UpdatePartSuccessAction implements Action {
    readonly type: string = UPDATE_PART_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdatePartErrorAction implements Action {
    readonly type: string = UPDATE_PART_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemovePartAction implements Action {
    readonly type: string = REMOVE_PART;

    constructor(public payload: Part) {
    }
}

export class RemovePartSuccessAction implements Action {
    readonly type: string = REMOVE_PART_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemovePartErrorAction implements Action {
    readonly type: string = REMOVE_PART_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
