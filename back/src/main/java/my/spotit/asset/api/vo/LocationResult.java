package my.spotit.asset.api.vo;

import java.util.List;

public class LocationResult {
    private List<Location> data;
    private Integer page;
    private Integer totalSize;

    public LocationResult(List<Location> data, Integer totalSize) {
        this.data = data;
        this.totalSize = totalSize;
    }

    public List<Location> getData() { return data; }

    public void setData(List<Location> data) { this.data = data; }

    public Integer getPage() { return page; }

    public void setPage(Integer page) { this.page = page; }

    public Integer getTotalSize() { return totalSize; }

    public void setTotalSize(Integer totalSize) { this.totalSize = totalSize; }
}
