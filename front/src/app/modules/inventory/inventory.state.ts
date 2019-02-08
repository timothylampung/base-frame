import {ActionReducer, ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";

import {Part, PartResult} from "./parts/part-model";
import { partResultReducer, partsReducer} from "./parts/part-reducer";


export const FEATURE_NAME = 'part';
export const selectPartState = createFeatureSelector<State, InventoryState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<InventoryState> = {


    parts:partsReducer,
    partResult:partResultReducer,

    
    
};

export interface InventoryState {

    parts:Part[];
    partResult:PartResult;
    
}

export interface State extends AppState {
    part: InventoryState;
}
