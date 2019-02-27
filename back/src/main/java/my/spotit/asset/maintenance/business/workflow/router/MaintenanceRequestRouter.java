package my.spotit.asset.maintenance.business.workflow.router;


import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
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

    public String findDrafterAssignee(Long requestId) {
        String candidate = null;
        Assert.notNull(requestId, "Id must not be null");
        candidate = "tech1";// todo
        return candidate   ;
    }

    public String findDrafterCandidates(Long requestId) {
        String candidate = null;
        Assert.notNull(requestId, "Id must not be null");
        candidate = "zamir.zaharul";// todo
        return candidate   ;
    }

}
