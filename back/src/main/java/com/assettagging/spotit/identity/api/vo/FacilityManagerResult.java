package com.assettagging.spotit.identity.api.vo;

import java.util.List;

public class FacilityManagerResult {
    private List<FacilityManager> data;
    private Integer page;
    private Integer totalSize;

    public FacilityManagerResult(List<FacilityManager> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<FacilityManager> getData() {
        return data;
    }

    public void setData(List<FacilityManager> data) {
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
