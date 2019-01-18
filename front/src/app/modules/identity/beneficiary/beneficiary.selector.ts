import {selectIdentityState} from "../identity.state";
import {createSelector} from "@ngrx/store";


export const selectBeneficiaryResultState = createSelector(selectIdentityState, state => state.beneficiaryResult);
export const selectBeneficiaries = createSelector(selectBeneficiaryResultState, state => state.data);
export const selectBeneficiaryTotalSize = createSelector(selectBeneficiaryResultState, state => state.totalSize);

export const selectAllBeneficiaries = createSelector(selectIdentityState, state => state.beneficiaries);
