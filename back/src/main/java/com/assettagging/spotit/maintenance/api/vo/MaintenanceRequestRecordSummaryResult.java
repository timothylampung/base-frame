package com.assettagging.spotit.maintenance.api.vo;

import java.util.List;

public class MaintenanceRequestRecordSummaryResult {

    private List<MaintenanceRequestRecordSummary> data;
    private Integer page;
    private Integer totalSize;

    public MaintenanceRequestRecordSummaryResult(List<MaintenanceRequestRecordSummary> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<MaintenanceRequestRecordSummary> getData() {
        return data;
    }

    public void setData(List<MaintenanceRequestRecordSummary> data) {
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
