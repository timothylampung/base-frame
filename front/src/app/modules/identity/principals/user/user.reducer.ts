import {UserResult} from "./user-result.model";
import {
    FIND_ALL_USERS_SUCCESS,
    FIND_PAGED_USERS_SUCCESS,
    FindAllUsersSuccessAction,
    FindPagedUsersSuccessAction
} from "./user.action";
import {User} from "./user.model";

const initialState: UserResult = {
    data: [], totalSize: 0
};

export function userResultReducer(state = initialState, action: FindPagedUsersSuccessAction): UserResult {
    switch (action.type) {
        case FIND_PAGED_USERS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function usersReducer(state = [], action: FindAllUsersSuccessAction): User[] {
    switch (action.type) {
        case FIND_ALL_USERS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
