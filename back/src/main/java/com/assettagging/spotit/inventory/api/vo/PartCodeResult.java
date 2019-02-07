package com.assettagging.spotit.inventory.api.vo;

import java.util.List;

public class PartCodeResult {
    private List<PartCode> data;
    private Integer page;
    private Integer totalSize;

    public PartCodeResult(List<PartCode> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<PartCode> getData() { return data; }

    public void setData(List<PartCode> data) { this.data = data; }

    public Integer getPage() { return page; }

    public void setPage(Integer page) { this.page = page; }

    public Integer getTotalSize() { return totalSize; }

    public void setTotalSize(Integer totalSize) { this.totalSize = totalSize; }
}
