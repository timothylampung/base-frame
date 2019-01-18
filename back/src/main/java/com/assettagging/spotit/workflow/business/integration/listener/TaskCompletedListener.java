package com.assettagging.spotit.workflow.business.integration.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class TaskCompletedListener implements TaskListener {

    private static final Logger LOG = LoggerFactory.getLogger(TaskCompletedListener.class);


    @Override
    public void notify(DelegateTask delegateTask) {
        LOG.debug("[" + delegateTask.getProcessInstanceId() + "-" + delegateTask.getId() + "] " + delegateTask.getAssignee() + " is completing  " + delegateTask.getTaskDefinitionKey() + " (" + delegateTask.getName() + ")  - " + delegateTask.getVariables());
    }
}
