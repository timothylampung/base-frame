import {Action} from '@ngrx/store';
import {ApplicationError} from '../../../../models/application-error.model';
import {Group} from '../group.model';
import {GroupResult} from '../group-result.model';

export const FIND_PAGED_GROUPS = '[Group] Find Paged Groups';
export const FIND_PAGED_GROUPS_SUCCESS = '[Group] Find Paged Groups Success';
export const FIND_PAGED_GROUPS_ERROR = '[Group] Find Paged Groups Error';
export const SAVE_GROUP = '[Group] Save Group';
export const SAVE_GROUP_SUCCESS = '[Group] Save Group Success';
export const SAVE_GROUP_ERROR = '[Group] Save Group Error';
export const UPDATE_GROUP = '[Group] Update Group';
export const UPDATE_GROUP_SUCCESS = '[Group] Update Group Success';
export const UPDATE_GROUP_ERROR = '[Group] Update Group Error';
export const REMOVE_GROUP = '[Group] Remove Group';
export const REMOVE_GROUP_SUCCESS = '[Group] Remove Group Success';
export const REMOVE_GROUP_ERROR = '[Group] Remove Group Error';

export class FindPagedGroupsAction implements Action {
    readonly type: string = FIND_PAGED_GROUPS;

    constructor(public payload: { filter: string, page: number }) {
    }
}

export class FindPagedGroupsSuccessAction implements Action {
    readonly type: string = FIND_PAGED_GROUPS_SUCCESS;

    constructor(public payload: GroupResult) {
    }
}

export class FindPagedGroupsErrorAction implements Action {
    readonly type: string = FIND_PAGED_GROUPS_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class SaveGroupAction implements Action {
    readonly type: string = SAVE_GROUP;

    constructor(public payload: Group) {
    }
}

export class SaveGroupSuccessAction implements Action {
    readonly type: string = SAVE_GROUP_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class SaveGroupErrorAction implements Action {
    readonly type: string = SAVE_GROUP_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class UpdateGroupAction implements Action {
    readonly type: string = UPDATE_GROUP;

    constructor(public payload: Group) {
    }
}

export class UpdateGroupSuccessAction implements Action {
    readonly type: string = UPDATE_GROUP_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class UpdateGroupErrorAction implements Action {
    readonly type: string = UPDATE_GROUP_ERROR;

    constructor(public payload: ApplicationError) {
    }
}

export class RemoveGroupAction implements Action {
    readonly type: string = REMOVE_GROUP;

    constructor(public payload: Group) {
    }
}

export class RemoveGroupSuccessAction implements Action {
    readonly type: string = REMOVE_GROUP_SUCCESS;

    constructor(public payload: { message: string }) {
    }
}

export class RemoveGroupErrorAction implements Action {
    readonly type: string = REMOVE_GROUP_ERROR;

    constructor(public payload: ApplicationError) {
    }
}
