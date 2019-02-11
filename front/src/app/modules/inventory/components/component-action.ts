import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {Component, ComponentResult} from "./component-model";

export const FIND_ALL_COMPONENTS = '[Component] Find All Components';
export const FIND_ALL_COMPONENTS_SUCCESS = '[Component] Find All Components Success';
export const FIND_ALL_COMPONENTS_ERROR = '[Component] Find All Components Error';
export const FIND_PAGED_COMPONENTS = '[Component] Find Paged Components';
export const FIND_PAGED_COMPONENTS_SUCCESS = '[Component] Find Paged Components Success';
export const FIND_PAGED_COMPONENTS_ERROR = '[Component] Find Paged Components Error';
export const SAVE_COMPONENT = '[Component] Save Component';
export const SAVE_COMPONENT_SUCCESS = '[Component] Save Component Success';
export const SAVE_COMPONENT_ERROR = '[Component] Save Component Error';
export const UPDATE_COMPONENT = '[Component] Update Component';
export const UPDATE_COMPONENT_SUCCESS = '[Component] Update Component Success';
export const UPDATE_COMPONENT_ERROR = '[Component] Update Component Error';
export const REMOVE_COMPONENT = '[Component] Remove Component';
export const REMOVE_COMPONENT_SUCCESS = '[Component] Remove Component Success';
export const REMOVE_COMPONENT_ERROR = '[Component] Remove Component Error';

export class FindAllComponentsAction implements Action {
    readonly type: string = FIND_ALL_COMPONENTS;

    constructor() {
    }
}

export class FindAllComponentsSuccessAction implements Action {
    readonly type: string = FIND_ALL_COMPONENTS_SUCCESS;

    constructor(public payload: Component[]) {
    }
}

export class FindAllComponentsErrorAction implements Action {
    readonly type: string = FIND_ALL_COMPONENTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedComponentsAction implements Action {
    readonly type: string = FIND_PAGED_COMPONENTS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedComponentsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_COMPONENTS_SUCCESS;

    constructor(public payload: ComponentResult) {
    }
}

export class FindPagedComponentsErrorAction implements Action {
    readonly type: string = FIND_PAGED_COMPONENTS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveComponentAction implements Action {
    readonly type: string = SAVE_COMPONENT;

    constructor(public payload: Component) {
    }
}

export class SaveComponentSuccessAction implements Action {
    readonly type: string = SAVE_COMPONENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveComponentErrorAction implements Action {
    readonly type: string = SAVE_COMPONENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateComponentAction implements Action {
    readonly type: string = UPDATE_COMPONENT;

    constructor(public payload: Component) {
    }
}

export class UpdateComponentSuccessAction implements Action {
    readonly type: string = UPDATE_COMPONENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateComponentErrorAction implements Action {
    readonly type: string = UPDATE_COMPONENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveComponentAction implements Action {
    readonly type: string = REMOVE_COMPONENT;

    constructor(public payload: Component) {
    }
}

export class RemoveComponentSuccessAction implements Action {
    readonly type: string = REMOVE_COMPONENT_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveComponentErrorAction implements Action {
    readonly type: string = REMOVE_COMPONENT_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
