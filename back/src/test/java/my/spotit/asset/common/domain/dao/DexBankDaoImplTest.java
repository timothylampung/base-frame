package my.spotit.asset.common.domain.dao;

import my.spotit.AbstractTest;

import my.spotit.asset.common.domain.model.DexBank;
import my.spotit.asset.common.domain.model.DexBankImpl;
import my.spotit.asset.common.domain.model.DexBankStatus;
import my.spotit.asset.common.domain.model.DexBankType;
import my.spotit.asset.identity.domain.dao.DexUserDao;
import my.spotit.asset.identity.domain.model.DexUser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
public class DexBankDaoImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(DexBankDaoImplTest.class);


    @Autowired
    private DexUserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DexBankDao dexBankDao;


    @Test
    @Transactional
    @Rollback(false)
    public void saveBank(){
        DexUser user = userDao.findByUsername("nazifah.rosli");

        DexBank bank = new DexBankImpl();
        bank.setAddress("TEST ADDRESS");
        bank.setBranch("BRANCH");
        bank.setCode("CODE79");
        bank.setContactNo("213098192032");
        bank.setDescription("DESC");
        bank.setEmail("asdasd@sds.com");
        bank.setName("Name");
        bank.setPersonInCharge("Persopn in charge");
        bank.setRemarks("Remarks");
        bank.setStatus(DexBankStatus.Active);
        bank.setType(DexBankType.Branch);

        LOG.debug("----------------------prepared------------------------ {} ",bank.getContactNo() );
        dexBankDao.save(bank, user);

        entityManager.flush();
        DexBank savedBank = dexBankDao.findBankByCode("CODE");
        LOG.debug("--------------------saved-------------------------- {} ",savedBank.getContactNo() );

    }

    @Test
    public void findBankByCode() {

        DexBank findBankByCode = dexBankDao.findBankByCode("CODE");
        LOG.debug("TESTING {} ",findBankByCode.getContactNo() );

    }

    @Test
    public void findAllBanks() {
        List<DexBank> allBanks = dexBankDao.findAllBanks();
        for (DexBank allBank : allBanks) {

            {
                LOG.debug("TESTOOOOOOO " + allBank.getCode());
            }
        }
    }

    @Test
    public void findBankByBranch() {
    }


}