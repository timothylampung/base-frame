import {selectCommonState} from "../common.state";
import {createSelector} from "@ngrx/store";

export const selectCostCenterResultState = createSelector(selectCommonState, state => state.costCenterResult);
export const selectCostCenters = createSelector(selectCostCenterResultState, state => state.data);
export const selectCostCenterTotalSize = createSelector(selectCostCenterResultState, state => state.totalSize);

export const selectAllCostCenters = createSelector(selectCommonState, state => state.costCenters);
