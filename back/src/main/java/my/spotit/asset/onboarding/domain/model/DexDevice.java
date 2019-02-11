package my.spotit.asset.onboarding.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;
import my.spotit.asset.identity.domain.model.DexUser;

public interface DexDevice extends DexMetaObject {

    String getDeviceId();

    void setDeviceId(String deviceId);

    DeviceType getDeviceType();

    void setDeviceType(DeviceType deviceType);

    DexUser getUser();

    void setUser(DexUser user);

    DeviceStatus getDeviceStatus();

    void setDeviceStatus(DeviceStatus deviceStatus);

    String getDeviceName();

    void setDeviceName(String deviceName);
}
