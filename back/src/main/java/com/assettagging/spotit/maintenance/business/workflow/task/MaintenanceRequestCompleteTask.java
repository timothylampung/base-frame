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
        LOG.debug("completing request");

        Long requestId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest request = maintenanceRequestService.findMaintenanceRequestById(requestId);

        // update flow state
        request.getFlowdata().setState(DexFlowState.COMPLETED);
        maintenanceRequestService.updateMaintenanceRequest(request);
        applicationContext.publishEvent(new MaintenanceRequestCompletedEvent(request));


        // FIXME: 7/2/2019 serialize
        // serialize to work orders
        if (request.isDelegated())
            workOrderService.serializeToWorkOrder(request);
        else {
            // do something else
        }
    }
}
