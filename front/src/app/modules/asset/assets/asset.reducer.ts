import {
    FIND_ALL_ASSETS_SUCCESS,
    FIND_PAGED_ASSETS_SUCCESS,
    FindAllAssetsSuccessAction,
    FindPagedAssetsSuccessAction,
    UPLOAD_ASSET,
    UPLOAD_ASSET_ERROR,
    UPLOAD_ASSET_SUCCESS
} from "./asset.action";
import {Asset, AssetResult, AssetUploadStatus} from "./asset.model";
import {
    UPLOAD_LOCATION,
    UPLOAD_LOCATION_ERROR,
    UPLOAD_LOCATION_SUCCESS
} from "../locations/location-action";
import {LocationUploadStatus} from "../locations/location.model";


const initialState: AssetResult = {
    data: [], totalSize: 0
};

const initialUploadStatusState: AssetUploadStatus = {
    uploaded: false, isError: false, errorMsg: ''
};


export function assetResultReducer(state = initialState, action: FindPagedAssetsSuccessAction): AssetResult {
    switch (action.type) {
        case FIND_PAGED_ASSETS_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function assetsReducer(state = [], action: FindAllAssetsSuccessAction): Asset[] {
    switch (action.type) {
        case FIND_ALL_ASSETS_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}

export function assetUploadedStatusReducer(state = initialUploadStatusState, action) {
    switch (action.type) {
        case UPLOAD_ASSET:
            return {...state, uploaded: true};
        case UPLOAD_ASSET_SUCCESS:
            return {...state, ...action.payload, isError: false, uploaded: false};
        case UPLOAD_ASSET_ERROR:
            return {
                ...state,
                isError: true,
                uploaded: false,
                errorMsg: action.payload.error.message
            };
        default: {
            return state;
        }
    }
}
