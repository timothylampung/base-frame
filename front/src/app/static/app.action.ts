import {Action} from '@ngrx/store';

export const LOAD_ERROR = '[App] Load error';

export class LoadError implements Action {
    readonly type = LOAD_ERROR;

    constructor(public readonly payload?: any) {
    }
}
