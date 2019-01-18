package com.assettagging.spotit.flowable;

import org.flowable.engine.common.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.common.api.delegate.event.FlowableEntityEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskDebugger implements FlowableEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(TaskDebugger.class);

    @Override
    public void onEvent(FlowableEvent event) {
        FlowableEngineEventType type = (FlowableEngineEventType) event.getType();

        switch (type) {
            case TASK_CREATED:
                if (event instanceof FlowableEntityEvent) {
                    FlowableEntityEvent entityEvent = (FlowableEntityEvent) event;
                    TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
                    String assignee = (null == taskEntity.getAssignee()) ? taskEntity.getCandidates().toString() : taskEntity.getAssignee();
                    LOG.debug("Task Created {} for {}", new String[]{taskEntity.getName(), assignee});
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
