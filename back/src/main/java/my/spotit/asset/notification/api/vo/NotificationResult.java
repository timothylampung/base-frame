package my.spotit.asset.notification.api.vo;

import my.spotit.asset.core.api.Document;
import my.spotit.asset.core.api.MetaObject;

import java.util.List;

public class NotificationResult extends MetaObject {
    private Integer count;
    private List<Notification> data;

    public NotificationResult() {
    }

    public NotificationResult(Integer count, List<Notification> data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Notification> getData() {
        return data;
    }

    public void setData(List<Notification> data) {
        this.data = data;
    }
}
