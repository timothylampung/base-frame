package com.assettagging.spotit.maintenance.business.workflow.handler;


import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workflow.business.integration.registry.DocumentHandler;
import org.flowable.engine.*;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;


/**
 * @author canang technologies
 */
@Component("maintenanceRequestHandler")
public class MaintenanceRequestHandler implements DocumentHandler<DexMaintenanceRequest> {

    public static final String MAINTENANCE_REQUEST_PROCESS_KEY = "maintenance_request_workflow";
    public static final String MAINTENANCE_REQUEST_RESOURCE_PATH = "maintenance-request.bpmn20.xml";
    public static final String MAINTENANCE_REQUEST_PROCESS_NAME = "maintenanceRequest";
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestHandler.class);
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
    public String process(DexMaintenanceRequest orderInvoice, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(MAINTENANCE_REQUEST_PROCESS_KEY, variables);
        LOG.info("Process started for {} with process instance #{} ", MAINTENANCE_REQUEST_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployWorkflow() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(MAINTENANCE_REQUEST_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(MAINTENANCE_REQUEST_RESOURCE_PATH)
                    .name(MAINTENANCE_REQUEST_PROCESS_NAME)
                    .deploy();
        }
    }

}
