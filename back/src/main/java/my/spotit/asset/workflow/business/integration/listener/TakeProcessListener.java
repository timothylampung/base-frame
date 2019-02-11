package my.spotit.asset.workflow.business.integration.listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class TakeProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(TakeProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) {

        LOG.debug("[" + execution.getProcessInstanceId() + "]"
                + " Process take:"
                + execution.getVariables());


    }
}
