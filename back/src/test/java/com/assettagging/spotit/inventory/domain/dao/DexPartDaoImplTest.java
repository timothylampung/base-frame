package com.assettagging.spotit.Inventory.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.inventory.domain.dao.DexPartDao;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DexPartDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexPartDaoImplTest.class);

    @Autowired
    private IdentityService identityService;
    @Autowired
    private com.assettagging.spotit.inventory.domain.dao.DexPartDao DexPartDao;

    @Test
    @Rollback(false)
    public void findDexPartTest() {
        List<DexPart> parts = DexPartDao.findAllParts();
        for (DexPart part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexPartByCodeTest() {
        DexPart partByCode = DexPartDao.findPartByCode("PA01");

            LOG.debug("TEST: " + partByCode.getDescription());

    }

}
