package com.assettagging.spotit.workorder.business.workflow.task;

import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.system.business.service.SystemService;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.flowable.engine.impl.delegate.ActivityBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


/**
 * @author canang technologies
 */
public abstract class WorkOrderTaskSupport extends BpmnActivityBehavior implements ActivityBehavior {

    @Autowired
    protected WorkOrderService workOrderService;

    @Autowired
    protected SystemService systemService;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected ApplicationContext applicationContext;

    public abstract void execute(DelegateExecution execution);

}
