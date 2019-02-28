package my.spotit.asset.workorder.api.vo;

import my.spotit.asset.core.api.Task;

public class WorkOrderTask extends Task {

    private WorkOrder workOrder;

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }
}
