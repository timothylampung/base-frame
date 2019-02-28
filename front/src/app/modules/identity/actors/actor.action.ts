import {Action} from "@ngrx/store";
import {Actor} from "./actor.model";
import {ActorResult} from "./actor-result.model";
import {ApplicationError} from "../../../models";

export const FIND_ALL_ACTORS = '[Actor] Find All Actors';
export const FIND_ALL_ACTORS_SUCCESS = '[Actor] Find All Actors Success';
export const FIND_ALL_ACTORS_ERROR = '[Actor] Find All Actors Error';
export const FIND_PAGED_ACTORS = '[Actor] Find Paged Actors';
export const FIND_PAGED_ACTORS_SUCCESS = '[Actor] Find Paged Actors Success';
export const FIND_PAGED_ACTORS_ERROR = '[Actor] Find Paged Actors Error';

export class FindAllActorsAction implements Action {
    readonly type: string = FIND_ALL_ACTORS;

    constructor() {
    }
}

export class FindAllActorsSuccessAction implements Action {
    readonly type: string = FIND_ALL_ACTORS_SUCCESS;

    constructor(public payload: Actor[]) {
    }
}

export class FindAllActorsErrorAction implements Action {
    readonly type: string = FIND_ALL_ACTORS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class FindPagedActorsAction implements Action {
    readonly type: string = FIND_PAGED_ACTORS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedActorsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_ACTORS_SUCCESS;

    constructor(public payload: ActorResult) {
    }
}

export class FindPagedActorsErrorAction implements Action {
    readonly type: string = FIND_PAGED_ACTORS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
