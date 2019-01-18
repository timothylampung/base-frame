import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../identity.state";

export const selectStaffResultState = createSelector(selectIdentityState, state => state.staffResult);
export const selectStaffs = createSelector(selectStaffResultState, state => state.data);
export const selectStaffTotalSize = createSelector(selectStaffResultState, state => state.totalSize);

export const selectAllStaffs = createSelector(selectIdentityState, state => state.staffs);
