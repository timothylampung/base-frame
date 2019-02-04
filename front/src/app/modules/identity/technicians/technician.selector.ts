import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../identity.state";

export const selectTechnicianResultState = createSelector(selectIdentityState, state => state.technicianResult);
export const selectTechnicians = createSelector(selectTechnicianResultState, state => state.data);
export const selectTechnicianTotalSize = createSelector(selectTechnicianResultState, state => state.totalSize);
export const selectAllTechnicians = createSelector(selectIdentityState, state => state.technicians);
