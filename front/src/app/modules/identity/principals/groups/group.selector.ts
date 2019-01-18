import {createSelector} from '@ngrx/store';
import {selectIdentityState} from '../../identity.state';


export const selectGroupResultState = createSelector(selectIdentityState, state => state.groupResult);
export const selectGroups = createSelector(selectGroupResultState, state => state.data);
export const selectGroupTotalSize = createSelector(selectGroupResultState, state => state.totalSize);
