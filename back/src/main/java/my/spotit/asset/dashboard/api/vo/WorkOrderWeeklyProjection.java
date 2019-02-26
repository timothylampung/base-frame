package my.spotit.asset.dashboard.api.vo;

import java.math.BigDecimal;

public class WorkOrderWeeklyProjection {
    private Integer week;
    private Integer count;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
