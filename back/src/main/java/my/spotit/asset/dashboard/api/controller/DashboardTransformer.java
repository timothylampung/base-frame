package my.spotit.asset.dashboard.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.dashboard.api.vo.WorkOrderWeeklyProjection;
import my.spotit.asset.dashboard.domain.model.DexWorkOrderWeeklyProjection;
import my.spotit.asset.identity.api.controller.IdentityTransformer;

@Component("dashboardTransformer")
public class DashboardTransformer {

    private CommonTransformer commonTransformer;
    private CoreTransformer coreTransformer;
    private IdentityTransformer identityTransformer;

    @Autowired
    public DashboardTransformer(CommonTransformer commonTransformer,
                                CoreTransformer coreTransformer,
                                IdentityTransformer identityTransformer) {
        this.commonTransformer = commonTransformer;
        this.coreTransformer = coreTransformer;
        this.identityTransformer = identityTransformer;
    }

    public WorkOrderWeeklyProjection toWorkOrderWeeklyProjectionVo(DexWorkOrderWeeklyProjection p) {
        WorkOrderWeeklyProjection vo = new WorkOrderWeeklyProjection();
        vo.setWeek(p.getWeek());
        vo.setCount(p.getCount());
        return vo;
    }

    public List<WorkOrderWeeklyProjection> toWorkOrderWeeklyProjectionVos(List<DexWorkOrderWeeklyProjection> projections) {
        return projections.stream().map(this::toWorkOrderWeeklyProjectionVo).collect(Collectors.toList());
    }
}
