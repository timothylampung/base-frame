package my.spotit.asset.workorder.api.vo;

import java.util.List;

public class WorkOrderTaskSummaryResult {
    private List<WorkOrderTaskSummary> data;
    private Integer page;
    private Integer totalSize;

    public WorkOrderTaskSummaryResult(List<WorkOrderTaskSummary> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<WorkOrderTaskSummary> getData() {
        return data;
    }

    public void setData(List<WorkOrderTaskSummary> data) {
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
