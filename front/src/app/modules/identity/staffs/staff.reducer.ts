import {Staff, StaffResult, StaffUploadStatus} from './staff.model';
import {
    FIND_ALL_STAFFS_SUCCESS,
    FIND_PAGED_STAFFS_SUCCESS, FindAllStaffsSuccessAction,
    FindPagedStaffsSuccessAction, UPLOAD_STAFF, UPLOAD_STAFF_ERROR, UPLOAD_STAFF_SUCCESS
} from './staff.action';

const initialState: StaffResult = {
    data: [], totalSize: 0
};

const initialUploadStatusState: StaffUploadStatus = {
    uploaded: false, isError: false, errorMsg: ''
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

export function staffUploadedStatusReducer(state = initialUploadStatusState, action) {
    switch (action.type) {
        case UPLOAD_STAFF:
            return {...state, uploaded: true};
        case UPLOAD_STAFF_SUCCESS:
            return {...state, ...action.payload, isError: false, uploaded: false};
        case UPLOAD_STAFF_ERROR:
            return {
                ...state,
                isError: true,
                uploaded: false,
                errorMsg: action.payload.error.message
            };
        default: {
            return state;
        }
    }
}

