import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../work-order.state';

export const selectWorkOrderResultState = createSelector(selectCommonState, state => state.workOrderResult);
export const selectWorkOrders = createSelector(selectWorkOrderResultState, state => state.data);
export const selectWorkOrderTotalSize = createSelector(selectWorkOrderResultState, state => state.totalSize);
export const selectAllWorkOrders = createSelector(selectCommonState, state => state.workOrders);
``
