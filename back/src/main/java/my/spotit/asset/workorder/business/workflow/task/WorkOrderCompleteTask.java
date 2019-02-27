package my.spotit.asset.workorder.business.workflow.task;

import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.workorder.business.event.WorkOrderCompletedEvent;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@Transactional
@Component("workOrder_complete_ST")
public class WorkOrderCompleteTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderCompleteTask.class);

    public void execute(DelegateExecution execution) {
        LOG.debug("completing order");

        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexWorkOrder order = workOrderService.findWorkOrderById(orderId);

        // update flow state
        order.getFlowdata().setState(DexFlowState.COMPLETED);
        workOrderService.updateWorkOrder(order);
        applicationContext.publishEvent(new WorkOrderCompletedEvent(order));



    }
}
