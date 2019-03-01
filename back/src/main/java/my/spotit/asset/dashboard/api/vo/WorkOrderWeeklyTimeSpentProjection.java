package my.spotit.asset.dashboard.api.vo;

import java.math.BigDecimal;

public class WorkOrderWeeklyTimeSpentProjection {
    private Integer week;
    private BigDecimal total;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
