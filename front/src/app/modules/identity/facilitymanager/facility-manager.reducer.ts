import {
    FIND_ALL_FACILITY_MANAGERS_SUCCESS,
    FIND_PAGED_FACILITY_MANAGERS_SUCCESS, FindAllFacilityManagersSuccessAction,
    FindPagedFacilityManagersSuccessAction
} from './facility-manager.action';
import {FacilityManager, FacilityManagerResult} from "./facility-manager.model";

const initialState: FacilityManagerResult = {
    data: [], totalSize: 0
};

export function facilityManagerResultReducer(state = initialState, action: FindPagedFacilityManagersSuccessAction): FacilityManagerResult {
    switch (action.type) {
        case FIND_PAGED_FACILITY_MANAGERS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function facilityManagersReducer(state = [], action: FindAllFacilityManagersSuccessAction): FacilityManager[] {
    switch (action.type) {
        case FIND_ALL_FACILITY_MANAGERS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
