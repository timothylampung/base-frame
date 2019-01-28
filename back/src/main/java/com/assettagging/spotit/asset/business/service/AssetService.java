package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.asset.domain.model.DexLocation;

import java.util.List;

public interface AssetService {

    //==============================================================================================
    // ASSET CODE
    //==============================================================================================

    DexAssetCode findAssetCodeById(Long id);

    DexAssetCode findAssetCodeByCode(String code);

    List<DexAssetCode> findAssetCodes(String filter, Integer offset, Integer limit);

    Integer countAssetCode();

    void saveAssetCode(DexAssetCode AssetCode);

    void updateAssetCode(DexAssetCode AssetCode);

    void removeAssetCode(DexAssetCode AssetCode);

    //==============================================================================================
    // LOCATION
    //==============================================================================================

    DexLocation findLocationById(Long id);

    DexLocation findLocationByCode(String code);

    List<DexLocation> findLocations(String filter, Integer offset, Integer limit);

    Integer countLocation();

    Integer countLocation(String filter);

    void saveLocation(DexLocation Location);

    void updateLocation(DexLocation Location);

    void removeLocation(DexLocation Location);

    //==============================================================================================
    // ASSET
    //==============================================================================================

    DexAsset findAssetById(Long id);

    DexAsset findAssetByAssetCode(String code);
    
    DexAsset findAssetByLocation(String location);

    List<DexAsset> findAssets(String filter, Integer offset, Integer limit);

    Integer countAsset();

    void saveAsset(DexAsset Asset);

    void updateAsset(DexAsset Asset);

    void removeAsset(DexAsset Asset);
}