import {createSelector} from '@ngrx/store';
import {selectWorkOrderState} from './work-order.state';

export const selectWorkOrderResult = createSelector(selectWorkOrderState, state => state.workOrderResult);
export const selectWorkOrders = createSelector(selectWorkOrderResult, state => state.data);
export const selectWorkOrderCount = createSelector(selectWorkOrderResult, state => state.totalSize);

export const selectWorkOrderAssignedCount = createSelector(selectWorkOrderState, state => state.workOrderCountTask);
export const selectWorkOrderPooledCount = createSelector(selectWorkOrderState, state => state.workOrderCountTask);

export const selectWorkOrder = createSelector(selectWorkOrderState, state => state.workOrder);
export const selectActivities = createSelector(selectWorkOrderState, state => state.activities);

export const selectWorkOrderAssignedTaskResult = createSelector(selectWorkOrderState, state => state.workOrderTaskResult);
export const selectWorkOrderAssignedTasks = createSelector(selectWorkOrderAssignedTaskResult, state => state.data);
export const selectWorkOrderAssignedCountTasks = createSelector(selectWorkOrderAssignedTaskResult, state => state.totalSize);

export const selectWorkOrderPooledTaskResult = createSelector(selectWorkOrderState, state => state.workOrderTaskResult);
export const selectWorkOrderPooledTasks = createSelector(selectWorkOrderPooledTaskResult, state => state.data);
export const selectWorkOrderPooledCountTasks = createSelector(selectWorkOrderPooledTaskResult, state => state.totalSize);

export const selectWorkOrderTask = createSelector(selectWorkOrderState, state => state.workOrderTask);

export const selectWorkOrderRecordResult = createSelector(selectWorkOrderState, state => state.workOrderRecordResult);
export const selectWorkOrderRecords = createSelector(selectWorkOrderRecordResult, state => state.data);
export const selectWorkOrderCountRecords = createSelector(selectWorkOrderRecordResult, state => state.totalSize);
