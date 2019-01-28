package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDao;
import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDaoImplTest;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrderImpl;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

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
//        LOG.debug("----------------------prepared------------------------ {} ",workOrder.getAssetId() );
//        dexWorkOrderDao.save(workOrder, user);
//
//        entityManager.flush();
//        DexworkOrder savedworkOrder = dexworkOrderDao.findworkOrderByCode("CODE");
//        LOG.debug("--------------------saved-------------------------- {} ",savedworkOrder.getContactNo() );
//
//    }
}