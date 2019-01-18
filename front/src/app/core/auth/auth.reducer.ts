import {AuthState, initialState} from "./auth.model";
import {AuthActions, AuthActionTypes} from "./auth.action";

export function authReducer(state: AuthState = initialState, action: AuthActions): AuthState {
    switch (action.type) {
        case AuthActionTypes.LOGIN:
            return {...state, isAuthenticated: false, isLoading: true};

        case AuthActionTypes.LOGIN_SUCCESS:
            return {...state, isAuthenticated: true, isLoading: false};

        case AuthActionTypes.LOGIN_ERROR:
            return {...state, isAuthenticated: false, isLoading: false,};

        case AuthActionTypes.SET_TOKEN:
            console.log(`AuthActionTypes.SET_TOKEN.payload`, action.payload.token);
            return {...state, isAuthenticated: true, accessToken: action.payload.token};

        case AuthActionTypes.LOGOUT:
            return {...state, isAuthenticated: false, accessToken: null};

        default:
            return state;
    }
}

