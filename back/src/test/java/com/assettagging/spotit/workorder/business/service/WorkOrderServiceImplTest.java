package com.assettagging.spotit.workorder.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.common.domain.dao.DexBankDao;
import com.assettagging.spotit.common.domain.dao.DexBankDaoImplTest;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.workorder.domain.dao.DexWorkOrderDao;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

        List<DexActivity> Activities = workOrderService.findActivitys("%",0,999);
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