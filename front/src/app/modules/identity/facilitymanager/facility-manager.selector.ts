import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../identity.state";

export const selectFacilityManagerResultState = createSelector(selectIdentityState, state => state.facilityManagerResult);
export const selectFacilityManagers = createSelector(selectFacilityManagerResultState, state => state.data);
export const selectFacilityManagerTotalSize = createSelector(selectFacilityManagerResultState, state => state.totalSize);
export const selectAllFacilityManagers = createSelector(selectIdentityState, state => state.facilityManagers);
