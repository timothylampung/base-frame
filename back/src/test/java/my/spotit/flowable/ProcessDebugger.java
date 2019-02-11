package my.spotit.flowable;

import org.flowable.engine.common.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.common.api.delegate.event.FlowableEntityEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.event.impl.FlowableProcessCancelledEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDebugger implements FlowableEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessDebugger.class);

    @Override
    public void onEvent(FlowableEvent event) {

        FlowableEngineEventType type = (FlowableEngineEventType) event.getType();

        switch (type) {
            case PROCESS_CREATED:
                if (event instanceof FlowableEntityEvent) {
                    FlowableEntityEvent startedProcess = (FlowableEntityEvent) event;

                    ExecutionEntity executionEntity = (ExecutionEntity) startedProcess.getEntity();
                    LOG.debug(executionEntity.getProcessDefinitionId() + ":" + executionEntity.getProcessInstanceId() + " started");
                }
                break;
            case PROCESS_COMPLETED:
                if (event instanceof FlowableEntityEvent) {
                    FlowableEntityEvent entityEvent = (FlowableEntityEvent) event;

                    ExecutionEntity executionEntity = (ExecutionEntity) entityEvent.getEntity();
                    LOG.debug(executionEntity.getProcessDefinitionId() + ":" + executionEntity.getProcessInstanceId() + " completed");
                }
                break;
            case PROCESS_CANCELLED:
                if (event instanceof FlowableProcessCancelledEventImpl) {
                    FlowableProcessCancelledEventImpl entityEvent = (FlowableProcessCancelledEventImpl) event;

                    LOG.debug("{}:{} cancelled with reason {}", new Object[]{entityEvent.getProcessDefinitionId(), entityEvent.getProcessInstanceId(), entityEvent.getCause()});
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

}

