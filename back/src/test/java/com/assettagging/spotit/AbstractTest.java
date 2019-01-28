package com.assettagging.spotit;

import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.identity.domain.model.DexUser;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : alif.razak@canang.com.my
 * @since : 5/24/2018 1:34 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    IdentityServiceHelper identityServiceHelper;


    @Before
    public void setUp() throws Exception {
        identityServiceHelper.changeUser("nazifah.rosli");
    }

    public DexUser getCurrentUser() {
        return identityServiceHelper.getCurrentUser();
    }

    @After
    public void tear() throws Exception {

    }
}
