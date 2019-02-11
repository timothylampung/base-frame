package my.spotit.asset.workorder.domain.dao;


import my.spotit.AbstractTest;
import my.spotit.asset.asset.domain.dao.DexAssetDao;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexActivityImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
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
        activity.setDescription("DESC");
        activity.setWorkOrder(workOrder);
//        activityDao.save(activity,getCurrentUser());//TODO identity helper
    }
}