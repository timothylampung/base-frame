package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.model.DexStaff;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import com.assettagging.spotit.workflow.business.service.WorkflowService;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;

import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.assettagging.spotit.DexConstants.REQUEST_ID;

@Transactional
public class MaintenanceRequestServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceRequestServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private AssetService assetService;


    @Test
    public void maintenance_request_user_story_001() throws Exception {

        identityServiceHelper.changeUser("baizura.basar");

        DexStaff st000X = identityService.findStaffByCode("ST000X");
        DexStaff technician001 = identityService.findStaffByCode("ST0002");
        DexStaff supervisor001 = identityService.findStaffByCode("TC0002");
        DexAsset ast001 = assetService.findAssetByCode("AST001");
        DexLocation lctn001 = assetService.findLocationByCode("LCTN001");

        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
        request.setRemark("Some lampu is not working");
        request.setRequestedDate(new Date());
        request.setRequester(st000X);
        request.setAsset(ast001);
        request.setLocation(lctn001);

        String referenceNo = maintenanceRequestService.startMaintenanceRequestTask(request);

        // log in as FM
        identityServiceHelper.changeUser("fm1");
        List<Task> pooledDraftedTasks = maintenanceRequestService.findPooledMaintenanceRequestTasks("%", 0, 999);
        Assert.assertTrue(!pooledDraftedTasks.isEmpty());
        for (Task task : pooledDraftedTasks) {
            workflowService.claimTask(task);
        }

        List<Task> assignedDraftedTasks = maintenanceRequestService.findAssignedMaintenanceRequestTasks("%", 0, 999);
        Assert.assertTrue(!assignedDraftedTasks.isEmpty());
        for (Task task : assignedDraftedTasks) {
            Map<String, Object> vars = workflowService.getVariables(task.getExecutionId());
            DexMaintenanceRequest mr = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(REQUEST_ID));
            mr.setDelegator(technician001);
            mr.setVerifier(supervisor001);
            mr.setDelegated(true);

            workflowService.completeTask(task);
        }

        // work order exist
        identityServiceHelper.changeUser("technician001");
        List<Task> assignedWorkOrderTasks = workOrderService.findAssignedWorkOrderTasks("%", 0, 9999);
        Assert.assertTrue(!assignedWorkOrderTasks.isEmpty());
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