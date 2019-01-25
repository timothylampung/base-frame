import {createSelector} from '@ngrx/store';
import {selectAssetState} from "../asset.state";

export const selectAssetCodeResultState = createSelector(selectAssetState, state => state.assetCodeResult);
export const selectAssetCodes = createSelector(selectAssetCodeResultState, state => state.data);
export const selectAssetCodeTotalSize = createSelector(selectAssetCodeResultState, state => state.totalSize);
export const selectAllAssetCodes = createSelector(selectAssetState, state => state.assetCodes);
