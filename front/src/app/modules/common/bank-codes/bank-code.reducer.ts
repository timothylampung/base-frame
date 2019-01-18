import {BankCode, BankCodeResult} from "./bank-code.model";
import {
    FIND_ALL_BANK_CODES_SUCCESS,
    FIND_PAGED_BANK_CODES_SUCCESS,
    FindAllBankCodesSuccessAction, FindPagedBankCodesSuccessAction
} from "./bank-code.action";


const initialState: BankCodeResult = {
    data: [], totalSize: 0
};

export function bankCodeResultReducer(state = initialState, action: FindPagedBankCodesSuccessAction): BankCodeResult {
    switch (action.type) {
        case FIND_PAGED_BANK_CODES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function bankCodesReducer(state = [], action: FindAllBankCodesSuccessAction): BankCode[] {
    switch (action.type) {
        case FIND_ALL_BANK_CODES_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
