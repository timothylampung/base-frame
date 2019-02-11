package my.spotit.asset.identity.api.vo;

import java.util.List;

/**
 */
public class StaffResult {
    private List<Staff> data;
    private Integer page;
    private Integer totalSize;

    public StaffResult(List<Staff> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Staff> getData() {
        return data;
    }

    public void setData(List<Staff> data) {
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
