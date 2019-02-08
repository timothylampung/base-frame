import {createSelector} from '@ngrx/store';
import {selectMaintenanceRequest} from "../maintenance-request.state";

export const selectMaintenanceRequestResult = createSelector(selectMaintenanceRequest, state => state.maintenanceRequestResult);
export const selectMaintenanceRequests = createSelector(selectMaintenanceRequestResult, state => state.data);
export const selectMaintenanceRequestTotalSize = createSelector(selectMaintenanceRequestResult, state => state.totalSize);
