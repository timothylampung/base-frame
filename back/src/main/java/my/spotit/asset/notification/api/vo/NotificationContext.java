package my.spotit.asset.notification.api.vo;

public enum NotificationContext {
    WORK_ORDER,
    MAINTENANCE_REQUEST;

    public static NotificationContext get(int index) {
        return values()[index];
    }

    }
