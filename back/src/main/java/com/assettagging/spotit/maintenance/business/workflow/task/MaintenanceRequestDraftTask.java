package com.assettagging.spotit.maintenance.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_draft_ST")
public class MaintenanceRequestDraftTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestDraftTask.class);

    public void execute(DelegateExecution execution) {

        Long requestId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(requestId);
        LOG.info("drafting order refno {}", order.getReferenceNo());

        // update flow state
        order.getFlowdata().setState(DexFlowState.DRAFTED);
        order.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);
    }
}
