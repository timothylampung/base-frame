import {Action} from '@ngrx/store';
import {ApplicationError} from "../../../models/application-error.model";
import {PartComponent, ComponentResult} from "./component-model";

export const FIND_ALL_COMPONENTS = '[PartComponent] Find All Components';
export const FIND_ALL_COMPONENTS_SUCCESS = '[PartComponent] Find All Components Success';
export const FIND_ALL_COMPONENTS_ERROR = '[PartComponent] Find All Components Error';
export const FIND_PAGED_COMPONENTS = '[PartComponent] Find Paged Components';
export const FIND_PAGED_COMPONENTS_SUCCESS = '[PartComponent] Find Paged Components Success';
export const FIND_PAGED_COMPONENTS_ERROR = '[PartComponent] Find Paged Components Error';
export const SAVE_COMPONENT = '[PartComponent] Save PartComponent';
export const SAVE_COMPONENT_SUCCESS = '[PartComponent] Save PartComponent Success';
export const SAVE_COMPONENT_ERROR = '[PartComponent] Save PartComponent Error';
export const UPDATE_COMPONENT = '[PartComponent] Update PartComponent';
export const UPDATE_COMPONENT_SUCCESS = '[PartComponent] Update PartComponent Success';
export const UPDATE_COMPONENT_ERROR = '[PartComponent] Update PartComponent Error';
export const REMOVE_COMPONENT = '[PartComponent] Remove PartComponent';
export const REMOVE_COMPONENT_SUCCESS = '[PartComponent] Remove PartComponent Success';
export const REMOVE_COMPONENT_ERROR = '[PartComponent] Remove PartComponent Error';

export class FindAllComponentsAction implements Action {
    readonly type: string = FIND_ALL_COMPONENTS;

    constructor() {
    }
}

export class FindAllComponentsSuccessAction implements Action {
    readonly type: string = FIND_ALL_COMPONENTS_SUCCESS;

    constructor(public payload: PartComponent[]) {
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

    constructor(public payload: PartComponent) {
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

    constructor(public payload: PartComponent) {
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

    constructor(public payload: PartComponent) {
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
