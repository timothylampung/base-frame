package com.assettagging.spotit.maintenance.api.vo;

import com.assettagging.spotit.common.api.vo.PositionCode;

import java.util.List;

public class MaintenanceRequestResult {
    private List<MaintenanceRequest> data;
    private Integer page;
    private Integer totalSize;

    public MaintenanceRequestResult(List<MaintenanceRequest> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<MaintenanceRequest> getData() {
        return data;
    }

    public void setData(List<MaintenanceRequest> data) {
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
