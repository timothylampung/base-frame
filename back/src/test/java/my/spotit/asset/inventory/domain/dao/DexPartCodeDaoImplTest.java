package my.spotit.asset.inventory.domain.dao;

import my.spotit.AbstractTest;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.inventory.domain.model.DexPartCode;
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
    private my.spotit.asset.inventory.domain.dao.DexPartCodeDao DexPartCodeDao;

    @Test
    @Rollback(false)
    public void findDexPartCodeTest() {
        List<DexPartCode> partCodes = DexPartCodeDao.find();
        for (DexPartCode partCode : partCodes) {
            LOG.debug("TEST: " + partCode.getDescription());
        }
    }

    @Test
    @Rollback(false)
    public void findDexPartCodeByCodeTest() {
        DexPartCode partCodeByCode = DexPartCodeDao.findPartCodeByCode("PC_001");

        LOG.debug("TEST: " + partCodeByCode.getDescription());

    }



}