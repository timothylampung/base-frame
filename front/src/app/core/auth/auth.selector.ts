import {createSelector} from "@ngrx/store";
import {AuthState} from "./auth.model";

export const selectAuth = (state: AuthState) => state;
export const selectAuthLoading = createSelector(selectAuth, (s) => s.isLoading);
export const selectAuthIsAuthenticated = createSelector(selectAuth, (s) => s.isAuthenticated);
export const selectAuthAccessToken = createSelector(selectAuth, (s) => s.accessToken);
