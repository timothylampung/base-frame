package my.spotit.asset.workorder.business.workflow.task;

import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workorder.business.service.WorkOrderService;

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
