import {ActionReducerMap, createFeatureSelector} from '@ngrx/store';
import {AppState} from '../../core/core.state';

import {configurationResultReducer} from './configurations/configuration.reducer';
import {ConfigurationResult} from './configurations/configuration.model';

export const FEATURE_NAME = 'common';
export const selectCommonState = createFeatureSelector<State, CommonState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<CommonState> = {
    configurationResult: configurationResultReducer,
};

export interface CommonState {
    configurationResult: ConfigurationResult;

}

export interface State extends AppState {
    common: CommonState;
}
