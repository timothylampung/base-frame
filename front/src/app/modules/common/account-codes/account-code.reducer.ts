import {AccountCode, AccountCodeResult} from './account-code.model';
import {
    FIND_ALL_ACCOUNT_CODES_SUCCESS,
    FIND_PAGED_ACCOUNT_CODES_SUCCESS,
    FindAllAccountCodesSuccessAction,
    FindPagedAccountCodesSuccessAction
} from './account-code.action';

const initialState: AccountCodeResult = {
    data: [], totalSize: 0
};

export function accountCodeResultReducer(state = initialState, action: FindPagedAccountCodesSuccessAction): AccountCodeResult {
    switch (action.type) {
        case FIND_PAGED_ACCOUNT_CODES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function accountCodesReducer(state = [], action: FindAllAccountCodesSuccessAction): AccountCode[] {
    switch (action.type) {
        case FIND_ALL_ACCOUNT_CODES_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
