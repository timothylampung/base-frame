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
    public List<DexDevice> findByDevice(String deviceId) {
        Query q = entityManager.createQuery("select e from DexDevice  e where e.deviceId =:deviceId")
                .setParameter("deviceId", deviceId);
        return (List<DexDevice>)q.getResultList();

    }


    @Override
    public List<DexDevice> findDevices(DexUser user) {
        Query q = entityManager.createQuery("select e from DexDevice e where e.user =:user")
                .setParameter("user", user);
        return q.getResultList();
    }

    @Override
    public DexDevice findDevice(String deviceId, DexUser user) {
        Query q = entityManager.createQuery("select e from DexDevice  e where " +
                "e.deviceId =:deviceId " +
                "and e.user=:user")
                .setParameter("user", user)
                .setParameter("deviceId", deviceId);
        return (DexDevice) q.getSingleResult();
    }


}
