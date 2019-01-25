import {
    FIND_ALL_ASSET_CODES_SUCCESS,
    FIND_PAGED_ASSET_CODES_SUCCESS,
    FindAllAssetCodesSuccessAction, FindPagedAssetCodesSuccessAction
} from "./asset-code.action";
import {AssetCode, AssetCodeResult} from "./asset-code-model";


const initialState: AssetCodeResult = {
    data: [], totalSize: 0
};

export function assetCodeResultReducer(state = initialState, action: FindPagedAssetCodesSuccessAction): AssetCodeResult {
    switch (action.type) {
        case FIND_PAGED_ASSET_CODES_SUCCESS:
            return {
                data: action.payload.data,
                totalSize: action.payload.totalSize,
            };
        default: {
            return state;
        }
    }
}

export function assetCodesReducer(state = [], action: FindAllAssetCodesSuccessAction): AssetCode[] {
    switch (action.type) {
        case FIND_ALL_ASSET_CODES_SUCCESS:
            return [...state, ...action.payload];
        default: {
            return state;
        }
    }
}
