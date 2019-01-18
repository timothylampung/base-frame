import {Department, DepartmentResult} from './department.model';
import {
    FIND_ALL_DEPARTMENTS_SUCCESS,
    FIND_PAGED_DEPARTMENTS_SUCCESS, FindAllDepartmentsSuccessAction,
    FindPagedDepartmentsSuccessAction
} from './department.action';

const initialState: DepartmentResult = {
    data: [], totalSize: 0
};

export function departmentResultReducer(state = initialState, action: FindPagedDepartmentsSuccessAction): DepartmentResult {
    switch (action.type) {
        case FIND_PAGED_DEPARTMENTS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function departmentsReducer(state = [], action: FindAllDepartmentsSuccessAction): Department[] {
    switch (action.type) {
        case FIND_ALL_DEPARTMENTS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
