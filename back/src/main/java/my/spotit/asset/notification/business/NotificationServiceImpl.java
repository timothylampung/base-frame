package my.spotit.asset.notification.business;

import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.notification.domain.dao.DexNotificationDao;
import my.spotit.asset.notification.domain.model.DexNotification;
import my.spotit.asset.security.business.service.SecurityService;
import my.spotit.asset.system.business.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service("NotificationServiceImpl")
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private SecurityService securityService;
    private SystemService systemService;
    private DexNotificationDao notificationDao;
    private IdentityService identityService;
    private EntityManager entityManager;

    @Autowired
    public NotificationServiceImpl(SecurityService securityService, SystemService systemService, DexNotificationDao notificationDao, IdentityService identityService, EntityManager entityManager) {
        this.securityService = securityService;
        this.systemService = systemService;
        this.notificationDao = notificationDao;
        this.identityService = identityService;
        this.entityManager = entityManager;
    }


    @Override
    public void broadCastNotificatification(DexNotification notification) {
        notificationDao.save(notification, getCurrentUser());
        entityManager.flush();
    }


    @Override
    public List<DexNotification> findNotificationsByRecieverEmail(String recieverEmail) {
        return notificationDao.findNotifications(recieverEmail);
    }

    @Override
    public void markNotificationAsRead(DexNotification notification) {
        notificationDao.remove(notification, getCurrentUser());
    }

    @Override
    public DexNotification findNotificationById(Long id) {
        return notificationDao.findById(id);
    }

    @Override
    public Integer countNotification(String recieverEmail) {
        return notificationDao.count(recieverEmail);
    }


    private DexUser getCurrentUser() {
        return securityService.getCurrentUser();
    }
}
