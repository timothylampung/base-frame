package com.assettagging.spotit.workorder.business.workflow.task;


import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static org.slf4j.LoggerFactory.getLogger;

@Component("invoice_register_ST")
public class WorkOrderRegisterTask extends WorkOrderTaskSupport {

    private static final Logger LOG = getLogger(WorkOrderRegisterTask.class);

    public void execute(DelegateExecution execution) {
//        Long invoiceId = (Long) execution.getVariable(DexConstants.INVOICE_ID);
//        DexWorkOrder invoice = workOrderService.findInvoiceById(invoiceId);
//        LOG.info("registering invoice refno {}", invoice.getReferenceNo());
//
//        // update flow state
//        invoice.getFlowdata().setState(DexFlowState.REGISTERED);
//        invoice.getFlowdata().setRegisteredDate(new Timestamp(System.currentTimeMillis()));
//        invoice.getFlowdata().setRegistererId(securityService.getCurrentUser().getId());
//        workOrderService.updateInvoice(invoice);

    }
}
