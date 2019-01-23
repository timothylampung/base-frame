package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

public interface DexActivity  extends DexMetaObject  {
    void setId(Long id);

    DexAsset getWorkOrder();

    void setWorkOrder(DexAsset workOrder);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
