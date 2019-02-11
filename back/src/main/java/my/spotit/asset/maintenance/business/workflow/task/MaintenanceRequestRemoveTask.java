package my.spotit.asset.maintenance.business.workflow.task;


import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import my.spotit.asset.workflow.business.service.WorkflowConstants;

import static org.slf4j.LoggerFactory.getLogger;

@Component("maintenanceRequest_remove_ST")
public class MaintenanceRequestRemoveTask extends MaintenanceRequestTaskSupport {

    private static final Logger LOG = getLogger(MaintenanceRequestRemoveTask.class);

    public void execute(DelegateExecution execution) {
        Long requestId = (Long) execution.getVariable(DexConstants.REQUEST_ID);
        DexMaintenanceRequest order = maintenanceRequestService.findMaintenanceRequestById(requestId);
        LOG.info("removing order refno {}", order.getReferenceNo());

//         serialize remove comment
        String removeComment = (String) execution.getVariable(WorkflowConstants.REMOVE_COMMENT);

//         update flow state
        order.getFlowdata().setState(DexFlowState.REMOVED);
        order.setReferenceNo(removeComment);
        order.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        maintenanceRequestService.updateMaintenanceRequest(order);
    }
}
