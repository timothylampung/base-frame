package com.assettagging.spotit.maintenance.api.vo;

import java.util.List;

public class MaintenanceRequestTaskSummaryResult {
    private List<MaintenanceRequestTaskSummary> data;
    private Integer page;
    private Integer totalSize;

    public MaintenanceRequestTaskSummaryResult(List<MaintenanceRequestTaskSummary> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<MaintenanceRequestTaskSummary> getData() {
        return data;
    }

    public void setData(List<MaintenanceRequestTaskSummary> data) {
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
