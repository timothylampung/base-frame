package my.spotit.asset.workorder.api.vo;

import my.spotit.asset.core.api.Task;

public class WorkOrderTask extends Task {

    private WorkOrder order;

    public WorkOrder getOrder() {
        return order;
    }

    public void setOrder(WorkOrder order) {
        this.order = order;
    }
}
