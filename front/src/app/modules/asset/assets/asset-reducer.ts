import {
    FIND_ALL_ASSETS_SUCCESS,
    FIND_PAGED_ASSETS_SUCCESS,
    FindAllAssetsSuccessAction, FindPagedAssetsSuccessAction
} from "./asset-action";
import {Asset, AssetResult} from "./asset-model";


const initialState: AssetResult = {
    data: [], totalSize: 0
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
