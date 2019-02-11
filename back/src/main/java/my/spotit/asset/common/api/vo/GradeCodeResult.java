package my.spotit.asset.common.api.vo;

import java.util.List;

/**
 */
public class GradeCodeResult {
    private List<GradeCode> data;
    private Integer page;
    private Integer totalSize;

    public GradeCodeResult(List<GradeCode> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<GradeCode> getData() {
        return data;
    }

    public void setData(List<GradeCode> data) {
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
