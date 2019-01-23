package com.assettagging.spotit.onboarding.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.onboarding.domain.model.DexDevice;

import java.util.List;

public interface DexDeviceDao extends GenericDao<Long, DexDevice> {
    DexDevice findByDeviceId(String deviceId);

    List<DexDevice> findDevicesByUser(DexUser user);
}
