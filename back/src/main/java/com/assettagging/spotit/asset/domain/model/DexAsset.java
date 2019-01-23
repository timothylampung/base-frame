package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

public interface DexAsset  extends  DexMetaObject  {

    Long getId();

    void setId(Long id);

    DexMetadata getMetadata();

    void setMetadata(DexMetadata metadata);

    DexLocation getLocation();

    void setLocation(DexLocation location);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    DexAssetCode getAssetCode();

    void setAssetCode(DexAssetCode assetCode);
}
