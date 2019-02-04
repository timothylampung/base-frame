import {selectIdentityState} from "../identity.state";
import {createSelector} from "@ngrx/store";


export const selectSupervisorResultState = createSelector(selectIdentityState, state => state.supervisorResult);
export const selectSupervisors = createSelector(selectSupervisorResultState, state => state.data);
export const selectSupervisorTotalSize = createSelector(selectSupervisorResultState, state => state.totalSize);

export const selectAllSupervisors = createSelector(selectIdentityState, state => state.supervisors);
