package com.assettagging.spotit.maintenance.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.dao.DexAssetDao;
import com.assettagging.spotit.asset.domain.dao.DexLocationDao;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.dao.DexStaffDao;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexStaff;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public class DexMaintenanceRequestDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexMaintenanceRequestDaoImplTest.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private DexUserDao userDao;

    @Autowired
    private DexStaffDao staffDao;

    @Autowired
    private DexAssetDao assetDao;

    @Autowired
    private DexLocationDao locationDao;

    @Autowired
    private DexMaintenanceRequestDao maintenanceRequestDao;

    @Test
    @Rollback(false)
    public void findDexMaintenanceRequestTest() {
        List<DexMaintenanceRequest> maintenanceRequests = maintenanceRequestDao.find();
        for (DexMaintenanceRequest maintenanceRequest : maintenanceRequests) {
            LOG.debug("TEST: " + maintenanceRequest.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexMaintenanceRequestByIdTest() {
        long id = 2;
        DexMaintenanceRequest request = maintenanceRequestDao.findById(id);
        LOG.debug("TEST: " + request.getDescription());
    }


    @Test
    @Rollback(false)
    public void findMaintenanceRequestByAsset() {
        DexUser user = userDao.findByUsername("root");
        DexStaff st000X = staffDao.findByCode("ST000X");
        DexAsset asset = assetDao.findByCode("AST001");
        DexLocation lctn001 = locationDao.findByCode("LCTN001");

        String referenceNo = "RQST" + System.currentTimeMillis();
        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
        request.setReferenceNo(referenceNo);
        request.setRemark("Some lampu is not working");
        request.setRequestedDate(new Date());
        request.setRequester(st000X);
        request.setAsset(asset);
        request.setLocation(lctn001);
        maintenanceRequestDao.save(request, user);

        DexMaintenanceRequest savedRequest = maintenanceRequestDao.findByReferenceNo(referenceNo);
        LOG.debug("TEST: " + ((DexMaintenanceRequest) request).getDescription());
    }

}
