package my.spotit.asset.asset.domain.dao;

import my.spotit.AbstractTest;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.domain.dao.DexUserDao;
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
    IdentityServiceHelper identityServiceHelper;


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
    @Rollback(true)
    public void saveAsset(){

        identityServiceHelper.changeUser("maula");

        DexAsset asset = new DexAssetImpl();
        asset.setCode("CODE7957");
        asset.setDescription("DESCRIPTIONLAGHEAY");

        LOG.debug("----------------------prepared------------------------ {} ",asset.getDescription() );
        dexAssetDao.save(asset, identityServiceHelper.getCurrentUser()); //TODO identity helper

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