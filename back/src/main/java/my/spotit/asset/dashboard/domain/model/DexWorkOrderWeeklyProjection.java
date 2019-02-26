package my.spotit.asset.dashboard.domain.model;

public interface DexWorkOrderWeeklyProjection {

    Integer getWeek();

    void setWeek(Integer week);

    Integer getCount();

    void setCount(Integer count);
}
