import {createSelector} from '@ngrx/store';
import {selectCommonState} from '../common.state';


export const selectConfigurationResultState = createSelector(selectCommonState, state => state.configurationResult);
export const selectConfigurations = createSelector(selectConfigurationResultState, state => state.data);
export const selectConfigurationTotalSize = createSelector(selectConfigurationResultState, state => state.totalSize);
