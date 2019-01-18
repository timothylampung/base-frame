import {createFeatureSelector} from '@ngrx/store';
import {AuthState} from "./auth/auth.model";
import {authReducer} from "./auth/auth.reducer";

export const selectAuthState = createFeatureSelector<AppState, AuthState>(
    'auth'
);

export const reducers = {
    auth: authReducer
};

export interface AppState {
    auth: AuthState;
}
