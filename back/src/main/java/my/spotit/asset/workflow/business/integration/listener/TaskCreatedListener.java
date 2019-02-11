package my.spotit.asset.workflow.business.integration.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class TaskCreatedListener implements TaskListener {

    private static final Logger LOG = LoggerFactory.getLogger(TaskCreatedListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        String currentState = delegateTask.getTaskDefinitionKey();
        LOG.debug("[" + delegateTask.getProcessInstanceId() + "-" + delegateTask.getId() + "] Task Created: " + currentState + "(" + delegateTask.getName() + ")");

    }


}
