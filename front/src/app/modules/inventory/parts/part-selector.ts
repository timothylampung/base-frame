import {createSelector} from '@ngrx/store';
import {selectPartState} from "../inventory.state";

export const selectPartResultState = createSelector(selectPartState, state => state.partResult);
export const selectParts = createSelector(selectPartResultState, state => state.data);
export const selectPartTotalSize = createSelector(selectPartResultState, state => state.totalSize);
export const selectAllParts = createSelector(selectPartState, state => state.parts);
