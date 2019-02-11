import {ActionReducer, ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";

import {Part, PartResult} from "./parts/part-model";
import { partResultReducer, partsReducer} from "./parts/part-reducer";
import {Component, ComponentResult} from "./components/component-model";
import {componentResultReducer, componentsReducer} from "./components/component-reducer";
import {PartCode, PartCodeResult} from "./part-codes/part-code-model";
import {ParsedCssResult} from "codelyzer/angular/styles/cssParser";
import {partCodeResultReducer, partCodesReducer} from "./part-codes/part-code-reducer";


export const FEATURE_NAME = 'component';
export const selectInventoryState = createFeatureSelector<State, InventoryState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<InventoryState> = {


    parts:partsReducer,
    partResult:partResultReducer,
    components: componentsReducer,
    componentResult: componentResultReducer,
    partCodes:partCodesReducer,
    partCodeResult:partCodeResultReducer,

    
    
};

export interface InventoryState {

    parts:Part[];
    partResult:PartResult;
    components:Component[];
    componentResult:ComponentResult;
    partCodes:PartCode[],
    partCodeResult: PartCodeResult;
    
}

export interface State extends AppState {
    component: InventoryState;
}
