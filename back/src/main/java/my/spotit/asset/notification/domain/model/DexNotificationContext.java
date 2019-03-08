package my.spotit.asset.notification.domain.model;

public enum DexNotificationContext {
    WORK_ORDER,
    MAINTENANCE_REQUEST;

    public static DexNotificationContext get(int index) {
        return values()[index];
    }

    }
