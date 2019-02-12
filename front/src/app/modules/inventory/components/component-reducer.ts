import {
    FIND_ALL_COMPONENTS_SUCCESS,
    FIND_PAGED_COMPONENTS_SUCCESS,
    FindAllComponentsSuccessAction, FindPagedComponentsSuccessAction
} from "./component-action";
import {PartComponent, ComponentResult} from "./component-model";


const initialState: ComponentResult = {
    data: [], totalSize: 0
};

export function componentResultReducer(state = initialState, action: FindPagedComponentsSuccessAction): ComponentResult {
    switch (action.type) {
        case FIND_PAGED_COMPONENTS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function componentsReducer(state = [], action: FindAllComponentsSuccessAction): PartComponent[] {
    switch (action.type) {
        case FIND_ALL_COMPONENTS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
