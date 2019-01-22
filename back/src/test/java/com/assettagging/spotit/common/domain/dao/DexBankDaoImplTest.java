package com.assettagging.spotit.common.domain.dao;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.common.domain.model.DexBank;
import com.assettagging.spotit.identity.business.service.IdentityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DexBankDaoImplTest extends AbstractTest {


    @Autowired
    private IdentityService identityService;
    @Autowired
    private DexBankDao  dexBankDao;

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