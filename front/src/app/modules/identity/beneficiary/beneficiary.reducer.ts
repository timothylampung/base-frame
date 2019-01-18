import {Beneficiary, BeneficiaryResult} from "./beneficiary.model";
import {
    FIND_ALL_BENEFICIARIES_SUCCESS,
    FIND_PAGED_BENEFICIARIES_SUCCESS,
    FindAllBeneficiariesSuccessAction,
    FindPagedBeneficiariesSuccessAction
} from "./beneficiary.action";


const initialState: BeneficiaryResult = {
    data: [], totalSize: 0
};

export function beneficiaryResultReducer(state = initialState, action: FindPagedBeneficiariesSuccessAction): BeneficiaryResult {
    switch (action.type) {
        case FIND_PAGED_BENEFICIARIES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function beneficiariesReducer(state = [], action: FindAllBeneficiariesSuccessAction): Beneficiary[] {
    switch (action.type) {
        case FIND_ALL_BENEFICIARIES_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
