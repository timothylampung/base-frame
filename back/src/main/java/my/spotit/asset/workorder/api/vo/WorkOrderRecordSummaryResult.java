package my.spotit.asset.workorder.api.vo;

import java.util.List;

public class WorkOrderRecordSummaryResult {

    private List<WorkOrderRecordSummary> data;
    private Integer page;
    private Integer totalSize;

    public WorkOrderRecordSummaryResult(List<WorkOrderRecordSummary> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<WorkOrderRecordSummary> getData() {
        return data;
    }

    public void setData(List<WorkOrderRecordSummary> data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
