package my.spotit.asset.workorder.domain.model;

import java.sql.Timestamp;

import my.spotit.asset.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexWorkOrderLog extends DexMetaObject {

    Timestamp getStartTime();

    void setStartTime(Timestamp startTime);

    Timestamp getEndTime();

    void setEndTime(Timestamp endTime);

    DexWorkOrder getWorkOrder();

    void setWorkOrder(DexWorkOrder workOrder);

}
