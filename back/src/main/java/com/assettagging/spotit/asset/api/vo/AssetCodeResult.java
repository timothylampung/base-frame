package com.assettagging.spotit.asset.api.vo;

import java.util.List;

public class AssetCodeResult {
    private List<AssetCode> data;
    private Integer page;
    private Integer totalSize;

    public AssetCodeResult(List<AssetCode> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<AssetCode> getData() { return data; }

    public void setData(List<AssetCode> data) { this.data = data; }

    public Integer getPage() { return page; }

    public void setPage(Integer page) { this.page = page; }

    public Integer getTotalSize() { return totalSize; }

    public void setTotalSize(Integer totalSize) { this.totalSize = totalSize; }
}
