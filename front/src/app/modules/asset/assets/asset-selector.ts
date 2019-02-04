import {createSelector} from '@ngrx/store';
import {selectAssetState} from "../asset.state";

export const selectAssetResultState = createSelector(selectAssetState, state => state.assetResult);
export const selectAssets = createSelector(selectAssetResultState, state => state.data);
export const selectAssetTotalSize = createSelector(selectAssetResultState, state => state.totalSize);
export const selectAllAssets = createSelector(selectAssetState, state => state.assets);
