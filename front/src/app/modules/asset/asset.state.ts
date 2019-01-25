import {ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";
import {AssetCode, AssetCodeResult} from "./asset-codes/asset-code-model";
import {assetCodeResultReducer, assetCodesReducer} from "./asset-codes/asset-code.reducer";

export const FEATURE_NAME = 'asset';
export const selectAssetState = createFeatureSelector<State, AssetState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<AssetState> = {

    assetCodes: assetCodesReducer,
    assetCodeResult: assetCodeResultReducer

    
    
};

export interface AssetState {
    assetCodes: AssetCode[];
    assetCodeResult: AssetCodeResult;
    
}

export interface State extends AppState {
    asset: AssetState;
}
