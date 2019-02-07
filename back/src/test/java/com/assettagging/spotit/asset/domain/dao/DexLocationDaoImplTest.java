package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.*;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
public class DexLocationDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexLocationDaoImplTest.class);

    @Autowired
    private DexUserDao dexUserDao;
    @Autowired
    private DexLocationDao dexLocationDao;

    @Autowired
    private EntityManager entityManager;




    @Test
    @Transactional
    @Rollback(false)
    public void findDexLocationTest() {
        List<DexLocation> locations = dexLocationDao.find();
        for (DexLocation location : locations) {
            LOG.debug("TEST: " + location.getDescription());
        }
    }


    @Test
    @Transactional
    @Rollback(false)
    public void findLocationByCode() {
        String code = "CODE7959";
        DexLocation locationByCode =  dexLocationDao.findByCode(code);

        LOG.debug("TEST: " + locationByCode.getDescription());

    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveLocation(){

        DexLocation location = new DexLocationImpl();
        location.setCode("CODE7960");
        location.setDescription("DESCRIPTIONLAG60");
        location.setAddress("Jalan40");
        location.setName("Rumah420");


        LOG.debug("----------------------prepared------------------------ {} ",location.getDescription() );
//        dexLocationDao.save(location, getCurrentUser()); TODO identity helper
        entityManager.flush();
        DexLocation savedLocation = dexLocationDao.findByCode("CODE7960");
        LOG.debug("--------------------saved-------------------------- {} ",savedLocation.getDescription() );

    }
}