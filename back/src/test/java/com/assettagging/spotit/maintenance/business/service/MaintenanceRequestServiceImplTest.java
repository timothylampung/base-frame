package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexStaff;
import com.assettagging.spotit.identity.domain.model.DexSupervisor;
import com.assettagging.spotit.identity.domain.model.DexTechnician;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import com.assettagging.spotit.workflow.business.service.WorkflowService;
import com.assettagging.spotit.workorder.business.service.WorkOrderService;

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

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.assettagging.spotit.DexConstants.ORDER_ID;
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
    @Rollback(false)
    public void maintenance_request_staff_story_001() throws Exception {

        identityServiceHelper.changeUser("timothy.lampung"); //login web

        DexStaff st000X = identityService.findStaffByIdentityNo("348938398934"); //staff buat req


        DexActor technician001 = identityService.findTechnicianByCode("TECH_1549007206147"); //tech
        DexActor supervisor001 = identityService.findSupervisorByCode("SPVR_1549007207144"); //sup

        DexAsset ast001 = assetService.findAssetByCode("ASST_001");
        DexLocation lctn001 = assetService.findLocationByCode("SM_003");



        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
        request.setRemark("Some lampu is not working");
        request.setDescription("Some lampu is not working fuse suspected");
        request.setRequestedDate(new Date());
        request.setRequester(st000X);
        request.setAsset(ast001);
        request.setLocation(lctn001);

        String referenceNo = maintenanceRequestService.startMaintenanceRequestTask(request);

        // log in as FM
        identityServiceHelper.changeUser("zamir.zaharul");
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

        //find serialized workorder and assign task to assignee
        List<Task> pooledWorkOrderTasks = workOrderService.findPooledWorkOrderTasks("%", 0, 9999);
        Assert.assertTrue(!pooledWorkOrderTasks.isEmpty());
        for (Task task : pooledWorkOrderTasks) {
            Map<String, Object> vars = workflowService.getVariables(task.getExecutionId());
            DexWorkOrder wr = workOrderService.findWorkOrderById((Long) vars.get(ORDER_ID));
            workflowService.assignTask(task,wr.getAssignee().getName());
        }

//        // work order exist
//        identityServiceHelper.changeUser("timothy.lampung");
//        List<Task> assignedWorkOrderTasks = workOrderService.findAssignedWorkOrderTasks("%", 0, 9999);
//        Assert.assertTrue(!assignedWorkOrderTasks.isEmpty());


    }
}