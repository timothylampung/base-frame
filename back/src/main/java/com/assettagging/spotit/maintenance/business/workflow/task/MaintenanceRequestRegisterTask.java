package com.assettagging.spotit.maintenance.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_register_ST")
public class MaintenanceRequestRegisterTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestRegisterTask.class);

    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(orderId);
        LOG.info("registering request refno {}", order.getReferenceNo());

        // update flow state
        order.getFlowdata().setState(DexFlowState.REGISTERED);
        order.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);

    }
}
