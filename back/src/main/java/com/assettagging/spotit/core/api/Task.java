package com.assettagging.spotit.core.api;


/**
 * @author canang technologies
 */
public class Task {

    private Long id;
    private String taskId;
    private String taskName;
    private String referenceNo;
    private String sourceNo;
    private String description;
    private String candidate;
    private String assignee;
    private MetaState metaState;
    private FlowState flowState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public MetaState getMetaState() {
        return metaState;
    }

    public void setMetaState(MetaState metaState) {
        this.metaState = metaState;
    }

    public FlowState getFlowState() {
        return flowState;
    }

    public void setFlowState(FlowState flowState) {
        this.flowState = flowState;
    }
}
