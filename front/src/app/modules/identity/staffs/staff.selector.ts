import {createSelector} from '@ngrx/store';
import {selectIdentityState} from "../identity.state";
import {selectAssetState} from "../../asset/asset.state";

export const selectStaffResultState = createSelector(selectIdentityState, state => state.staffResult);
export const selectStaffs = createSelector(selectStaffResultState, state => state.data);
export const selectStaffTotalSize = createSelector(selectStaffResultState, state => state.totalSize);
export const selectStaffUploadStatus = createSelector(selectIdentityState, state => state.staffUploadStatus);

