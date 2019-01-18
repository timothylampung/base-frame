import {
    FIND_PAGED_GROUPS_SUCCESS,
    FindPagedGroupsSuccessAction
} from './group.action';
import {GroupResult} from '../group-result.model';


const initialState: GroupResult = {
    data: [], totalSize: 0
};

export function groupResultReducer(state = initialState, action: FindPagedGroupsSuccessAction): GroupResult {
    switch (action.type) {
        case FIND_PAGED_GROUPS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}
