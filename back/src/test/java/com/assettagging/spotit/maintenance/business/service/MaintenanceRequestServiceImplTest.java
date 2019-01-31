package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexTechnician;
import com.assettagging.spotit.maintenance.domain.dao.DexMaintenanceRequestDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;
import com.assettagging.spotit.workorder.business.service.WorkOrderServiceImplTest;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@Transactional
public class MaintenanceRequestServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private AssetService assetService;


    @Test
    public void maintenance_request_user_story_001() throws Exception {

        identityServiceHelper.changeUser("baizura.basar");

        DexMaintenanceRequest mr = new DexMaintenanceRequestImpl();
        DexLocation location = assetService.findLocationByCode("CODE7959");
        mr.setLocation(location);
        DexActor actor = ((DexTechnician) identityServiceHelper.getCurrentUser().getActor());
        mr.setRequester(actor);
        mr.setDescription("MR REQUEST TEST");

        maintenanceRequestService.startMaintenanceRequestTask(mr);

        List<Task> tasks = maintenanceRequestService.findPooledMaintenanceRequestTasks("com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest:DRAFTED", 0, 1);

        Assert.assertFalse(tasks.isEmpty());




    }


    @Test
    public void findMaintenanceRequestById() {

        long id = 2;
        DexMaintenanceRequest maintenanceRequest = maintenanceRequestService.findMaintenanceRequestById(id);

        LOG.debug("TEST: " + maintenanceRequest.getDescription());

    }

    @Test
    public void findMaintenanceRequestByCode() {
    }

    @Test
    public void findMaintenanceRequests() {

        List<DexMaintenanceRequest> maintenanceRequests = maintenanceRequestService.findMaintenanceRequests("%", 0, 999);
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