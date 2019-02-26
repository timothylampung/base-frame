package my.spotit.asset.dashboard.business.service;


import java.util.List;

import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyProjection;

public interface DashboardService {

    List<DexWorkOrderWeeklyProjection> findWorkOrderWeeklyProjections();

}
