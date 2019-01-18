package com.assettagging.spotit.workflow.business.service;


import com.assettagging.spotit.core.domain.DexDocument;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Map;

/**
 * @author canang technologies
 */
public interface WorkflowService {

    void processWorkflow(DexDocument document, Map<String, Object> variables);

    Map<String, Object> getVariables(String executionId);

    Task findTask(String taskId);

    Task findTaskByVariable(String variable, Object value);

    List<Task> findTasks(String taskName);

    List<IdentityLink> getIdentityLinksForTask(Task task);

    List<Task> findAssignedTasks(String taskPrefix, Integer offset, Integer limit);

    List<Task> findAssignedTasks(String filter, String taskPrefix, Integer offset, Integer limit);

    List<Task> findAssignedTasks(String name, String value, String taskPrefix, Integer offset, Integer limit);

    List<Task> findPooledTasks(String taskPrefix, Integer offset, Integer limit);

    List<Task> findPooledTasks(String filter, String taskPrefix, Integer offset, Integer limit);

    List<Task> findPooledTasks(String name, String value, String taskPrefix, Integer offset, Integer limit);

    Integer countAssignedTask(String taskPrefix);

    Integer countAssignedTask(String filter, String taskPrefix);

    Integer countAssignedTask(String param, String value, String taskPrefix);

    Integer countPooledTask(String taskPrefix);

    Integer countPooledTask(String filter, String taskPrefix);

    Integer countPooledTask(String param, String value, String taskPrefix);

    void assignTask(Task task);

    void assignTask(Task task, String username);

    void claimTask(Task task);

    void releaseTask(Task task);

    void stealTask(Task task);

    void completeTask(Task task);

    void completeTask(Task task, Map<String, Object> variables);

}
