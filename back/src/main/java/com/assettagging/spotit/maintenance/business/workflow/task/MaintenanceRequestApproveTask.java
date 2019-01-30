package com.assettagging.spotit.maintenance.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.business.event.MaintenanceRequestCompletedEvent;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_approve_ST")
public class MaintenanceRequestApproveTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestApproveTask.class);

    public void execute(DelegateExecution execution) {
        LOG.debug("approving request");

        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(orderId);

        // update flow state
        order.getFlowdata().setState(DexFlowState.APPROVED);
        order.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);

        applicationContext.publishEvent(new MaintenanceRequestCompletedEvent(order));
    }
}
