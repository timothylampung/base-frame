package my.spotit.asset.workorder.api.vo;

import java.sql.Timestamp;

import my.spotit.asset.core.api.MetaObject;
import my.spotit.asset.identity.api.vo.User;

/**
 * {
 * "startTime": 12313123,
 * "stopTime": 12313123,
 * }
 *
 * @author canang technologies
 */
public class WorkOrderLog extends MetaObject {
    private Timestamp startTime;
    private Timestamp stopTime;
    private User logger;
    private Long workOrderId;

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getStopTime() {
        return stopTime;
    }

    public void setStopTime(Timestamp stopTime) {
        this.stopTime = stopTime;
    }

    public User getLogger() {
        return logger;
    }

    public void setLogger(User logger) {
        this.logger = logger;
    }
}
