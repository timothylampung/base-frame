import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';

export const selectPositionCodeResultState = createSelector(selectCommonState, state => state.positionCodeResult);
export const selectPositionCodes = createSelector(selectPositionCodeResultState, state => state.data);
export const selectPositionCodeTotalSize = createSelector(selectPositionCodeResultState, state => state.totalSize);
