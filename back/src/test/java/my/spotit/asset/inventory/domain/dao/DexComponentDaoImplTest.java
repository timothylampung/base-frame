package my.spotit.asset.inventory.domain.dao;

import my.spotit.AbstractTest;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.inventory.domain.model.DexComponent;

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
    private my.spotit.asset.inventory.domain.dao.DexComponentDao DexComponentDao;



    @Test
    @Rollback(false)
    public void findDexComponentTest() {
        List<DexComponent> parts = DexComponentDao.findComponents();
        for (DexComponent part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexComponentByCodeTest() {
        DexComponent partByCode = DexComponentDao.findComponentByCode("CO_001");

        LOG.debug("TEST: " + partByCode.getDescription());

    }

    
    
}
