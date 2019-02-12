package my.spotit.asset.onboarding.bussiness.service;

import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.domain.model.DexDevice;

import java.util.List;

public interface DeviceService {
    void registerNewDevice(DexDevice device);

    void unregisterDevice(DexDevice device);

    List<DexDevice> findDevices(String deviceId);

    List<DexDevice> findDevicesByUser(DexUser user);

    DexDevice findDevices(String deviceId, DexUser user);

     boolean deviceExist(String deviceId, DexUser user);
}
