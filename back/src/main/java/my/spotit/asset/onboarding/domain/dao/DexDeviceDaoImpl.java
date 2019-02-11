package my.spotit.asset.onboarding.domain.dao;


import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.domain.model.DexDevice;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings({"unchecked"})
@Repository("DeviceDao")
public class DexDeviceDaoImpl extends GenericDaoSupport<Long, DexDevice> implements DexDeviceDao {

    public DexDeviceDaoImpl() {
        super(DexDeviceDaoImpl.class);
    }

    @Override
    public DexDevice findByDeviceId(String deviceId) {
        Query q = entityManager.createQuery("select e from DexDevice  e where e.deviceId =:deviceId")
                .setParameter("deviceId", deviceId);
        return (DexDevice) q.getSingleResult();
    }


    @Override
    public List<DexDevice> findDevicesByUser(DexUser user) {
        Query q = entityManager.createQuery("select e from DexDevice e where e.user =:user")
                .setParameter("user", user);
        return q.getResultList();
    }



}
