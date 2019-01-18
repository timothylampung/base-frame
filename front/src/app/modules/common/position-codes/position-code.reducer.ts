import {PositionCodeResult} from './position-code.model';
import {FIND_PAGED_POSITION_CODES_SUCCESS, FindPagedPositionCodesSuccessAction} from './position-code.action';

const initialState: PositionCodeResult = {
    data: [], totalSize: 0
};

export function positionCodeReducer(state = initialState, action: FindPagedPositionCodesSuccessAction): PositionCodeResult {
    switch (action.type) {
        case FIND_PAGED_POSITION_CODES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}
