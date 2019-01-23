package com.assettagging.spotit.onboarding.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.identity.domain.model.DexUser;

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
