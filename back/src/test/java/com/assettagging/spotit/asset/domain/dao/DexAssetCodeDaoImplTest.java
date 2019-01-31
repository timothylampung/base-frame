package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.asset.domain.model.DexAssetCodeImpl;
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
public class DexAssetCodeDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexAssetCodeDaoImplTest.class);


    @Autowired
    private DexUserDao dexUserDao;
    @Autowired
    private DexAssetCodeDao dexAssetCodeDao;
    @Autowired
    private EntityManager entityManager;






    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetCodeTest() {
        List<DexAssetCode> assetCodes = dexAssetCodeDao.find();
        for (DexAssetCode assetCode : assetCodes) {
            LOG.debug("TEST: " + assetCode.getDescription());
        }
    }



    @Test
    @Transactional
    @Rollback(false)
    public void findDexAssetCodeByCode() {
        String code = "CODE7958";
        DexAssetCode DexAssetCodeByCode =  dexAssetCodeDao.findByCode(code);

        LOG.debug("TEST: " + DexAssetCodeByCode.getDescription());

    }


    @Test
    @Transactional
    @Rollback(false)
    public void saveAssetCode(){

        DexAssetCode assetCode = new DexAssetCodeImpl();
        assetCode.setCode("CODE7959");
        assetCode.setDescription("DESCRIPTIONLAG59");

        LOG.debug("----------------------prepared------------------------ {} ",assetCode.getDescription() );
        dexAssetCodeDao.save(assetCode, getCurrentUser());

        entityManager.flush();
        DexAssetCode savedAssetCode = dexAssetCodeDao.findByCode("CODE7959");
        LOG.debug("--------------------saved-------------------------- {} ",savedAssetCode.getDescription() );

    }

}