package my.spotit.asset.onboarding.domain.model;

import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;

import javax.persistence.*;

@Table(name = "DEX_DVCE")
@Entity(name = "DexDevice")
public class DexDeviceImpl implements DexDevice {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_DVCE")
    @SequenceGenerator(name = "SQ_DEX_DVCE", sequenceName = "SQ_DEX_DVCE", allocationSize = 1)
    private Long id;


    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "DEVICE_TYPE")
    private DeviceType deviceType;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "DEVICE_STATUS")
    private DeviceStatus deviceStatus;

    @ManyToOne(targetEntity = DexUserImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private DexUser user;

    @Embedded
    private DexMetadata trgMetadata;


    public DexDeviceImpl() {
    }


    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public DexUser getUser() {
        return user;
    }

    @Override
    public void setUser(DexUser user) {
        this.user = user;
    }

    @Override
    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    @Override
    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public DexMetadata getMetadata() {
        return trgMetadata;
    }

    @Override

    public void setMetadata(DexMetadata metadata) {
        trgMetadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexDevice.class;
    }
}
