package my.spotit.asset.workorder.business.workflow.task;


import my.spotit.asset.DexConstants;
import my.spotit.asset.core.domain.DexFlowState;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("workOrder_verify_ST")
public class WorkOrderVerifyTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderVerifyTask.class);

    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable(DexConstants.ORDER_ID);
        DexWorkOrder order = workOrderService.findWorkOrderById(orderId);
        LOG.info("registering order refno {}", order.getReferenceNo());

        // update flow state
        order.getFlowdata().setState(DexFlowState.VERIFIED);
        order.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
        order.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
        workOrderService.updateWorkOrder(order);

    }
}
