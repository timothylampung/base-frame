package my.spotit.asset.dashboard.domain.model;

import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity(name = "DexWorkOrderWeeklyTimeSpentProjection")
@Table(name = "DEX_WORK_ORDR_WEEK_TIME_SPNT_PRJN")
public class DexWorkOrderWeeklyTimeSpentProjectionImpl implements DexWorkOrderWeeklyTimeSpentProjection {

    @Id
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

    @Override
    public String toString() {
        return "DexWorkOrderWeeklyProjectionImpl{" +
                "week='" + week + '\'' +
                ", total=" + total +
                '}';
    }
}
