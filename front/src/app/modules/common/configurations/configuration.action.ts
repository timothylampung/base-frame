import {Action} from '@ngrx/store';
import {ApplicationError} from '../../../models/application-error.model';
import {Configuration, ConfigurationResult} from './configuration.model';

export const FIND_PAGED_CONFIGURATIONS = '[Configuration] Find Paged Configurations';
export const FIND_PAGED_CONFIGURATIONS_SUCCESS = '[Configuration] Find Paged Configurations Success';
export const FIND_PAGED_CONFIGURATIONS_ERROR = '[Configuration] Find Paged Configurations Error';
export const SAVE_CONFIGURATION = '[Configuration] Save Configuration';
export const SAVE_CONFIGURATION_SUCCESS = '[Configuration] Save Configuration Success';
export const SAVE_CONFIGURATION_ERROR = '[Configuration] Save Configuration Error';
export const UPDATE_CONFIGURATION = '[Configuration] Update Configuration';
export const UPDATE_CONFIGURATION_SUCCESS = '[Configuration] Update Configuration Success';
export const UPDATE_CONFIGURATION_ERROR = '[Configuration] Update Configuration Error';
export const REMOVE_CONFIGURATION = '[Configuration] Remove Configuration';
export const REMOVE_CONFIGURATION_SUCCESS = '[Configuration] Remove Configuration Success';
export const REMOVE_CONFIGURATION_ERROR = '[Configuration] Remove Configuration Error';

export class FindPagedConfigurationsAction implements Action {
    readonly type: string = FIND_PAGED_CONFIGURATIONS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedConfigurationsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_CONFIGURATIONS_SUCCESS;

    constructor(public payload: ConfigurationResult) {
    }
}

export class FindPagedConfigurationsErrorAction implements Action {
    readonly type: string = FIND_PAGED_CONFIGURATIONS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveConfigurationAction implements Action {
    readonly type: string = SAVE_CONFIGURATION;

    constructor(public payload: Configuration) {
    }
}

export class SaveConfigurationSuccessAction implements Action {
    readonly type: string = SAVE_CONFIGURATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveConfigurationErrorAction implements Action {
    readonly type: string = SAVE_CONFIGURATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateConfigurationAction implements Action {
    readonly type: string = UPDATE_CONFIGURATION;

    constructor(public payload: Configuration) {
    }
}

export class UpdateConfigurationSuccessAction implements Action {
    readonly type: string = UPDATE_CONFIGURATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateConfigurationErrorAction implements Action {
    readonly type: string = UPDATE_CONFIGURATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveConfigurationAction implements Action {
    readonly type: string = REMOVE_CONFIGURATION;

    constructor(public payload: Configuration) {
    }
}

export class RemoveConfigurationSuccessAction implements Action {
    readonly type: string = REMOVE_CONFIGURATION_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveConfigurationErrorAction implements Action {
    readonly type: string = REMOVE_CONFIGURATION_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
