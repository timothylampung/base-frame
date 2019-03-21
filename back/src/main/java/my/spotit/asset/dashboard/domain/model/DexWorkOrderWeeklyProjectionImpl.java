package my.spotit.asset.dashboard.domain.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Immutable
@Entity(name = "DexWorkOrderWeeklyProjection")
@Table(name = "DEX_WORK_ORDR_WEEK_PRJN")
public class DexWorkOrderWeeklyProjectionImpl implements DexWorkOrderWeeklyProjection {

    @Id
    @Column(name = "week")
    private Integer week;
    @Column(name = "total")
    private Integer total;
    @Column(name = "weekstart")
    private Timestamp weekstart;
    @Column(name = "weekend")
    private Timestamp weekend;

    @Override
    public Integer getWeek() {
        return week;
    }

    @Override
    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public Timestamp getWeekstart() {
        return weekstart;
    }

    @Override
    public void setWeekstart(Timestamp weekstart) {
        this.weekstart = weekstart;
    }

    @Override
    public Timestamp getWeekend() {
        return weekend;
    }

    @Override
    public void setWeekend(Timestamp weekend) {
        this.weekend = weekend;
    }

    @Override
    public String toString() {
        return "DexWorkOrderWeeklyProjectionImpl{" +
                "week='" + week + '\'' +
                ", total=" + total +
                '}';
    }
}
