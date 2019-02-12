package my.spotit.asset.integration.mobile.workorder.api;

import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/api/mobile/work-order")
public class IntegrationWorkOrderController {

    private WorkOrderService workOrderService;
    private MobileSecurityService securityService;

    @Autowired
    public IntegrationWorkOrderController(WorkOrderService workOrderService, MobileSecurityService securityService) {
        this.workOrderService = workOrderService;
        this.securityService = securityService;
    }



}
