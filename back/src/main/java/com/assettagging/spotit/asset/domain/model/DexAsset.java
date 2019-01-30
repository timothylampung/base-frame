package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexAsset  extends  DexMetaObject  {

    void setId(Long id);

    DexLocation getLocation();

    void setLocation(DexLocation location);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    DexAssetCode getAssetCode();

    void setAssetCode(DexAssetCode assetCode);
}
