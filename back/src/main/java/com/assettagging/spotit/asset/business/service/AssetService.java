package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;

import java.util.List;

public interface AssetService {

/*    //==============================================================================================
    // ASSET CODE
    //==============================================================================================

    DexAssetCode findAssetCodeById(Long id);

    DexAssetCode findAssetCodeByCode(String code);

    List<DexAssetCode> findAssetCodes(String filter, Integer offset, Integer limit);

    Integer countAssetCode();

    void saveAssetCode(DexAssetCode AssetCode);

    void updateAssetCode(DexAssetCode AssetCode);

    void removeAssetCode(DexAssetCode AssetCode);*/

    //==============================================================================================
    // LOCATION
    //==============================================================================================

    List<DexLocation> findAllLocations(String s, int i, int limit);

    DexLocation findLocationById(Long id);

    DexLocation findLocationByCode(String code);

    List<DexLocation> findLocations(String filter, Integer offset, Integer limit);

    Integer countLocation(String filter);

    void saveLocation(DexLocation location);

    void updateLocation(DexLocation location);

    void removeLocation(DexLocation location);

    //==============================================================================================
    // ASSET
    //==============================================================================================

    DexAsset findAssetById(Long id);

    DexAsset findAssetByAssetCode(String code);

    List<DexAsset> findAssetsByLocation(DexLocation location);

    Integer countAsset();

    Integer countAsset(String filter);

    void saveAsset(DexAsset asset);

    void updateAsset(DexAsset asset);

    void removeAsset(DexAsset asset);

    List<DexAsset> findAllAssets(String s, int i, int limit);
}