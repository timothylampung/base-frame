package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

public interface DexPart extends DexMetaObject {
    String getDescription();

    void setDescription(String description);

    DexPartCode getPartCode();

    void setPartCode(DexPartCode partCode);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

    String getCode();

    void setCode(String code);
}
