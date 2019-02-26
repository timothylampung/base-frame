package my.spotit.asset.dashboard.domain.model;

import java.math.BigDecimal;

public interface DexWorkOrderProjection {

    String getMonth();

    void setMonth(String month);

    Integer getCount();

    void setCount(Integer count);
}
