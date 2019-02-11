package com.assettagging.spotit.workorder.domain.dao;


import com.assettagging.spotit.AbstractTest;
import my.spotit.asset.domain.dao.DexAssetDao;
import my.spotit.workorder.domain.dao.DexActivityDao;
import my.spotit.workorder.domain.dao.DexWorkOrderDao;
import my.spotit.workorder.domain.model.DexActivity;
import my.spotit.workorder.domain.model.DexActivityImpl;
import my.spotit.workorder.domain.model.DexWorkOrder;
import my.spotit.workorder.domain.model.DexWorkOrderImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DexWorkOrderDaoTest extends AbstractTest {

    @Autowired
    private DexActivityDao activityDao;
    @Autowired
    private DexWorkOrderDao workOrderDao;
    @Autowired
    private DexAssetDao assetDao;
    @PersistenceContext
    private EntityManager entityManager;



    @Test
    @Transactional
    @Rollback(false)
    public void saveWorkOrder(){
        DexWorkOrder workOrder = new DexWorkOrderImpl();
        workOrder.setAsset(null);
        workOrder.setAssignee(null);
        workOrder.setDescription("DEXS");
        workOrder.setLocation(null);
        workOrder.setMaintenanceRequest(null);
        workOrder.setSupervisor(null);
//        workOrderDao.save(workOrder, getCurrentUser());//TODO identity helper
        entityManager.flush();
        entityManager.refresh(workOrder);


        DexActivity activity = new DexActivityImpl();
        activity.setCode("ODSS");
        activity.setDescription("DESC");
        activity.setWorkOrder(workOrder);
//        activityDao.save(activity,getCurrentUser());//TODO identity helper


    }


}