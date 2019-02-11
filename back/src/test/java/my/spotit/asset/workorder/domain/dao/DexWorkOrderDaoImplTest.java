package my.spotit.asset.workorder.domain.dao;

import my.spotit.AbstractTest;

import my.spotit.asset.identity.domain.dao.DexUserDao;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Transactional
public class DexWorkOrderDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderDaoImplTest.class);


    @Autowired
    private DexUserDao dexUserDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DexWorkOrderDao dexWorkOrderDao;


    @Test
    @Transactional
    @Rollback(false)
    public void findWorkOrderByCode() {

        String code = "CODE_@#!";
        DexWorkOrder dexWorkOrderByCode =  dexWorkOrderDao.findWorkOrderByCode(code);

        LOG.debug("TEST: " + dexWorkOrderByCode.getDescription());

    }

    @Test
    public void findAllWorkOrders() {

        List<DexWorkOrder> workOrders = dexWorkOrderDao.findAllWorkOrders();
        for (DexWorkOrder workOrder : workOrders) {
            LOG.debug("TEST: " + workOrder.getDescription());
        }
    }



//    @Test
//    @Transactional
//    @Rollback(false)
//    public void saveWorkOrder(){
//        DexUser user = dexUserDao.findByUsername("nazifah.rosli");
//
//        DexWorkOrder workOrder = new DexWorkOrderImpl();
//        workOrder.setCode("WO02");
//        workOrder.setDescription("DESKRIPSI");
//        workOrder.setWorkOrder(long);
//
//        LOG.debug("----------------------prepared------------------------ {} ",workOrder.getAsset() );
//        dexWorkOrderDao.save(workOrder, user);
//
//        entityManager.flush();
//        DexworkOrder savedworkOrder = dexworkOrderDao.findworkOrderByCode("CODE");
//        LOG.debug("--------------------saved-------------------------- {} ",savedworkOrder.getContactNo() );
//
//    }
}