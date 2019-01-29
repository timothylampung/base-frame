package com.assettagging.spotit.workorder.business.workflow.router;


import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.system.business.service.SystemService;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


@Component("workOrderRouter")
public class WorkOrderRouter {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderRouter.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TaskService taskService;

    public List<String> findDrafterCandidates(Long orderId) {
        String candidate = null;
        Assert.notNull(orderId, "Id must not be null");

        candidate = "GRP";// todo

        return Arrays.asList(candidate, securityService.getCurrentUser().getUsername());
    }

    public List<String> findCheckerCandidates(Long orderId) {
        String candidate = null;
        Assert.notNull(orderId, "Id must not be null");

        candidate = "GRP";// todo

        return Arrays.asList(candidate);
    }

    public List<String> findApproverCandidates(Long orderId) {
        String candidate = null;
        Assert.notNull(orderId, "Id must not be null");

        candidate = "GRP"; // todo

        return Arrays.asList(candidate);
    }
}
