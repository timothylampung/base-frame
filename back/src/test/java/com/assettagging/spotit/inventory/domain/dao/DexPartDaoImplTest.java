package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
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
    private DexPartDao DexPartDao;

    @Test
    @Rollback(false)
    public void findDexPartTest() {
        List<DexPart> parts = DexPartDao.findAllPart();
        for (DexPart part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }
    }
}
