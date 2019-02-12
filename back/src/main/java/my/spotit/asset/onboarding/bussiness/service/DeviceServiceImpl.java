package my.spotit.asset.onboarding.bussiness.service;

import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.onboarding.domain.dao.DexDeviceDao;
import my.spotit.asset.onboarding.domain.model.DexDevice;
import my.spotit.asset.security.business.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {


    private EntityManager entityManager;
    private DexDeviceDao deviceDao;
    private SecurityService securityService;

    @Autowired
    public DeviceServiceImpl(EntityManager entityManager, DexDeviceDao deviceDao, SecurityService securityService) {
        this.entityManager = entityManager;
        this.deviceDao = deviceDao;
        this.securityService = securityService;
    }


    @Override
    public void registerNewDevice(DexDevice device) {
        this.deviceDao.save(device, securityService.getCurrentUser());
        entityManager.flush();
    }


    @Override
    public void unregisterDevice(DexDevice device) {
        this.deviceDao.remove(device, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public boolean deviceExist(String deviceId, DexUser user) {
        return findDevices(deviceId, user)!=null;
    }


    @Override
    public List<DexDevice> findDevices(String deviceId) {
        List<DexDevice> device = null;
        try {
            device = deviceDao.findByDevice(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }


    @Override
    public List<DexDevice> findDevicesByUser(DexUser user) {
        return deviceDao.findDevices(user);
    }

    @Override
    public DexDevice findDevices(String deviceId, DexUser user) {
        DexDevice device = null;
        try {
            device = deviceDao.findDevice(deviceId, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }
}

