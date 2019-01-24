package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.Inventory.domain.dao.DexComponentDaoImplTest;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.inventory.domain.dao.DexComponentDao;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.Assert.*;

public class DexLocationDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexLocationDaoImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;
    @Autowired
    private DexLocationDao dexLocationDao;


    @Before
    public void setUp() throws Exception {
        identityServiceHelper.changeUser("nazifah.rosli");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Rollback(false)
    public void findDexLocationTest() {
        List<DexLocation> locations = dexLocationDao.findAllLocations();
        for (DexLocation location : locations) {
            LOG.debug("TEST: " + location.getDescription());
        }
    }
}