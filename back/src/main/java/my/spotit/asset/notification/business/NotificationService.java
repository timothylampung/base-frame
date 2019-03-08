package my.spotit.asset.notification.business;

import my.spotit.asset.notification.domain.model.DexNotification;

import java.util.List;

public interface NotificationService {

    void broadCastNotificatification(DexNotification notification);

    List<DexNotification> findNotificationsByRecieverEmail(String recieverEmail);

    void markNotificationAsRead(DexNotification notification);

    DexNotification findNotificationById(Long id);

    Integer countNotification(String recieverEmail);
}
