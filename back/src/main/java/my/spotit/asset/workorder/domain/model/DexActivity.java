package my.spotit.asset.workorder.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexActivity  extends DexMetaObject  {
    void setId(Long id);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
