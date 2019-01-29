package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexComponent extends DexMetaObject {
    void setId(Long id);

    String getDescription();

    void setDescription(String description);

    DexPartCode getPartCode();

    void setPartCode(DexPartCode partCode);

    DexAsset getAsset();

    void setAsset(DexAsset asset);

    String getCode();

    void setCode(String code);
}
