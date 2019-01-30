package com.assettagging.spotit.Inventory.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.inventory.domain.dao.DexPartCodeDao;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DexPartCodeDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexPartCodeDaoImplTest.class);


    @Autowired
    private IdentityService identityService;
    @Autowired
    private com.assettagging.spotit.inventory.domain.dao.DexPartCodeDao DexPartCodeDao;

    @Test
    @Rollback(false)
    public void findDexPartCodeTest() {
        List<DexPartCode> partCodes = DexPartCodeDao.findAllPartCodes();
        for (DexPartCode partCode : partCodes) {
            LOG.debug("TEST: " + partCode.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexPartCodeByCodeTest() {
        DexPartCode partCodeByCode = DexPartCodeDao.findPartCodeByCode("PC01");

        LOG.debug("TEST: " + partCodeByCode.getDescription());

    }



}