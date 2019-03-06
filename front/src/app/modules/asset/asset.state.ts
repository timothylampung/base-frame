import {ActionReducer, ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";
import {AssetCode, AssetCodeResult} from "./asset-codes/asset-code-model";
import {assetCodeResultReducer, assetCodesReducer} from "./asset-codes/asset-code.reducer";
import {locationResultReducer, locationsReducer} from "./locations/location-reducer";
import {Location, LocationResult} from "./locations/location-model";
import {Asset, AssetResult} from "./assets/asset-model";
import {assetResultReducer, assetsReducer} from "./assets/asset-reducer";


export const FEATURE_NAME = 'asset';
export const selectAssetState = createFeatureSelector<State, AssetState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<AssetState> = {
    assetCodes: assetCodesReducer,
    assetCodeResult: assetCodeResultReducer,
    locations:locationsReducer,
    locationResult:locationResultReducer,
    assets:assetsReducer,
    assetResult:assetResultReducer,
};

export interface AssetState {
    assetCodes: AssetCode[];
    assetCodeResult: AssetCodeResult;
    locations:Location[];
    locationResult:LocationResult;
    assets:Asset[];
    assetResult:AssetResult;
}

export interface State extends AppState {
    asset: AssetState;
}
