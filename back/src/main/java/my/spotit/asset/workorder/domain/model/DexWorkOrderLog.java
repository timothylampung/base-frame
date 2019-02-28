package my.spotit.asset.workorder.domain.model;

import java.sql.Timestamp;

import my.spotit.asset.core.domain.DexMetaObject;
import my.spotit.asset.identity.domain.model.DexUser;

/**
 * @author canang technologies
 */
public interface DexWorkOrderLog extends DexMetaObject {

    Timestamp getStartTime();

    void setStartTime(Timestamp startTime);

    Timestamp getStopTime();

    void setStopTime(Timestamp stopTime);

    DexUser getLogger();

    void setLogger(DexUser logger);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

}
