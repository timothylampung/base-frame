
import {MaintenanceRequest, MaintenanceRequestResult} from "./maintenance-request.model";
import {
    FIND_ALL_MAINTENANCE_REQUESTS_SUCCESS,
    FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS, FindAllMaintenanceRequestsSuccessAction,
    FindPagedMaintenanceRequestsSuccessAction
} from "./maintenance-request.action";

const initialState: MaintenanceRequestResult = {
    data: [], totalSize: 0
};

export function maintenanceRequestResultReducer(state = initialState, action: FindPagedMaintenanceRequestsSuccessAction): MaintenanceRequestResult {
    switch (action.type) {
        case FIND_PAGED_MAINTENANCE_REQUESTS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function maintenanceRequestsReducer(state = [], action: FindAllMaintenanceRequestsSuccessAction): MaintenanceRequest[] {
    switch (action.type) {
        case FIND_ALL_MAINTENANCE_REQUESTS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
