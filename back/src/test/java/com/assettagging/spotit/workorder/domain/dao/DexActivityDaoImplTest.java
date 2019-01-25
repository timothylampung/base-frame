package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexActivityImpl;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
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
public class DexActivityDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderDaoImplTest.class);


    @Autowired
    private DexUserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DexActivityDao dexActivityDao;

//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void saveActivity(){
//        DexUser user = userDao.findByUsername("nazifah.rosli");
//
//        DexActivity activity = new DexActivityImpl();
//        activity.setCode("AT02");
//        activity.setDescription("DESC");
//        activity.setWorkOrder(long);
//
//        LOG.debug("----------------------prepared------------------------ {} ",activity.getContactNo() );
//        dexActivityDao.save(activity, user);
//
//        entityManager.flush();
//        DexActivity savedActivity = dexActivityDao.findActivityByCode("CODE");
//        LOG.debug("--------------------saved-------------------------- {} ",savedActivity.getContactNo() );
//
//    }

    @Test
    public void findActivityByCode() {

        DexActivity findActivityByCode = dexActivityDao.findActivityByCode("AT01");
        LOG.debug("TESTING {} ", findActivityByCode.getDescription());

    }

    @Test
    public void findAllActivitys() {

        List<DexActivity> Activitys = dexActivityDao.findAllActivitys();
        for (DexActivity Activity : Activitys) {
            LOG.debug("TEST: " + Activity.getDescription());
        }
    }

}