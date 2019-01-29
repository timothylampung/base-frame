package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.maintenance.domain.dao.DexMaintenanceRequestDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;
import com.assettagging.spotit.workorder.business.service.WorkOrderServiceImplTest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@Transactional
public class MaintenanceRequestServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;


    @Test
    public void findMaintenanceRequestById() {

        long id =2;
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById(id);

        LOG.debug("TEST: " +  maintenanceRequest.getDescription());

    }

    @Test
    public void findMaintenanceRequestByCode() {
    }

    @Test
    public void findMaintenanceRequests() {

        List<DexMaintenanceRequest> maintenanceRequests = maintenanceRequestService.findMaintenanceRequests("%",0,999);
        for (DexMaintenanceRequest maintenanceRequest : maintenanceRequests) {
            LOG.debug("TEST: " + maintenanceRequest.getDescription());
        }

    }

    @Test
    public void countMaintenanceRequest() {
    }

    @Test
    public void countMaintenanceRequest1() {
    }

    @Test
    public void saveMaintenanceRequest() {
    }

    @Test
    public void updateMaintenanceRequest() {
    }

    @Test
    public void removeMaintenanceRequest() {
    }
}