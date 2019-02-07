package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

public interface DexAssetCode extends DexMetaObject{
    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
