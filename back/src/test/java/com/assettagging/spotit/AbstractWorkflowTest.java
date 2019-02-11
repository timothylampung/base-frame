package com.assettagging.spotit;

import my.spotit.workflow.business.integration.registry.DocumentHandlerRegistry;
import com.assettagging.spotit.flowable.ProcessDebugger;
import com.assettagging.spotit.flowable.SequenceFlowDebugger;
import com.assettagging.spotit.flowable.TaskDebugger;
import my.spotit.security.business.integration.DexAutoLoginToken;
import my.spotit.workflow.business.service.WorkflowService;
import my.spotit.workflow.business.service.WorkflowServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorkflowTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractWorkflowTest.class);
    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            super.starting(description);
            LOG.debug("Starting {}.{}", description.getClassName(), description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            super.finished(description);
            LOG.debug("Finished {}.{}", description.getClassName(), description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            LOG.error("Error {}.{} {}", new Object[]{description.getClassName(), description.getMethodName(), e.getMessage()});
            e.printStackTrace();
        }
    };
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected WorkflowService workflowService;
    protected boolean redeploy = false;
    @Autowired
    AuthenticationManager authenticationManager;
    private SequenceFlowDebugger sequenceFlowDebugger = new SequenceFlowDebugger();
    private ProcessDebugger processDebugger = new ProcessDebugger();
    private TaskDebugger taskDebugger = new TaskDebugger();

    protected void redeployProcessDefinition() {
        if (redeploy) {
            deleteDeployment();
            deployProcessDefinition();
        }
    }

    protected void deleteDeployment() {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> list = query.processDefinitionKey(getProcessKey()).list();
        long count = list.size();
        if (count > 0) {
            for (ProcessDefinition processDefinition : list) {

                ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
                List<ProcessInstance> instances = processInstanceQuery.processDefinitionId(processDefinition.getId()).list();
                for (ProcessInstance instance : instances) {
                    runtimeService.deleteProcessInstance(instance.getProcessInstanceId(), "");
                }

                LOG.debug("Undelploy {}", processDefinition.getId());
                repositoryService.deleteDeployment(processDefinition.getDeploymentId());
            }
        }
    }

    protected void deployProcessDefinition() {
        repositoryService.createDeployment()
                .addClasspathResource(getProcessResourcePath())
                .name(getProcessName())
                .deploy();
    }

    protected void disableCommonServicesLogger() {
        changeLogLevelToError(WorkflowServiceImpl.class.getPackage().getName());
        changeLogLevelToError(DocumentHandlerRegistry.class.getName());
    }

    protected void addWorkflowLogger() {
        //workflow
        runtimeService.addEventListener(sequenceFlowDebugger);
        runtimeService.addEventListener(processDebugger);
        runtimeService.addEventListener(taskDebugger);
    }

    protected void auth() {
        DexAutoLoginToken token = new DexAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

    }

    protected void changeLogLevelToError(String loggerName) {
        LogManager.getLoggerRepository().getLogger(loggerName).setLevel(Level.ERROR);
    }

    //////////// workflow

    protected void doClaimAndComplete(String referenceNo) {
        doClaimTask(referenceNo, new HashMap<>());
        doCompleteTask(referenceNo, new HashMap<>());
    }

    protected void doClaimAndComplete(String referenceNo, Map<String, Object> variables) {
        doClaimTask(referenceNo, variables);
        doCompleteTask(referenceNo, variables);
    }

    protected void doClaimTask(String referenceNo, Map<String, Object> var) {
        doClaimTask(referenceNo, getTaskPrefix(), var);
    }

    protected void doClaimTask(String referenceNo, String taskPrefix, Map<String, Object> var) {
        List<Task> pooledTasks = workflowService.findPooledTasks("referenceNo", referenceNo, taskPrefix, 0, 1);
        Assert.assertEquals(1, pooledTasks.size());

        Task task = pooledTasks.get(0);
        workflowService.claimTask(task);
    }

    protected void doCompleteTask(String referenceNo) {
        doCompleteTask(referenceNo, new HashMap<>());
    }

    protected void doCompleteTask(String referenceNo, Map<String, Object> var) {
        doCompleteTask(referenceNo, getTaskPrefix(), var);
    }

    protected void doCompleteTask(String referenceNo, String taskPrefix, Map<String, Object> var) {

        List<Task> assignedTasks = workflowService.findAssignedTasks("referenceNo", referenceNo, taskPrefix, 0, 1);
        Assert.assertEquals(1, assignedTasks.size());

        Task task = assignedTasks.get(0);
        workflowService.completeTask(task, var);
    }

    //////////////

    protected abstract String getProcessKey();

    protected abstract String getProcessName();

    protected abstract String getProcessResourcePath();

    public abstract String getTaskPrefix();

}
