package my.spotit.asset.notification.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.notification.domain.model.DexNotification;

import java.util.List;

public interface DexNotificationDao extends GenericDao<Long, DexNotification> {
    List<DexNotification> findNotifications(String recieverEmail);

    Integer count(String recieverEmail);
}
