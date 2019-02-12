package my.spotit.asset.asset.business.service;

import org.flowable.task.api.Task;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import java.util.List;

public interface AssetService {

    //==============================================================================================
    // ASSET CODE
    //==============================================================================================

    DexAssetCode findAssetCodeById(Long id);

    DexAssetCode findAssetCodeByCode(String code);

    List<DexAssetCode> findAssetCodes(String filter, Integer offset, Integer limit);

    Integer countAssetCode();

    Integer countAssetCode(String filter);


    void saveAssetCode(DexAssetCode AssetCode);

    void updateAssetCode(DexAssetCode AssetCode);

    void removeAssetCode(DexAssetCode AssetCode);

    //==============================================================================================
    // LOCATION
    //==============================================================================================

    DexLocation findLocationById(Long id);

    DexLocation findLocationByCode(String code);

    List<DexLocation> findLocations();

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

    List<DexAsset> findAssets(String filter, Integer offset, Integer limit);

    List<DexAsset> findAssetsByLocation(DexLocation location);

    Integer countAsset();

    Integer countAsset(String filter);

    void saveAsset(DexAsset asset);

    void updateAsset(DexAsset asset);

    void removeAsset(DexAsset asset);

}