import {createSelector} from '@ngrx/store';
import {selectAssetState} from "../asset.state";

export const selectLocationResultState = createSelector(selectAssetState, state => state.locationResult);
export const selectLocations = createSelector(selectLocationResultState, state => state.data);
export const selectLocationTotalSize = createSelector(selectLocationResultState, state => state.totalSize);
export const selectLocationUploadStatus = createSelector(selectAssetState, state => state.locationUploadStatus);
