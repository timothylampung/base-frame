package my.spotit.asset.maintenance.business.workflow.task;


import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.business.event.MaintenanceRequestCompletedEvent;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
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

        Long requestId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(requestId);

        // update flow state
        order.getFlowdata().setState(DexFlowState.APPROVED);
        order.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);

        applicationContext.publishEvent(new MaintenanceRequestCompletedEvent(order));
    }
}
