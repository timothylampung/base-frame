package com.assettagging.spotit.workorder.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.workorder.business.event.WorkOrderCompletedEvent;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;

import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


import static org.slf4j.LoggerFactory.getLogger;

@Component("workOrder_approve_ST")
public class WorkOrderApproveTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderApproveTask.class);

    public void execute(DelegateExecution execution) {
        LOG.debug("approving work order");

        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexWorkOrder order = workOrderService.findWorkOrderById(orderId);

        // update flow state
        order.getFlowdata().setState(DexFlowState.APPROVED);
        order.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        workOrderService.updateWorkOrder(order);

        applicationContext.publishEvent(new WorkOrderCompletedEvent(order));
    }
}
