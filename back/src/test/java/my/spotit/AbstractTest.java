package my.spotit;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import my.spotit.asset.Application;

/**
 * @author : alif.razak@canang.com.my
 * @since : 5/24/2018 1:34 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {


    public String generateString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    protected String getRandomString(int targetStringLength) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890 ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < targetStringLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    protected String getRandomId() {
        return RandomStringUtils.random(12, false, true);
    }
}
