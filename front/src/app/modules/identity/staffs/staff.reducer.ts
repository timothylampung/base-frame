import {Staff, StaffResult} from './staff.model';
import {
    FIND_ALL_STAFFS_SUCCESS,
    FIND_PAGED_STAFFS_SUCCESS, FindAllStaffsSuccessAction,
    FindPagedStaffsSuccessAction
} from './staff.action';

const initialState: StaffResult = {
    data: [], totalSize: 0
};

export function staffResultReducer(state = initialState, action: FindPagedStaffsSuccessAction): StaffResult {
    switch (action.type) {
        case FIND_PAGED_STAFFS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function staffsReducer(state = [], action: FindAllStaffsSuccessAction): Staff[] {
    switch (action.type) {
        case FIND_ALL_STAFFS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
