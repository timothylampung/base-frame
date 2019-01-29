package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.asset.domain.dao.DexLocationDao;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexUserImpl;
import com.assettagging.spotit.maintenance.domain.dao.DexMaintenanceRequestDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.security.business.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MaintenanceRequestService")
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    private DexMaintenanceRequestDao maintenanceRequestDao;
    private DexLocationDao locationDao;
    private SecurityService securityService;

    @Autowired
    public MaintenanceServiceImpl(DexMaintenanceRequestDao maintenanceRequestDao, DexLocationDao locationDao, SecurityService securityService) {
        this.maintenanceRequestDao = maintenanceRequestDao;
        this.locationDao = locationDao;
        this.securityService = securityService;
    }

    @Override
    public List<DexMaintenanceRequest> findAllMaintenanceRequest() {
        return maintenanceRequestDao.findAllMaintenanceRequest();
    }

    @Override
    public void submitMaintenanceRequest(DexMaintenanceRequest maintenanceRequest, DexLocation location) {
        DexUserImpl currentUser = (DexUserImpl) securityService.getCurrentUser();
        DexActor actor = currentUser.getActor();
        maintenanceRequestDao.saveMaintenanceRequest(maintenanceRequest, location, actor, currentUser);
    }
}
