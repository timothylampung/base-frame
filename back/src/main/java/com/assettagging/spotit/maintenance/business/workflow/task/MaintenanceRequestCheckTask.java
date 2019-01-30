package com.assettagging.spotit.maintenance.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_check_ST")
public class MaintenanceRequestCheckTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestCheckTask.class);

    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(orderId);
        LOG.info("checking request refno {}", order.getReferenceNo());

//         update flow state
        order.getFlowdata().setState(DexFlowState.CHECKED);
        order.getFlowdata().setCheckedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setCheckerId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);
    }
}
