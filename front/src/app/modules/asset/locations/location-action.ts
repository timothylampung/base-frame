import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {Location, LocationResult} from "./location-model";

export const FIND_ALL_LOCATIONS = '[Location] Find All Locations';
export const FIND_ALL_LOCATIONS_SUCCESS = '[Location] Find All Locations Success';
export const FIND_ALL_LOCATIONS_ERROR = '[Location] Find All Locations Error';
export const FIND_PAGED_LOCATIONS = '[Location] Find Paged Locations';
export const FIND_PAGED_LOCATIONS_SUCCESS = '[Location] Find Paged Locations Success';
export const FIND_PAGED_LOCATIONS_ERROR = '[Location] Find Paged Locations Error';
export const SAVE_LOCATION = '[Location] Save Location';
export const SAVE_LOCATION_SUCCESS = '[Location] Save Location Success';
export const SAVE_LOCATION_ERROR = '[Location] Save Location Error';
export const UPDATE_LOCATION = '[Location] Update Location';
export const UPDATE_LOCATION_SUCCESS = '[Location] Update Location Success';
export const UPDATE_LOCATION_ERROR = '[Location] Update Location Error';
export const REMOVE_LOCATION = '[Location] Remove Location';
export const REMOVE_LOCATION_SUCCESS = '[Location] Remove Location Success';
export const REMOVE_LOCATION_ERROR = '[Location] Remove Location Error';

export class FindAllLocationsAction implements Action {
    readonly type: string = FIND_ALL_LOCATIONS;

    constructor() {
    }
}

export class FindAllLocationsSuccessAction implements Action {
    readonly type: string = FIND_ALL_LOCATIONS_SUCCESS;

    constructor(public payload: Location[]) {
    }
}

export class FindAllLocationsErrorAction implements Action {
    readonly type: string = FIND_ALL_LOCATIONS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedLocationsAction implements Action {
    readonly type: string = FIND_PAGED_LOCATIONS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedLocationsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_LOCATIONS_SUCCESS;

    constructor(public payload: LocationResult) {
    }
}

export class FindPagedLocationsErrorAction implements Action {
    readonly type: string = FIND_PAGED_LOCATIONS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveLocationAction implements Action {
    readonly type: string = SAVE_LOCATION;

    constructor(public payload: Location) {
    }
}

export class SaveLocationSuccessAction implements Action {
    readonly type: string = SAVE_LOCATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveLocationErrorAction implements Action {
    readonly type: string = SAVE_LOCATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateLocationAction implements Action {
    readonly type: string = UPDATE_LOCATION;

    constructor(public payload: Location) {
    }
}

export class UpdateLocationSuccessAction implements Action {
    readonly type: string = UPDATE_LOCATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateLocationErrorAction implements Action {
    readonly type: string = UPDATE_LOCATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveLocationAction implements Action {
    readonly type: string = REMOVE_LOCATION;

    constructor(public payload: Location) {
    }
}

export class RemoveLocationSuccessAction implements Action {
    readonly type: string = REMOVE_LOCATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveLocationErrorAction implements Action {
    readonly type: string = REMOVE_LOCATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
