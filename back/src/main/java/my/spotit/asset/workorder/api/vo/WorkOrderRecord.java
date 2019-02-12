package my.spotit.asset.workorder.api.vo;

import my.spotit.asset.core.api.Record;

public class WorkOrderRecord extends Record {

    private String referenceNo;
    private String sourceNo;
    private String description;
    private WorkOrder order;

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

    public WorkOrder getOrder() {
        return order;
    }

    public void setOrder(WorkOrder order) {
        this.order = order;
    }
}
