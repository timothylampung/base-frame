package my.spotit.asset.workorder.api.vo;


import my.spotit.asset.core.api.Task;

public class WorkOrderTaskSummary extends Task {

    private WorkOrder workOrder;
    private String assignee;
    private String reporter;
    private String verifier;

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }
}
