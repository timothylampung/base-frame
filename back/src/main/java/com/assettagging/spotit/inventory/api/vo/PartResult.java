package com.assettagging.spotit.inventory.api.vo;

import java.util.List;

public class PartResult {
    private List<Part> data;
    private Integer page;
    private Integer totalSize;

    public PartResult(List<Part> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Part> getData() { return data; }

    public void setData(List<Part> data) { this.data = data; }

    public Integer getPage() { return page; }

    public void setPage(Integer page) { this.page = page; }

    public Integer getTotalSize() { return totalSize; }

    public void setTotalSize(Integer totalSize) { this.totalSize = totalSize; }
}
