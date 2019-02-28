package my.spotit.asset.maintenance.business.service;

import my.spotit.AbstractTest;

import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.business.service.WorkOrderService;

import my.spotit.asset.workorder.domain.model.DexWorkOrder;
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

import static my.spotit.asset.DexConstants.ORDER_ID;
import static my.spotit.asset.DexConstants.REQUEST_ID;

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

        identityServiceHelper.changeUser("staff1"); //login web

        DexStaff staff1 = identityService.findStaffByIdentityNo("staff1"); //staff buat req
        DexStaff tech1 = identityService.findStaffByCode("tech1"); //tech
        DexStaff supervisor1 = identityService.findStaffByCode("supervisor1"); //sup

        DexAsset ast001 = assetService.findAssetByCode("ASST_001");
        DexLocation lctn001 = assetService.findLocationByCode("SM_003");

        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
        request.setRemark("DIM LAMP");
        request.setDescription("REPLACEMENT");
        request.setRequestedDate(new Date());
        request.setRequester(staff1);
        request.setAsset(ast001);
        request.setLocation(lctn001);

        String referenceNo = maintenanceRequestService.startMaintenanceRequestTask(request);
        LOG.debug("generated referenceNo = " + referenceNo);

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
            mr.setDelegator(tech1);
            mr.setVerifier(supervisor1);
            mr.setDelegated(true);
            workflowService.completeTask(task);
        }
//
//        //find serialized workorder and assign task to assignee
//        List<Task> pooledWorkOrderTasks = workOrderService.findPooledWorkOrderTasks("%", 0, 9999);
//        Assert.assertTrue(!pooledWorkOrderTasks.isEmpty());
//        for (Task task : pooledWorkOrderTasks) {
//            Map<String, Object> vars = workflowService.getVariables(task.getExecutionId());
//            DexWorkOrder wr = workOrderService.findWorkOrderById((Long) vars.get(ORDER_ID));
//            workflowService.assignTask(task,wr.getAssignee().getName());
//        }

//        // work order exist
//        identityServiceHelper.changeUser("timothy.lampung");
//        List<Task> assignedWorkOrderTasks = workOrderService.findAssignedWorkOrderTasks("%", 0, 9999);
//        Assert.assertTrue(!assignedWorkOrderTasks.isEmpty());

    }
}