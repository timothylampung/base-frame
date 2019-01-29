package com.assettagging.spotit.workorder.business.workflow.task;


import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("invoice_remove_ST")
public class WorkOrderRemoveTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderRemoveTask.class);

    public void execute(DelegateExecution execution) {
//        Long invoiceId = (Long) execution.getVariable(DexConstants.INVOICE_ID);
//        DexWorkOrder invoice = workOrderService.findInvoiceById(invoiceId);
//        LOG.info("removing invoice refno {}", invoice.getReferenceNo());
//
//         serialize remove comment
//        String removeComment = (String) execution.getVariable(REMOVE_COMMENT);
//
//         update flow state
//        invoice.getFlowdata().setState(DexFlowState.REMOVED);
//        invoice.setReferenceNo(removeComment);
//        invoice.getFlowdata().setRemovedDate(new Timestamp(System.currentTimeMillis()));
//        invoice.getFlowdata().setRemoverId(securityService.getCurrentUser().getId());
//        workOrderService.updateInvoice(invoice);
    }
}
