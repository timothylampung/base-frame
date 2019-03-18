import {createSelector} from '@ngrx/store';
import {selectMaintenanceRequestState} from './maintenance-request.state';

export const selectMaintenanceRequest = createSelector(selectMaintenanceRequestState, state => state.maintenanceRequest);

export const selectMaintenanceRequestAssignedTaskResult = createSelector(selectMaintenanceRequestState, state => state.maintenanceRequestTaskResult);
export const selectMaintenanceRequestAssignedTasks = createSelector(selectMaintenanceRequestAssignedTaskResult, state => state.data);
export const selectMaintenanceRequestAssignedCountTasks = createSelector(selectMaintenanceRequestAssignedTaskResult, state => state.totalSize);
export const selectMaintenanceRequestPooledTaskResult = createSelector(selectMaintenanceRequestState, state => state.maintenanceRequestTaskResult);
export const selectMaintenanceRequestPooledTasks = createSelector(selectMaintenanceRequestPooledTaskResult, state => state.data);
export const selectMaintenanceRequestPooledCountTasks = createSelector(selectMaintenanceRequestPooledTaskResult, state => state.totalSize);
export const selectMaintenanceRequestTask = createSelector(selectMaintenanceRequestState, state => state.maintenanceRequestTask);

// record
export const selectMaintenanceRequestRecordResult = createSelector(selectMaintenanceRequestState, state => state.maintenanceRequestRecordResult);
export const selectMaintenanceRequestRecords = createSelector(selectMaintenanceRequestRecordResult, state => state.data);
export const selectMaintenanceRequestCountRecords = createSelector(selectMaintenanceRequestRecordResult, state => state.totalSize);


