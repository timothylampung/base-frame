package com.assettagging.spotit.identity.api.vo;

import java.util.List;

public class SupervisorResult {
    private List<Supervisor> data;
    private Integer page;
    private Integer totalSize;

    public SupervisorResult(List<Supervisor> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Supervisor> getData() {
        return data;
    }

    public void setData(List<Supervisor> data) {
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
