package com.assettagging.spotit.onboarding.bussiness.service;

import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.onboarding.domain.model.DexDevice;

import java.util.List;

public interface DeviceService {
    void registerNewDevice(DexDevice device);

    void unregisterDevice(DexDevice device);

    DexDevice findDeviceByDeviceId(String deviceId);

    List<DexDevice> findDevicesByUserId(DexUser user);

     boolean deviceExist(String deviceId);
}
