package my.spotit.asset.dashboard.domain.model;

import java.sql.Timestamp;

public interface DexWorkOrderWeeklyProjection {

    Integer getWeek();

    void setWeek(Integer week);

    Integer getTotal();

    void setTotal(Integer total);

    Timestamp getWeekstart();

    void setWeekstart(Timestamp weekstart);

    Timestamp getWeekend();

    void setWeekend(Timestamp weekend);
}
