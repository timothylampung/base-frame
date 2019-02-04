import {Technician, TechnicianResult} from './technician.model';
import {
    FIND_ALL_TECHNICIANS_SUCCESS,
    FIND_PAGED_TECHNICIANS_SUCCESS, FindAllTechniciansSuccessAction,
    FindPagedTechniciansSuccessAction
} from './technician.action';

const initialState: TechnicianResult = {
    data: [], totalSize: 0
};

export function technicianResultReducer(state = initialState, action: FindPagedTechniciansSuccessAction): TechnicianResult {
    switch (action.type) {
        case FIND_PAGED_TECHNICIANS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function techniciansReducer(state = [], action: FindAllTechniciansSuccessAction): Technician[] {
    switch (action.type) {
        case FIND_ALL_TECHNICIANS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
