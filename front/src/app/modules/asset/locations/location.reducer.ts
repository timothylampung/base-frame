import {
    FIND_ALL_LOCATIONS_SUCCESS,
    FIND_PAGED_LOCATIONS_SUCCESS,
    FindAllLocationsSuccessAction,
    FindPagedLocationsSuccessAction,
    UPDATE_LOCATION_SUCCESS,
    UPLOAD_LOCATION, UPLOAD_LOCATION_ERROR, UPLOAD_LOCATION_SUCCESS
} from "./location-action";
import {Location, LocationResult, LocationUploadStatus} from "./location.model";


const initialState: LocationResult = {
    data: [], totalSize: 0
};

const initialUploadStatusState: LocationUploadStatus = {
    uploaded: false, isError: false, errorMsg: ''
};

export function locationResultReducer(state = initialState, action: FindPagedLocationsSuccessAction): LocationResult {
    switch (action.type) {
        case FIND_PAGED_LOCATIONS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function locationsReducer(state = [], action: FindAllLocationsSuccessAction): Location[] {
    switch (action.type) {
        case FIND_ALL_LOCATIONS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}

export function locationUploadedStatusReducer(state = initialUploadStatusState, action) {
    switch (action.type) {
        case UPLOAD_LOCATION:
            return {...state, uploaded: true};
        case UPLOAD_LOCATION_SUCCESS:
            return {...state, ...action.payload, isError: false, uploaded: false};
        case UPLOAD_LOCATION_ERROR:
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
