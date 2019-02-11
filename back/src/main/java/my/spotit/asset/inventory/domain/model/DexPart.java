package my.spotit.asset.inventory.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

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
