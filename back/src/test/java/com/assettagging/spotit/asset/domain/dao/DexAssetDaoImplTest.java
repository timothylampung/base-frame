package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class DexAssetDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexLocationDaoImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;
    @Autowired
    private DexAssetDao dexAssetDao;

    @Before
    public void setUp() throws Exception {
        identityServiceHelper.changeUser("nazifah.rosli");

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void findAllAssets() {
        List<DexAsset> assets = dexAssetDao.findAllAssets();
        for (DexAsset asset : assets) {
            LOG.debug("TEST: " + asset.getDescription());
        }
        
        
    }
}