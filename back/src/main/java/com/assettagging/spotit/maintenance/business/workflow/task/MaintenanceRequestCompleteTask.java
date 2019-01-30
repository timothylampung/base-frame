package com.assettagging.spotit.maintenance.business.workflow.task;

import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.maintenance.business.event.MaintenanceRequestCompletedEvent;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@Transactional
@Component("maintenanceRequest_complete_ST")
public class MaintenanceRequestCompleteTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestCompleteTask.class);

    public void execute(DelegateExecution execution) {
        LOG.debug("completing order");

        Long orderId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(orderId);

        // update flow state
        order.getFlowdata().setState(DexFlowState.COMPLETED);
        maintenanceRequestService.updateMaintenanceRequest(order);
        applicationContext.publishEvent(new MaintenanceRequestCompletedEvent(order));


        // serialize to work orders
    }
}
