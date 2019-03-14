package my.spotit.asset.dashboard.api.vo;

import java.math.BigDecimal;

public class WorkOrderWeeklyProjection {
    private Integer week;
    private Integer total;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
