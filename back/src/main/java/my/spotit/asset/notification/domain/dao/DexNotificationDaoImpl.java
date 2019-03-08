package my.spotit.asset.notification.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.notification.domain.model.DexNotification;
import my.spotit.asset.notification.domain.model.DexNotificationImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("DexNotificationDao")
public class DexNotificationDaoImpl extends GenericDaoSupport<Long, DexNotification> implements DexNotificationDao {
    public DexNotificationDaoImpl() {
        super(DexNotificationImpl.class);
    }

    @Override
    public List<DexNotification> findNotifications(String recieverEmail) {
        Query query = entityManager.createQuery("SELECT e FROM DexNotification e where e.recieverEmail =:recieverEmail" +
                " and e.metadata.state =:state");
        query.setParameter("recieverEmail", recieverEmail);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexNotification>) query.getResultList();
    }

    @Override
    public Integer count(String recieverEmail) {
        Query query = entityManager.createQuery("SELECT count(e) FROM DexNotification e where e.recieverEmail =:recieverEmail" +
                " and e.metadata.state =:state");
        query.setParameter("recieverEmail", recieverEmail);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

}
