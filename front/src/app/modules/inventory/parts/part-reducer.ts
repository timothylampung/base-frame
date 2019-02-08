import {
    FIND_ALL_PARTS_SUCCESS,
    FIND_PAGED_PARTS_SUCCESS,
    FindAllPartsSuccessAction, FindPagedPartsSuccessAction
} from "./part-action";
import {Part, PartResult} from "./part-model";


const initialState: PartResult = {
    data: [], totalSize: 0
};

export function partResultReducer(state = initialState, action: FindPagedPartsSuccessAction): PartResult {
    switch (action.type) {
        case FIND_PAGED_PARTS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function partsReducer(state = [], action: FindAllPartsSuccessAction): Part[] {
    switch (action.type) {
        case FIND_ALL_PARTS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
