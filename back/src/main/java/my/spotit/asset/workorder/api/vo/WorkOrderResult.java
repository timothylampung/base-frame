package my.spotit.asset.workorder.api.vo;

import java.util.List;

public class WorkOrderResult {

    private List<WorkOrder> data;
    private Integer page;
    private Integer totalSize;

    public WorkOrderResult(List<WorkOrder> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<WorkOrder> getData() { return data; }

    public void setData(List<WorkOrder> data) {
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
