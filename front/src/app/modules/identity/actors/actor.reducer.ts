import {ActorResult} from "./actor-result.model";
import {
    FIND_ALL_ACTORS_SUCCESS,
    FIND_PAGED_ACTORS_SUCCESS,
    FindAllActorsSuccessAction,
    FindPagedActorsSuccessAction
} from "./actor.action";
import {Actor} from "./actor.model";

const initialState: ActorResult = {
    data: [], totalSize: 0
};

export function actorResultReducer(state = initialState, action: FindPagedActorsSuccessAction): ActorResult {
    console.log(action.payload)
    switch (action.type) {
        case FIND_PAGED_ACTORS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function actorsReducer(state = [], action: FindAllActorsSuccessAction): Actor[] {
    switch (action.type) {
        case FIND_ALL_ACTORS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
