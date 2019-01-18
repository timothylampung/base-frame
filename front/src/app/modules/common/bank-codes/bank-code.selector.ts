import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';

export const selectBankCodeResultState = createSelector(selectCommonState, state => state.bankCodeResult);
export const selectBankCodes = createSelector(selectBankCodeResultState, state => state.data);
export const selectBankCodeTotalSize = createSelector(selectBankCodeResultState, state => state.totalSize);

export const selectAllBankCodes = createSelector(selectCommonState, state => state.bankCodes);
