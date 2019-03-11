package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

import java.util.List;

public interface DexAsset  extends  DexMetaObject  {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    String getCost();

    void setCost(String cost);

    String getQuantity();

    void setQuantity(String quantity);

    String getCategory();

    void setCategory(String category);

    List<DexAssetCategory> getAssetCategory();

    void setAssetCategory(List<DexAssetCategory> assetCategory);

    DexAssetCode getAssetCode();

    void setAssetCode(DexAssetCode assetCode);

    DexLocation getLocation();

    void setLocation(DexLocation location);

}
