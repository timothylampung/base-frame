package my.spotit.asset.identity.api.vo;

import java.util.List;

public class TechnicianResult {
    private List<Technician> data;
    private Integer page;
    private Integer totalSize;

    public TechnicianResult(List<Technician> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Technician> getData() {
        return data;
    }

    public void setData(List<Technician> data) {
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
