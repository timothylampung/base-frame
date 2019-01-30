package com.assettagging.spotit.maintenance.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static com.assettagging.spotit.workflow.business.service.WorkflowConstants.REMOVE_COMMENT;
import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_remove_ST")
public class MaintenanceRequestRemoveTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestRemoveTask.class);

    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(orderId);
        LOG.info("removing order refno {}", order.getReferenceNo());

//         serialize remove comment
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);

//         update flow state
        order.getFlowdata().setState(DexFlowState.REMOVED);
        order.setReferenceNo(removeComment);
        order.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);
    }
}
