import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';

export const selectPeriodResultState = createSelector(selectCommonState, state => state.periodResult);
export const selectPeriods = createSelector(selectPeriodResultState, state => state.data);
export const selectPeriodTotalSize = createSelector(selectPeriodResultState, state => state.totalSize);

export const selectAllPeriods = createSelector(selectCommonState, state => state.periods);
