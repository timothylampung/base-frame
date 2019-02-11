package my.spotit.asset.onboarding.bussiness.service;

import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.domain.model.DexDevice;

import java.util.List;

public interface DeviceService {
    void registerNewDevice(DexDevice device);

    void unregisterDevice(DexDevice device);

    DexDevice findDeviceByDeviceId(String deviceId);

    List<DexDevice> findDevicesByUserId(DexUser user);

     boolean deviceExist(String deviceId);
}
