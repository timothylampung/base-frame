import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {userResultReducer, usersReducer} from "./principals/user/user.reducer";
import {AppState} from "../../core/core.state";
import {UserResult} from "./principals/user/user-result.model";
import {User} from "./principals/user/user.model";
import {groupResultReducer} from './principals/groups/group.reducer';
import {GroupResult} from './principals/group-result.model';


export const FEATURE_NAME = 'identity';
export const selectIdentityState = createFeatureSelector<State, IdentityState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<IdentityState> = {
    userResult: userResultReducer,
    users: usersReducer,
    groupResult: groupResultReducer,

};

export interface IdentityState {
    userResult: UserResult;
    users: User[];
    groupResult: GroupResult;
}

export interface State extends AppState {
    identity: IdentityState;
}
