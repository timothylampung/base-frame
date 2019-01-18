import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';

export const selectDepartmentResultState = createSelector(selectCommonState, state => state.departmentResult);
export const selectDepartments = createSelector(selectDepartmentResultState, state => state.data);
export const selectDepartmentTotalSize = createSelector(selectDepartmentResultState, state => state.totalSize);

export const selectAllDepartments = createSelector(selectCommonState, state => state.departments);
