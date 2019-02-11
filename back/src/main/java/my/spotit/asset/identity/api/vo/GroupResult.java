package my.spotit.asset.identity.api.vo;

import java.util.List;

/**
 */
public class GroupResult {
    private List<Group> data;
    private Integer page;
    private Integer totalSize;

    public GroupResult(List<Group> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Group> getData() {
        return data;
    }

    public void setData(List<Group> data) {
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
