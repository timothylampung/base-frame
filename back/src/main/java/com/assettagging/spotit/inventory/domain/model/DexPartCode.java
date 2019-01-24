package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexPartCode extends DexMetaObject {
    String getDescription();

    void setDescription(String description);

    void setId(Long id);
}
