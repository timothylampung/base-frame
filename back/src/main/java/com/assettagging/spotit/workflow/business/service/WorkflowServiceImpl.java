package com.assettagging.spotit.workflow.business.service;

import com.assettagging.spotit.core.domain.DexDocument;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.workflow.business.integration.registry.DocumentHandlerRegistry;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import com.assettagging.spotit.security.business.service.SecurityService;

/**
 * @author canang technologies
 */
@Transactional
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    private static final String WILDCARD = "%";

    protected EntityManager entityManager;
    protected ProcessEngine processEngine;
    protected RuntimeService runtimeService;
    protected TaskService taskService;
    protected IdentityService identityService;
    protected HistoryService historyService;
    protected RepositoryService repositoryService;
    protected SecurityService securityService;
    private DocumentHandlerRegistry registry;

    @Autowired
    public WorkflowServiceImpl(EntityManager entityManager, ProcessEngine processEngine,
                               RuntimeService runtimeService, TaskService taskService,
                               IdentityService identityService, HistoryService historyService,
                               RepositoryService repositoryService, SecurityService securityService,
                               DocumentHandlerRegistry registry) {
        this.entityManager = entityManager;
        this.processEngine = processEngine;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.identityService = identityService;
        this.historyService = historyService;
        this.repositoryService = repositoryService;
        this.securityService = securityService;
        this.registry = registry;
    }

    // ==================================================================================================== //
    // WORKFLOW
    // ==================================================================================================== //

    @PreDestroy
    public void preDestroy() {
        processEngine.close();
    }

    @Override
    public void processWorkflow(DexDocument document, Map<String, Object> variables) {
        registry.process(document, variables);
    }

    /**
     * get variable from process
     */
    public Map<String, Object> getVariables(String executionId) {
        return runtimeService.getVariables(executionId);
    }

    /**
     * find task given task id
     *
     * @param taskId task id
     * @return single result
     */
    public Task findTask(String taskId) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskId(taskId);
        return taskQuery.singleResult();
    }

    /**
     * find task given variable and value
     *
     * @param variable var name
     * @param value    value
     * @return single result
     */
    public Task findTaskByVariable(String variable, Object value) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processVariableValueEquals(variable, value);
        return taskQuery.singleResult();
    }

    /**
     * find tasks per taskname
     */
    public List<Task> findTasks(String taskName) {
        LOG.debug("finding assigned task for user: " + securityService.getCurrentUser());
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskName(taskName);
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.list();
    }

    /**
     * @param task
     * @return
     */
    public List<IdentityLink> getIdentityLinksForTask(Task task) {
        return taskService.getIdentityLinksForTask(task.getId());
    }


    /**
     * find task
     */
    public List<Task> findAssignedTasks(String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding assigned task for user: " + securityService.getCurrentUser().getName());
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.list();
    }

    /**
     * find assigned task
     */
    public List<Task> findAssignedTasks(String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding assigned task for user: " + securityService.getCurrentUser().getName());
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");

        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    @Override
    public List<Task> findAssignedTasks(String filter, String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();
        LOG.debug("finding assigned task for user: " + currentUsername);
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee(currentUsername);
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    public List<Task> findAssignedTasks(String name, String value, String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding assigned task for user: " + securityService.getCurrentUser().getName());
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processVariableValueLike(name, WILDCARD + value + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    /**
     * find pooled task
     */
    public List<Task> findPooledTasks(String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding pooled task for user: " + securityService.getCurrentUser().getName());
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser(securityService.getCurrentUser().getName());
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.list();
    }

    /**
     * find pooled task
     */
    public List<Task> findPooledTasks(String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding pooled task for user: " + securityService.getCurrentUser().getName());
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser("["+"GRP_USR"+", "+currentUsername+"]");
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    @Override
    public List<Task> findPooledTasks(String filter, String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding pooled task for user: " + currentUsername);
        LOG.debug("task prefix: " + taskPrefix);

        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
//        taskQuery.processVariableValueLike("name", WILDCARD + filter + WILDCARD); //TODO pooled by group ? user?
//        taskQuery.taskCandidateUser(currentUsername);
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    public List<Task> findPooledTasks(String name, String value, String taskPrefix, Integer offset, Integer limit) {
        String currentUsername = securityService.getCurrentUser().getName();

        LOG.debug("finding pooled task for user: " + securityService.getCurrentUser().getName());
        LOG.debug("task prefix: " + taskPrefix);
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processVariableValueLike(name, WILDCARD + value + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser(securityService.getCurrentUser().getName());
        taskQuery.orderByTaskCreateTime();
        taskQuery.desc();
        return taskQuery.listPage(offset, limit);
    }

    public Integer countAssignedTask(String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");
        return (int) taskQuery.count();
    }

    @Override
    public Integer countAssignedTask(String filter, String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        TaskQuery taskQuery = taskService.createTaskQuery();
        // TODO: taskQuery.processVariableValueLike(param, WILDCARD + value + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");
        return (int) taskQuery.count();
    }

    public Integer countAssignedTask(String param, String value, String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processVariableValueLike(param, WILDCARD + value + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskAssignee("["+"GRP_USR"+", "+currentUsername+"]");
        return (int) taskQuery.count();
    }

    public Integer countPooledTask(String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();
        
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser(securityService.getCurrentUser().getName());
        return (int) taskQuery.count();
    }

    @Override
    public Integer countPooledTask(String filter, String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();

        TaskQuery taskQuery = taskService.createTaskQuery();
        // TODO: taskQuery.processVariableValueLike(param, WILDCARD + filter + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser(securityService.getCurrentUser().getName());
        return (int) taskQuery.count();
    }

    /**
     * count pooled task
     */
    public Integer countPooledTask(String param, String filter, String taskPrefix) {
        String currentUsername = securityService.getCurrentUser().getName();
        
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processVariableValueLike(param, WILDCARD + filter + WILDCARD);
        taskQuery.taskNameLike(WILDCARD + taskPrefix + WILDCARD);
        taskQuery.taskCandidateUser(securityService.getCurrentUser().getName());
        return (int) taskQuery.count();
    }

    /**
     * assign task to user
     */
    public void claimTask(Task task) {
        Assert.notNull(task, "Task cannot be null");
        LOG.debug("claiming for user: " + securityService.getCurrentUser().getName());
        taskService.claim(task.getId(), securityService.getCurrentUser().getName());
    }

    /**
     * release task to user
     */
    public void releaseTask(Task task) {
        Assert.notNull(task, "Task cannot be null");
        LOG.debug("releasing for user: " + securityService.getCurrentUser().getName());
        taskService.claim(task.getId(), null);
    }

    /**
     * assign task to user
     */
    public void stealTask(Task task) {
        Assert.notNull(task, "Task cannot be null");
        LOG.debug("stealing for user: " + securityService.getCurrentUser().getName());
        taskService.claim(task.getId(), securityService.getCurrentUser().getName());
    }

    /**
     * assign task to user
     */
    public void assignTask(Task task) {
        Assert.notNull(task, "Task cannot be null");
        LOG.debug("assigning for user: " + securityService.getCurrentUser().getName());
        taskService.setAssignee(task.getId(), securityService.getCurrentUser().getName());
    }

    /**
     * assign task to user
     */
    public void assignTask(Task task, String username) {
        Assert.notNull(task, "Task cannot be null");
        taskService.setAssignee(task.getId(), username);
    }

    /**
     * complete this task
     */
    public void completeTask(Task task) {
        Assert.notNull(task, "Task cannot be null");
        taskService.complete(task.getId());
    }

    /**
     * complete this task
     */
    public void completeTask(Task task, Map<String, Object> variables) {
        Assert.notNull(task, "Task cannot be null");
        taskService.complete(task.getId(), variables);
    }
}
