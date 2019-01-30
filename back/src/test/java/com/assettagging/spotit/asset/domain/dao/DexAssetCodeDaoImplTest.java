package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.common.domain.dao.DexBankDao;
import com.assettagging.spotit.common.domain.model.DexBank;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
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
public class DexAssetCodeDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexAssetCodeDaoImplTest.class);


    @Autowired
    private DexUserDao dexUserDao;
    @Autowired
    private DexAssetCodeDao DexAssetCodeDao;
    @Autowired
    private EntityManager entityManager;






    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetCodeTest() {
        List<DexAssetCode> assetCodes = DexAssetCodeDao.findAllAssetCodes();
        for (DexAssetCode assetCode : assetCodes) {
            LOG.debug("TEST: " + assetCode.getDescription());
        }
    }



    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetCodeByCode() {
        String code = "AC01";
        DexAssetCode DexAssetCodeByCode =  DexAssetCodeDao.findAssetCodeByCode(code);

        LOG.debug("TEST: " + DexAssetCodeByCode.getDescription());

    }




}