package my.spotit.asset.workorder.api.vo;

import java.sql.Timestamp;

import my.spotit.asset.core.api.MetaObject;

/**
 * {
 *     "startTime": 12313123,
 *     "endTime": 12313123,
 * }
 *
 * @author canang technologies
 */
public class WorkOrderLog extends MetaObject
{
    private Timestamp startTime;
    private Timestamp endTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
