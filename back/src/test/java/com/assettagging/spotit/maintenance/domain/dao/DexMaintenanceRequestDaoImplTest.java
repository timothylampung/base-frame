package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DexMaintenanceRequestDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexMaintenanceRequestDaoImplTest.class);

    @Autowired
    private IdentityService identityService;
    @Autowired
    private DexMaintenanceRequestDao DexMaintenanceRequestDao;

    @Test
    @Rollback(false)
    public void findDexMaintenanceRequestTest() {
        List<DexMaintenanceRequest> maintenanceRequests = DexMaintenanceRequestDao.findAllMaintenanceRequest();
        for (DexMaintenanceRequest maintenanceRequest : maintenanceRequests) {
            LOG.debug("TEST: " + maintenanceRequest.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexMaintenanceRequestByIdTest() {
        long id =2;
        DexMaintenanceRequest maintenanceRequests = DexMaintenanceRequestDao.findById(id);

            LOG.debug("TEST: " + ((DexMaintenanceRequest) maintenanceRequests).getDescription());

    }

}
