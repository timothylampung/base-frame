package my.spotit.asset.dashboard.business.service;


import java.util.List;

import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyProjection;
import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyTimeSpentProjection;

public interface DashboardService {

    List<DexWorkOrderWeeklyProjection> findWorkOrderWeeklyProjections();

    List<DexWorkOrderWeeklyTimeSpentProjection> findWorkOrderWeeklyTimeSpentProjections();

}
