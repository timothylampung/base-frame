import {Supervisor, SupervisorResult} from "./supervisor.model";
import {
    FIND_ALL_SUPERVISORS_SUCCESS,
    FIND_PAGED_SUPERVISORS_SUCCESS,
    FindAllSupervisorsSuccessAction,
    FindPagedSupervisorsSuccessAction
} from "./supervisor.action";


const initialState: SupervisorResult = {
    data: [], totalSize: 0
};

export function supervisorResultReducer(state = initialState, action: FindPagedSupervisorsSuccessAction): SupervisorResult {
    switch (action.type) {
        case FIND_PAGED_SUPERVISORS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function supervisorsReducer(state = [], action: FindAllSupervisorsSuccessAction): Supervisor[] {
    switch (action.type) {
        case FIND_ALL_SUPERVISORS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
