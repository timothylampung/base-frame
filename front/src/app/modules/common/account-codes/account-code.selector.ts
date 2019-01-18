import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';

export const selectAccountCodeResultState = createSelector(selectCommonState, state => state.accountCodeResult);
export const selectAccountCodes = createSelector(selectAccountCodeResultState, state => state.data);
export const selectAccountCodeTotalSize = createSelector(selectAccountCodeResultState, state => state.totalSize);

export const selectAllAccountCodes = createSelector(selectCommonState, state => state.accountCodes);
