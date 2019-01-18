package com.assettagging.spotit.flowable;

import org.flowable.engine.ProcessEngines;
import org.flowable.engine.common.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.common.api.delegate.event.FlowableEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.event.FlowableSequenceFlowTakenEvent;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequenceFlowDebugger implements FlowableEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(SequenceFlowDebugger.class);

    @Override
    public void onEvent(FlowableEvent event) {
        FlowableEngineEventType type = (FlowableEngineEventType) event.getType();

        switch (type) {
            case SEQUENCEFLOW_TAKEN:
                if (event instanceof FlowableSequenceFlowTakenEvent) {

                    FlowableSequenceFlowTakenEvent takenSequence = (FlowableSequenceFlowTakenEvent) event;
                    ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService().createProcessInstanceQuery().processDefinitionId(takenSequence.getProcessInstanceId()).singleResult();
                    LOG.debug("{} : from {} to {}", new String[]{takenSequence.getId(), takenSequence.getSourceActivityId(), takenSequence.getTargetActivityId()});
//                    LOG.debug("variables {} ", processInstance.getProcessVariables());
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
