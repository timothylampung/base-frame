package my.spotit.asset.workorder.business.workflow.task;


import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static my.spotit.asset.workflow.business.service.WorkflowConstants.REMOVE_COMMENT;
import static org.slf4j.LoggerFactory.getLogger;

@Component("workOrder_remove_ST")
public class WorkOrderRemoveTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderRemoveTask.class);

    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexWorkOrder order = workOrderService.findWorkOrderById(orderId);
        LOG.info("removing order refno {}", order.getReferenceNo());

//         serialize remove comment
        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);

//         update flow state
        order.getFlowdata().setState(DexFlowState.REMOVED);
        order.setReferenceNo(removeComment);
        order.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
        workOrderService.updateWorkOrder(order);
    }
}
