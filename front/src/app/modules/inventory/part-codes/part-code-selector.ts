import {createSelector} from '@ngrx/store';
import {selectInventoryState} from "../inventory.state";

export const selectPartCodeResultState = createSelector(selectInventoryState, state => state.partCodeResult);
export const selectPartCodes = createSelector(selectPartCodeResultState, state => state.data);
export const selectPartCodeTotalSize = createSelector(selectPartCodeResultState, state => state.totalSize);
export const selectAllPartCodes = createSelector(selectInventoryState, state => state.partCodes);
