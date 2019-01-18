import {Action} from "@ngrx/store";

export enum AuthActionTypes {
    LOGIN = '[Auth] Login',
    LOGIN_SUCCESS = '[Auth] Login Success',
    LOGIN_ERROR = '[Auth] Login Error',
    LOGOUT = '[Auth] Logout',
    LOGOUT_SUCCESS = '[Auth] Logout Success',
    SET_TOKEN = '[Auth] Set token'
}

export class LoginAction implements Action {
    readonly type = AuthActionTypes.LOGIN;

    constructor(public username: string, public password: string) {

    }
}

export class LoginSuccessAction implements Action {
    readonly type = AuthActionTypes.LOGIN_SUCCESS;

    constructor() {

    }
}

export class LoginErrorAction implements Action {
    readonly type = AuthActionTypes.LOGIN_ERROR;

    constructor(public message: string) {

    }
}

export class LogoutAction implements Action {
    readonly type = AuthActionTypes.LOGOUT;

    constructor(public payload?: { redirectUrl: string }) {
    }
}

export class SetTokenAction implements Action {
    readonly type = AuthActionTypes.SET_TOKEN;

    constructor(public payload: { token: string }) {

    }
}

export type AuthActions = LoginAction | LoginSuccessAction | LoginErrorAction | LogoutAction | SetTokenAction;
