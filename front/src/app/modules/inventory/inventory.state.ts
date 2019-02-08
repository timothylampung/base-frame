import {ActionReducer, ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";

import {Part, PartResult} from "./parts/part-model";
import { partResultReducer, partsReducer} from "./parts/part-reducer";
import {Component, ComponentResult} from "./components/component-model";
import {componentResultReducer, componentsReducer} from "./components/component-reducer";


export const FEATURE_NAME = 'component';
export const selectInventoryState = createFeatureSelector<State, InventoryState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<InventoryState> = {


    parts:partsReducer,
    partResult:partResultReducer,
    components: componentsReducer,
    componentResult: componentResultReducer,

    
    
};

export interface InventoryState {

    parts:Part[];
    partResult:PartResult;
    components:Component[];
    componentResult:ComponentResult;
    
}

export interface State extends AppState {
    component: InventoryState;
}
