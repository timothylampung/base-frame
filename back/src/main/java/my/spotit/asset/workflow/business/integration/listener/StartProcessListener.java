package my.spotit.asset.workflow.business.integration.listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class StartProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(StartProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) {
        ExecutionEntity executionEntity = (ExecutionEntity) execution;
        LOG.debug("[" + executionEntity.getProcessDefinitionId() + "-" + execution.getProcessInstanceId() + "] Process start " + executionEntity.getProcessDefinitionId());
    }

}
