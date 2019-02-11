package my.spotit.asset.asset.business.service;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexLocation;

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

    List<DexLocation> findAllLocations();

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

    DexAsset findAssetByCode(String code);

    List<DexAsset> findAssetsByLocation(DexLocation location);

    Integer countAsset();

    Integer countAsset(String filter);

    void saveAsset(DexAsset asset);

    void updateAsset(DexAsset asset);

    void removeAsset(DexAsset asset);

    List<DexAsset> findAssets(String s, int i, int limit);
}