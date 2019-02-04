package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class DexAssetDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexLocationDaoImplTest.class);

    @Autowired
    private DexUserDao dexUserDao;
    @Autowired
    private DexAssetDao dexAssetDao;

    @Autowired
    private DexLocationDao dexLocationDao;


    @Autowired
    private EntityManager entityManager;



    @Test
    @Transactional
    @Rollback(false)
    public void findAllAssets() {
        List<DexAsset> assets = dexAssetDao.find();
        for (DexAsset asset : assets) {
            LOG.debug("TEST: " + asset.getDescription());
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetByCode() {
        String code = "CODE7956";
        DexAsset DexAssetByCode =  dexAssetDao.findByCode(code);

        LOG.debug("TEST: " + DexAssetByCode.getDescription());

    }
    @Test
    @Transactional
    @Rollback(false)
    public void saveAsset(){

        DexAsset asset = new DexAssetImpl();
        asset.setCode("CODE7957");
        asset.setDescription("DESCRIPTIONLAGHEAY");

        LOG.debug("----------------------prepared------------------------ {} ",asset.getDescription() );
//        dexAssetDao.save(asset, getCurrentUser()); //TODO identity helper

        entityManager.flush();
        DexAsset savedAsset = dexAssetDao.findByCode("CODE7957");
        LOG.debug("--------------------saved-------------------------- {} ",savedAsset.getDescription() );

    }
//    @Test
//    public void findByLocation() {
//
//        int location = 1;
//        List<DexAsset> daoAssetsByLocation = dexAssetDao.findByLocation(location);
//        for (DexAsset daoAssetByLocation : daoAssetsByLocation) {
//            LOG.debug("TEST: " + daoAssetByLocation.getDescription());
//
//        }
//    }
}