import {
    FIND_ALL_LOCATIONS_SUCCESS,
    FIND_PAGED_LOCATIONS_SUCCESS,
    FindAllLocationsSuccessAction, FindPagedLocationsSuccessAction
} from "./location-action";
import {Location, LocationResult} from "./location-model";


const initialState: LocationResult = {
    data: [], totalSize: 0
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
