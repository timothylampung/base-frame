package com.assettagging.spotit.workorder.business.workflow.task;


import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.core.domain.DexFlowState;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("workOrder_draft_ST")
public class WorkOrderDraftTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderDraftTask.class);

    public void execute(DelegateExecution execution) {

        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexWorkOrder order = workOrderService.findWorkOrderById(orderId);
        LOG.info("drafting order refno {}", order.getReferenceNo());

        // update flow state
        order.getFlowdata().setState(DexFlowState.DRAFTED);
        order.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        workOrderService.updateWorkOrder(order);
    }
}
