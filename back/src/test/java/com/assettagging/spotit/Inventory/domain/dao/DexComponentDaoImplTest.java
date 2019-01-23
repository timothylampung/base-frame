package com.assettagging.spotit.Inventory.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.inventory.domain.dao.DexComponentDao;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DexComponentDaoImplTest extends AbstractTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(DexComponentDaoImplTest.class);

    @Autowired
    private IdentityService identityService;
    @Autowired
    private DexComponentDao DexComponentDao;

    @Test
    @Rollback(false)
    public void findDexComponentTest() {
        List<DexComponent> components = DexComponentDao.findAllComponent();
        for (DexComponent component : components) {
            LOG.debug("TEST: " + component.getDescription());
        }
    }
}
