import {createSelector} from '@ngrx/store';
import {selectInventoryState} from "../inventory.state";

export const selectComponentResultState = createSelector(selectInventoryState, state => state.componentResult);
export const selectComponents = createSelector(selectComponentResultState, state => state.data);
export const selectComponentTotalSize = createSelector(selectComponentResultState, state => state.totalSize);
export const selectAllComponents = createSelector(selectInventoryState, state => state.components);
