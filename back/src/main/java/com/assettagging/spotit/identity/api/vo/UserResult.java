package com.assettagging.spotit.identity.api.vo;

import java.util.List;

/**
 */
public class UserResult {
    private List<User> data;
    private Integer page;
    private Integer totalSize;

    public UserResult(List<User> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
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
