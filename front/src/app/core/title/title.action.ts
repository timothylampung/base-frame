import {Action} from '@ngrx/store';

export const CHANGE_TITLE = '[Title] Change Title';
export const CHANGE_TITLE_SUCCESS = '[Title] Change Title Success';

export class ChangeTitleAction implements Action {
    readonly type: string = CHANGE_TITLE;

    constructor(public payload: { title: string }) {
    }
}

export class ChangeTitleSuccessAction implements Action {
    readonly type: string = CHANGE_TITLE_SUCCESS;

    constructor(public payload: string) {
    }
}
