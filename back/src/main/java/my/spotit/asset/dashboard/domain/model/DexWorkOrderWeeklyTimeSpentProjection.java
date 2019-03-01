package my.spotit.asset.dashboard.domain.model;

import java.math.BigDecimal;

public interface DexWorkOrderWeeklyTimeSpentProjection {

    Integer getWeek();

    void setWeek(Integer week);

    BigDecimal getTotal();

    void setTotal(BigDecimal total);
}
