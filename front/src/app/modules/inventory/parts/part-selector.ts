import {createSelector} from '@ngrx/store';
import {selectInventoryState} from "../inventory.state";

export const selectPartResultState = createSelector(selectInventoryState, state => state.partResult);
export const selectParts = createSelector(selectPartResultState, state => state.data);
export const selectPartTotalSize = createSelector(selectPartResultState, state => state.totalSize);
export const selectAllParts = createSelector(selectInventoryState, state => state.parts);
