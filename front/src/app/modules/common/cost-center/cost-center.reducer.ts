import {CostCenter, CostCenterResult} from "./cost-center.model";
import {
    FIND_ALL_COST_CENTERS_SUCCESS,
    FIND_PAGED_COST_CENTERS_SUCCESS, FindAllCostCentersSuccessAction,
    FindPagedCostCentersSuccessAction
} from "./cost-center.action";


const initialState: CostCenterResult = {
    data: [], totalSize: 0
};

export function costCenterResultReducer(state = initialState, action: FindPagedCostCentersSuccessAction): CostCenterResult {
    switch (action.type) {
        case FIND_PAGED_COST_CENTERS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function costCentersReducer(state = [], action: FindAllCostCentersSuccessAction): CostCenter[] {
    switch (action.type) {
        case FIND_ALL_COST_CENTERS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
