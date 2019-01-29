package com.assettagging.spotit.workorder.business.workflow.task;


import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


import static org.slf4j.LoggerFactory.getLogger;

@Component("invoice_check_ST")
public class WorkOrderCheckTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderCheckTask.class);

    public void execute(DelegateExecution execution) {
//        Long invoiceId = (Long) execution.getVariable(DexConstants.INVOICE_ID);
//        DexWorkOrder invoice = workOrderService.findInvoiceById(invoiceId);
//        LOG.info("checking invoice refno {}", invoice.getReferenceNo());
//
//         update flow state
//        invoice.getFlowdata().setState(DexFlowState.CHECKED);
//        invoice.getFlowdata().setCheckedDate(new Timestamp(System.currentTimeMillis()));
//        invoice.getFlowdata().setCheckerId(securityService.getCurrentUser().getId());
//        workOrderService.updateInvoice(invoice);
    }
}
