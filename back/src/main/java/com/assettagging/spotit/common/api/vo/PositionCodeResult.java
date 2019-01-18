package com.assettagging.spotit.common.api.vo;

import java.util.List;

/**
 */
public class PositionCodeResult {
    private List<PositionCode> data;
    private Integer page;
    private Integer totalSize;

    public PositionCodeResult(List<PositionCode> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<PositionCode> getData() {
        return data;
    }

    public void setData(List<PositionCode> data) {
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
