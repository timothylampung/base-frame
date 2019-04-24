package my.spotit.asset.common.domain.dao;

import my.spotit.AbstractTest;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

public class DexVendorDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexVendorDaoImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DexVendorDao dexVendorDao;




    @Test
    @Rollback(false)
    public void saveVendor() {

    identityServiceHelper.changeUser("fm1");

    DexVendor  vendor = new DexVendorImpl();
    vendor.setCode("VDR_001");
    vendor.setAddress("NO. 6B");
    vendor.setContactNo("017-4021235");
    vendor.setName("Ah Long Lights");
    vendor.setEmail("ahlonglights@gmail.com");
    vendor.setPersonInCharge("Mr. Long");
    vendor.setDescription("Lights and Fixtures");
    vendor.setBranch("Main HQ");
    dexVendorDao.save(vendor, identityServiceHelper.getCurrentUser());

    LOG.debug("----------------------prepared------------------------ {} ",vendor.getDescription() );
    entityManager.flush();








    }

    @Test
    public void findVendorByCode() {
    }

    @Test
    public void find() {
    }

    @Test
    public void count() {
    }
}
