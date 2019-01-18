import {Action} from '@ngrx/store';
import {Period, PeriodResult} from './period.model';
import {ApplicationError} from "../../../models/application-error.model";
export const FIND_ALL_PERIODS = '[Period] Find All Periods';
export const FIND_ALL_PERIODS_SUCCESS = '[Period] Find All Periods Success';
export const FIND_ALL_PERIODS_ERROR = '[Period] Find All Periods Error';
export const FIND_PAGED_PERIODS = '[Period] Find Paged Periods';
export const FIND_PAGED_PERIODS_SUCCESS = '[Period] Find Paged Periods Success';
export const FIND_PAGED_PERIODS_ERROR = '[Period] Find Paged Periods Error';
export const SAVE_PERIOD = '[Period] Save Period';
export const SAVE_PERIOD_SUCCESS = '[Period] Save Period Success';
export const SAVE_PERIOD_ERROR = '[Period] Save Period Error';
export const UPDATE_PERIOD = '[Period] Update Period';
export const UPDATE_PERIOD_SUCCESS = '[Period] Update Period Success';
export const UPDATE_PERIOD_ERROR = '[Period] Update Period Error';
export const REMOVE_PERIOD = '[Period] Remove Period';
export const REMOVE_PERIOD_SUCCESS = '[Period] Remove Period Success';
export const REMOVE_PERIOD_ERROR = '[Period] Remove Period Error';

export class FindAllPeriodsAction implements Action {
    readonly type: string = FIND_ALL_PERIODS;

    constructor() {
    }
}

export class FindAllPeriodsSuccessAction implements Action {
    readonly type: string = FIND_ALL_PERIODS_SUCCESS;

    constructor(public payload: Period[]) {
    }
}

export class FindAllPeriodsErrorAction implements Action {
    readonly type: string = FIND_ALL_PERIODS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedPeriodsAction implements Action {
    readonly type: string = FIND_PAGED_PERIODS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedPeriodsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_PERIODS_SUCCESS;

    constructor(public payload: PeriodResult) {
    }
}

export class FindPagedPeriodsErrorAction implements Action {
    readonly type: string = FIND_PAGED_PERIODS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SavePeriodAction implements Action {
    readonly type: string = SAVE_PERIOD;

    constructor(public payload: Period) {
    }
}

export class SavePeriodSuccessAction implements Action {
    readonly type: string = SAVE_PERIOD_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SavePeriodErrorAction implements Action {
    readonly type: string = SAVE_PERIOD_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdatePeriodAction implements Action {
    readonly type: string = UPDATE_PERIOD;

    constructor(public payload: Period) {
    }
}

export class UpdatePeriodSuccessAction implements Action {
    readonly type: string = UPDATE_PERIOD_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdatePeriodErrorAction implements Action {
    readonly type: string = UPDATE_PERIOD_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemovePeriodAction implements Action {
    readonly type: string = REMOVE_PERIOD;

    constructor(public payload: Period) {
    }
}

export class RemovePeriodSuccessAction implements Action {
    readonly type: string = REMOVE_PERIOD_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemovePeriodErrorAction implements Action {
    readonly type: string = REMOVE_PERIOD_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
