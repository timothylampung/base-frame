package com.assettagging.spotit.maintenance.business.workflow.router;


import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.maintenance.business.service.MaintenanceRequestService;
import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.system.business.service.SystemService;
import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;


@Component("maintenanceRequestRouter")
public class MaintenanceRequestRouter {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestRouter.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TaskService taskService;

    public List<String> findDrafterCandidates(Long requestId) {
        String candidate = null;
        Assert.notNull(requestId, "Id must not be null");

        candidate = "GRP_USR";// todo

        return Arrays.asList(candidate, securityService.getCurrentUser().getUsername());
    }

    public List<String> findCheckerCandidates(Long requestId) {
        String candidate = null;
        Assert.notNull(requestId, "Id must not be null");

        candidate = "GRP_USR";// todo

        return Arrays.asList(candidate);
    }

    public List<String> findApproverCandidates(Long requestId) {
        String candidate = null;
        Assert.notNull(requestId, "Id must not be null");

        candidate = "GRP_USR"; // todo

        return Arrays.asList(candidate);
    }
}
