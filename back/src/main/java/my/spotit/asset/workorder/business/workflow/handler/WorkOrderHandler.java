package my.spotit.asset.workorder.business.workflow.handler;


import my.spotit.asset.workflow.business.integration.registry.DocumentHandler;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.PostConstruct;


/**
 * @author canang technologies
 */
@Component("workOrderHandler")
public class WorkOrderHandler implements DocumentHandler<DexWorkOrder> {

    public static final String WORK_ORDER_PROCESS_KEY = "work_order_workflow";
    public static final String WORK_ORDER_RESOURCE_PATH = "work-order.bpmn20.xml";
    public static final String WORK_ORDER_PROCESS_NAME = "orderInvoice";
    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderHandler.class);
    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;

    @Override
    public String process(DexWorkOrder orderInvoice, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(WORK_ORDER_PROCESS_KEY, variables);
        LOG.info("Process started for {} with process instance #{} ", WORK_ORDER_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployWorkflow() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(WORK_ORDER_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(WORK_ORDER_RESOURCE_PATH)
                    .name(WORK_ORDER_PROCESS_NAME)
                    .deploy();
        }
    }

}
