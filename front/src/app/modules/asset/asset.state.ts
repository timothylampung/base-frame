import {ActionReducer, ActionReducerMap, createFeatureSelector} from "@ngrx/store";
import {AppState} from "../../core/core.state";
import {AssetCode, AssetCodeResult} from "./asset-codes/asset-code-model";
import {assetCodeResultReducer, assetCodesReducer} from "./asset-codes/asset-code.reducer";
import {
    locationResultReducer,
    locationsReducer,
    locationUploadedStatusReducer
} from "./locations/location.reducer";
import {Location, LocationResult, LocationUploadStatus} from "./locations/location.model";
import {Asset, AssetResult, AssetUploadStatus} from "./assets/asset.model";
import {
    assetResultReducer,
    assetsReducer,
    assetUploadedStatusReducer
} from "./assets/asset.reducer";


export const FEATURE_NAME = 'asset';
export const selectAssetState = createFeatureSelector<State, AssetState>(
    FEATURE_NAME
);

export const reducers: ActionReducerMap<AssetState> = {
    assetCodes: assetCodesReducer,
    assetCodeResult: assetCodeResultReducer,
    locations: locationsReducer,
    locationResult: locationResultReducer,
    locationUploadStatus: locationUploadedStatusReducer,
    assets: assetsReducer,
    assetResult: assetResultReducer,
    assetUploadStatus: assetUploadedStatusReducer,
};

export interface AssetState {
    assetCodes: AssetCode[];
    assetCodeResult: AssetCodeResult;
    locations: Location[];
    locationResult: LocationResult;
    locationUploadStatus: LocationUploadStatus;
    assets: Asset[];
    assetResult: AssetResult;
    assetUploadStatus: AssetUploadStatus;
}

export interface State extends AppState {
    asset: AssetState;
}
