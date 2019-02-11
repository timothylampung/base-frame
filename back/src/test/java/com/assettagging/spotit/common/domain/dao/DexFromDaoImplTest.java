package com.assettagging.spotit.common.domain.dao;

import my.spotit.common.domain.dao.DexFormDao;
import my.spotit.identity.domain.dao.DexUserDao;
import my.spotit.identity.domain.model.DexUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import my.spotit.common.domain.model.DexForm;
import my.spotit.common.domain.model.DexFormImpl;
import com.assettagging.spotit.config.TestApplicationConfig;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfig.class)
@Transactional
public class DexFromDaoImplTest {

    @Autowired
    private DexFormDao formDao;
    @Autowired
    private DexUserDao userDao;

    @Test
    @Transactional
    public void saveForm() {
        DexUser user = userDao.findByUsername("root");
        DexForm form = new DexFormImpl(); //fromimpl  is intoruducing himself as dex form
        form.setCode("sdasdasdasd");
        form.setRefNo("asdasdasdasd");
        formDao.save(form, user);
    }

    @Test
    public void findAllForms() {

    }

    @Test
    public void findFormByCode() {
    }
}