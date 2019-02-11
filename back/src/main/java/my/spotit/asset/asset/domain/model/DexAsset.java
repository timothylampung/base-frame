package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexAsset  extends  DexMetaObject  {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    DexAssetCode getAssetCode();

    void setAssetCode(DexAssetCode assetCode);

    DexLocation getLocation();

    void setLocation(DexLocation location);

}
