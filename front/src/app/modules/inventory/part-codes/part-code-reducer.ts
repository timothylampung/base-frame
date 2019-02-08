import {
    FIND_ALL_PART_CODES_SUCCESS,
    FIND_PAGED_PART_CODES_SUCCESS, FindAllPartCodesSuccessAction,
    FindPagedPartCodesSuccessAction
} from "./part-code-action";
import {PartCode, PartCodeResult} from "./part-code-model";

const initialState: PartCodeResult = {
    data: [], totalSize: 0
};

export function partCodeResultReducer(state = initialState, action: FindPagedPartCodesSuccessAction): PartCodeResult {
    switch (action.type) {
        case FIND_PAGED_PART_CODES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function partCodesReducer(state = [], action: FindAllPartCodesSuccessAction): PartCode[] {
    switch (action.type) {
        case FIND_ALL_PART_CODES_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
