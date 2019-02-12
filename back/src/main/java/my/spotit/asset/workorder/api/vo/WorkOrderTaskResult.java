package my.spotit.asset.workorder.api.vo;

import java.util.List;

public class WorkOrderTaskResult {
    private List<WorkOrderTask> data;
    private Integer page;
    private Integer totalSize;

    public WorkOrderTaskResult(List<WorkOrderTask> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<WorkOrderTask> getData() {
        return data;
    }

    public void setData(List<WorkOrderTask> data) {
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
