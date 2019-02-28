package my.spotit.asset.workorder.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;
import my.spotit.asset.identity.domain.model.DexUser;

/**
 * @author team canang
 */
public interface DexWorkOrderComment extends DexMetaObject {

    String getBody();

    void setBody(String body);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

    DexUser getPoster();

    void setPoster(DexUser poster);
}
