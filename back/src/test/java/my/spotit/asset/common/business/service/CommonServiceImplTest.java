package my.spotit.asset.common.business.service;

import my.spotit.AbstractTest;

import my.spotit.asset.helper.IdentityServiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonServiceImplTest extends AbstractTest {
    private static final Logger log = LoggerFactory
            .getLogger(CommonServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;
    @Autowired
    private CommonService commonService;

    @Before
    public void setUp() throws Exception {
        identityServiceHelper.changeUser("nazifah.rosli");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findPositionCodeById() {
    }

    @Test
    public void findPositionCodeByCode() {
    }

    @Test
    public void findPositionCodes() {
    }

    @Test
    public void countPositionCode() {
    }

    @Test
    public void countPositionCode1() {
    }

    @Test
    public void savePositionCode() {
    }

    @Test
    public void updatePositionCode() {
    }

    @Test
    public void removePositionCode() {
    }

    @Test
    public void findGradeCodeById() {
    }

    @Test
    public void findGradeCodeByCode() {
    }

    @Test
    public void findGradeCodes() {
    }

    @Test
    public void countGradeCode() {
    }

    @Test
    public void countGradeCode1() {
    }

    @Test
    public void saveGradeCode() {
        DexGradeCode gradeCode = new DexGradeCodeImpl();
        gradeCode.setCode("HELLO CODE!");
        gradeCode.setDescription("THIS CODE IS FAKE!");

        commonService.saveGradeCode(gradeCode);
    }

    @Test
    public void updateGradeCode() {
    }

    @Test
    public void removeGradeCode() {
    }
}
