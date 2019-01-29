package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.common.business.service.CommonServiceImpl;
import com.assettagging.spotit.maintenance.domain.dao.DexMaintenanceRequestDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.security.business.service.SecurityService;
import com.assettagging.spotit.workorder.domain.dao.DexActivityDao;
import com.assettagging.spotit.workorder.domain.dao.DexWorkOrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@Service("maintenanceOrderService")
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexMaintenanceRequestDao maintenanceRequestDao;

    @Autowired
    public MaintenanceRequestServiceImpl(EntityManager entityManager, SecurityService securityService,
                                DexMaintenanceRequestDao maintenanceRequestDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.maintenanceRequestDao = maintenanceRequestDao;

    }


    //MaintenanceRequest



    @Override
    public DexMaintenanceRequest findMaintenanceRequestById(Long id) {
        return maintenanceRequestDao.findById(id);
    }

    @Override
    public DexMaintenanceRequest findMaintenanceRequestByCode(String code) {
        return maintenanceRequestDao.findByCode(code);
    }

    @Override
    public List<DexMaintenanceRequest> findMaintenanceRequests(String filter, Integer offset, Integer limit) {
        return maintenanceRequestDao.find(filter, offset, limit);
    }

    @Override
    public Integer countMaintenanceRequest() {
        return maintenanceRequestDao.count();
    }

    @Override
    public Integer countMaintenanceRequest(String filter) {
        return maintenanceRequestDao.count(filter);
    }

    @Override
    public void saveMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest) {

        maintenanceRequestDao.save(MaintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();

    }

    @Override
    public void updateMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest) {
        maintenanceRequestDao.update(MaintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();


    }

    @Override
    public void removeMaintenanceRequest(DexMaintenanceRequest MaintenanceRequest) {

        maintenanceRequestDao.remove(MaintenanceRequest, securityService.getCurrentUser());
        entityManager.flush();


    }
}
