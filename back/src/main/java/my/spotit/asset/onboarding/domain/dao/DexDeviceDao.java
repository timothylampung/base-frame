package my.spotit.asset.onboarding.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.domain.model.DexDevice;

import java.util.List;

public interface DexDeviceDao extends GenericDao<Long, DexDevice> {
    DexDevice findByDeviceId(String deviceId);

    List<DexDevice> findDevicesByUser(DexUser user);
}
