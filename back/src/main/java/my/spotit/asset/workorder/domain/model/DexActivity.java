package my.spotit.asset.workorder.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexActivity  extends DexMetaObject  {

    String getDescription();

    void setDescription(String description);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

}
