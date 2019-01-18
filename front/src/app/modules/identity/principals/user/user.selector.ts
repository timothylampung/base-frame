import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../../identity.state";

export const selectUserResultState = createSelector(selectIdentityState, state => state.userResult);
export const selectUsers = createSelector(selectUserResultState, state => state.data);
export const selectUserTotalSize = createSelector(selectUserResultState, state => state.totalSize);

export const selectAllUsers = createSelector(selectIdentityState, state => state.users);
