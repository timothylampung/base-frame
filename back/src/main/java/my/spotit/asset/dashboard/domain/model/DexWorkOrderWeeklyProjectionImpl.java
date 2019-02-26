package my.spotit.asset.dashboard.domain.model;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity(name = "DexWorkOrderWeeklyProjection")
@Table(name = "DEX_WORK_ORDR_WEEK_PRJN")
@Subselect("select * from DEX_WORK_ORDR_WEEK_PRJN")
public class DexWorkOrderWeeklyProjectionImpl implements DexWorkOrderWeeklyProjection {

    @Id
    private Integer week;

    private Integer count;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DexWorkOrderWeeklyProjectionImpl{" +
                "week='" + week + '\'' +
                ", count=" + count +
                '}';
    }
}
