package my.spotit.asset.dashboard.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import my.spotit.asset.dashboard.api.vo.WorkOrderWeeklyProjection;
import my.spotit.asset.dashboard.business.service.DashboardService;
import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyProjection;


@RestController
@Transactional
@RequestMapping("/api/dashboard")
public class DashboardController {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    private DashboardService dashboardService;
    private DashboardTransformer dashboardTransformer;

    @Autowired
    public DashboardController(DashboardService dashboardService,
                               DashboardTransformer dashboardTransformer) {
        this.dashboardService = dashboardService;
        this.dashboardTransformer = dashboardTransformer;
    }

    @GetMapping("/work-order-weekly-projections")
    public ResponseEntity<List<WorkOrderWeeklyProjection>> getWorkOrderWeeklyProjections() {
        List<DexWorkOrderWeeklyProjection> projections = dashboardService.findWorkOrderWeeklyProjections();
        return ResponseEntity.ok(dashboardTransformer.toWorkOrderWeeklyProjectionVos(projections));
    }
}
