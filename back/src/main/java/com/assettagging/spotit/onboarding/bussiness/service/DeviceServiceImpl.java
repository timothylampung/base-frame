package com.assettagging.spotit.onboarding.bussiness.service;

import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.onboarding.domain.dao.DexDeviceDao;
import com.assettagging.spotit.onboarding.domain.model.DexDevice;
import com.assettagging.spotit.security.business.service.SecurityService;
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
    public boolean deviceExist(String deviceId) {
        return findDeviceByDeviceId(deviceId)!=null;
    }


    @Override
    public DexDevice findDeviceByDeviceId(String deviceId) {
        DexDevice device = null;
        try {
            device = deviceDao.findByDeviceId(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }


    @Override
    public List<DexDevice> findDevicesByUserId(DexUser user) {
        return deviceDao.findDevicesByUser(user);
    }
}

