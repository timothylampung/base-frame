package com.assettagging.spotit.workflow.business.integration.listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class EndProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(EndProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) {
        LOG.debug("[" + execution.getProcessInstanceId() + "] Process end");
    }

}
