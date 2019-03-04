package my.spotit.asset.maintenance.business.service;

import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import my.spotit.AbstractTest;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.business.service.WorkOrderService;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import static my.spotit.asset.DexConstants.MAX_LIMIT;
import static my.spotit.asset.DexConstants.ORDER_ID;
import static my.spotit.asset.DexConstants.REQUEST_ID;

@Transactional
public class WorkOrderServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderServiceImplTest.class);

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


    // DRAFT --> COMPLETED
    // PREPARE --> CHECK --> VERIFY
    @Test
    @Rollback(false)
    public void maintenance_request_staff_story_001() throws Exception {


            identityServiceHelper.changeUser("staff2"); //login web

            DexStaff staff1 = identityService.findStaffByIdentityNo("staff2"); //staff buat req
            DexStaff tech1 = identityService.findStaffByCode("tech2"); //tech
            DexStaff supervisor1 = identityService.findStaffByCode("supervisor1"); //sup

            DexAsset ast001 = assetService.findAssetByCode("ASST_001");
            DexLocation lctn001 = assetService.findLocationByCode("SM_002");

            DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
            request.setRemark("Leaking AirCond");
            request.setDescription("Change Filters");
            request.setRequestedDate(new Date());
            request.setRequester(staff1);
            request.setAsset(ast001);
            request.setLocation(lctn001);

            String referenceNo = maintenanceRequestService.startMaintenanceRequestTask(request);
            LOG.debug("generated referenceNo = " + referenceNo);

            // log in as FM
            identityServiceHelper.changeUser("fm2");
            //find serialized workorder and assign task to assignee
            List<Task> assignedDraftedTasks = maintenanceRequestService.findAssignedMaintenanceRequestTasks("%", 0, MAX_LIMIT);
            Assert.assertTrue(!assignedDraftedTasks.isEmpty());
            for (Task task : assignedDraftedTasks) {
                Map<String, Object> vars = workflowService.getVariables(task.getExecutionId());
                DexMaintenanceRequest mr = maintenanceRequestService.findMaintenanceRequestById((Long) vars.get(REQUEST_ID));
                mr.setDelegator(tech1);
                mr.setVerifier(supervisor1);
                mr.setDelegated(true);
                maintenanceRequestService.updateMaintenanceRequest(mr);
                entityManager.flush();
                workflowService.completeTask(task);
            }

            // log in as tech1
            identityServiceHelper.changeUser("tech2");
            List<Task> assignedPreparedTasks = workflowService.findAssignedTasks("%", 0, MAX_LIMIT);
            Assert.assertTrue(!assignedPreparedTasks.isEmpty());
            for (Task task : assignedPreparedTasks) {
                Map<String, Object> vars = workflowService.getVariables(task.getExecutionId());
                DexWorkOrder wo = workOrderService.findWorkOrderById((Long) vars.get(ORDER_ID));
                workOrderService.updateWorkOrder(wo);

                // set time log
                workOrderService.startWorkOrderLog(wo);
                Thread.sleep(1000 * 30);
                workOrderService.stopWorkOrderLog(wo);
                workflowService.completeTask(task);
            }


    }


}