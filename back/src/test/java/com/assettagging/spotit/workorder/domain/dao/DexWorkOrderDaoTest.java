package com.assettagging.spotit.workorder.domain.dao;


import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.dao.DexAssetDao;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexActivityImpl;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrderImpl;
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
        workOrder.setReporter(null);
        workOrderDao.save(workOrder, getCurrentUser());
        entityManager.flush();
        entityManager.refresh(workOrder);


        DexActivity activity = new DexActivityImpl();
        activity.setCode("ODSS");
        activity.setDescription("DESC");
        activity.setWorkOrder(workOrder);
        activityDao.save(activity,getCurrentUser());


    }


}