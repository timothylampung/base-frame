package my.spotit.asset.workorder.api.vo;

import my.spotit.asset.core.api.Record;

/**
 * @author canang technologies
 */
public class WorkOrderRecordSummary extends Record {

    private String referenceNo;
    private String sourceNo;
    private String description;
    private WorkOrder workOrder;

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
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
}
