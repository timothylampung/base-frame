package com.assettagging.spotit.workorder.business.workflow.task;


import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("invoice_draft_ST")
public class WorkOrderDraftTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderDraftTask.class);

    public void execute(DelegateExecution execution) {

//        Long invoiceId = (Long) execution.getVariable(DexConstants.INVOICE_ID);
//        DexWorkOrder invoice = workOrderService.findInvoiceById(invoiceId);
//        LOG.info("drafting invoice refno {}", invoice.getReferenceNo());
//
//        // update flow state
//        invoice.getFlowdata().setState(DexFlowState.DRAFTED);
//        invoice.getFlowdata().setDraftedDate(new Timestamp(System.currentTimeMillis()));
//        invoice.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
//        workOrderService.updateInvoice(invoice);
    }
}
