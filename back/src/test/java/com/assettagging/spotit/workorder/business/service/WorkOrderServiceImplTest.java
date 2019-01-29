package com.assettagging.spotit.workorder.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.common.domain.dao.DexBankDao;
import com.assettagging.spotit.common.domain.dao.DexBankDaoImplTest;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.workflow.business.service.WorkflowService;
import com.assettagging.spotit.workorder.domain.dao.DexWorkOrderDao;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrderImpl;

import org.flowable.task.api.Task;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@Transactional
public class WorkOrderServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(WorkOrderServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IdentityService identityService;

    @Autowired


    @Test
    public void userStory001() {
        DexWorkOrder order = new DexWorkOrderImpl();
        order.setAsset(assetService.findAssetByAssetCode("AST-001"));
        order.setAssignee(identityService.findStaffByCode("STF-001"));
        order.setReporter(identityService.findStaffByCode("STF-001"));
        order.setDescription("F-ed up");

        try {
            workOrderService.startWorkOrderTask(order);

            identityServiceHelper.changeUser("STF-001");

            List<Task> draftedTasks = workOrderService.findAssignedWorkOrderTasks("%", 0, 999);
            for (Task draftedTask : draftedTasks) {
                Long orderId = null; // todo: workflowService.findTaskByVariable("orderId");
                DexWorkOrder refWf = workOrderService.findWorkOrderById(orderId);
                // refWf.setStartTime(new Date());
                workflowService.completeTask(draftedTask);
            }

            // report logs in
            identityServiceHelper.changeUser("RPT-001");
            List<Task> checkedTasks = workOrderService.findAssignedWorkOrderTasks("%", 0, 999);
            for (Task checkedTask : checkedTasks) {
                Long orderId = null; // todo: workflowService.findTaskByVariable("orderId");
                DexWorkOrder refWf = workOrderService.findWorkOrderById(orderId);
                // refWf.setRemark("yes done and done");
                workflowService.completeTask(checkedTask);
            }

            identityServiceHelper.changeUser("RPT-001");
            Integer checkedTaskCount = workOrderService.countAssignedWorkOrderTask("%");
            Assert.assertEquals("Shouldnt be zero", java.util.Optional.ofNullable(checkedTaskCount), 0);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void findWorkOrderById() {
    }

    @Test
    public void findWorkOrderByCode() {

        DexWorkOrder findWorkOrderByCode = workOrderService.findWorkOrderByCode("CODE_@#!");
        LOG.debug("TEST: " + findWorkOrderByCode.getDescription());
    }

    @Test
    public void findWorkOrders() {

        List<DexWorkOrder> workOrders = workOrderService.findWorkOrders("%",0,999);
        for (DexWorkOrder workOrder : workOrders) {
            LOG.debug("TEST: " + workOrder.getDescription());
        }
    }

    @Test
    public void countWorkOrder() {
    }

    @Test
    public void countWorkOrder1() {
    }

    @Test
    public void saveWorkOrder() {
    }

    @Test
    public void updateWorkOrder() {
    }

    @Test
    public void removeWorkOrder() {
    }

    @Test
    public void findActivityById() {
    }

    @Test
    public void findActivityByCode() {

        DexActivity findActivityByCode = workOrderService.findActivityByCode("ODSS");

        LOG.debug("TEST: " + findActivityByCode.getDescription());


    }

    @Test
    public void findActivitys() {
        List<DexActivity> Activities = null; // tod workOrderService.findActivities("%",null 0,999);
        for (DexActivity activity : Activities) {
            LOG.debug("TEST: " + activity.getDescription());
        }
    }

    @Test
    public void countActivity() {
    }

    @Test
    public void countActivity1() {
    }

    @Test
    public void saveActivity() {
    }

    @Test
    public void updateActivity() {
    }

    @Test
    public void removeActivity() {
    }
}