package my.spotit.asset.maintenance.business.workflow.task;

import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.business.event.MaintenanceRequestCompletedEvent;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;

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


        // serialize to work orders
        if (request.isDelegated())
            workOrderService.serializeToWorkOrder(request);
        else {
            // do something else
        }
    }
}
