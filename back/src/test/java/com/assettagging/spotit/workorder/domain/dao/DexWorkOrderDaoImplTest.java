package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDao;
import com.assettagging.spotit.asset.domain.dao.DexAssetCodeDaoImplTest;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
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

        String code = "WO01";
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
}