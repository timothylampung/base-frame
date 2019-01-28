package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

public class DexAssetDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexLocationDaoImplTest.class);

    @Autowired
    private DexUserDao dexUserDao;
    @Autowired
    private DexAssetDao dexAssetDao;
    @Autowired
    private EntityManager entityManager;



    @Test
    @Transactional
    @Rollback(false)
    public void findAllAssets() {
        List<DexAsset> assets = dexAssetDao.findAllAssets();
        for (DexAsset asset : assets) {
            LOG.debug("TEST: " + asset.getDescription());
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetByCode() {
        String code = "AS01";
        DexAsset DexAssetByCode =  dexAssetDao.findAssetByAssetCode(code);

        LOG.debug("TEST: " + DexAssetByCode.getDescription());

    }
}