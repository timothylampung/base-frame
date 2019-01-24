package com.assettagging.spotit.common.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.common.domain.model.DexBank;
import com.assettagging.spotit.common.domain.model.DexBankImpl;
import com.assettagging.spotit.common.domain.model.DexBankStatus;
import com.assettagging.spotit.common.domain.model.DexBankType;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.dao.DexUserDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
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
    @Rollback(true)
    public void saveBank(){
        DexUser user = userDao.findByUsername("nazifah.rosli");

        DexBank bank = new DexBankImpl();
        bank.setAddress("TEST ADDRESS");
        bank.setBranch("BRANCH");
        bank.setCode("CODE");
        bank.setContactNo("213098192032");
        bank.setDescription("DESC");
        bank.setEmail("asdasd@sds.com");
        bank.setName("Name");
        bank.setPersonInCharge("Persopn in chrfge");
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
    }

    @Test
    public void findAllBanks() {
        List<DexBank> allBanks = dexBankDao.findAllBanks();
    }

    @Test
    public void findBankByBranch() {
    }
}