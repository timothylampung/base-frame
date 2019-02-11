package my.spotit;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Base64;

import static io.restassured.RestAssured.given;

/**
 * @author : alif.razak@canang.com.my
 * @since : 6/26/2018 12:52 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractApiTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractApiTest.class);
    private static final String BASE_URI = "http://localhost:8080/";

    private String basicAuth;

    public String getAccessToken() {
        return requestAccessToken();
    }

    @Before
    public void setUp() throws Exception {
        basicAuth = Base64.getEncoder().encodeToString("spot-it-client:XY7kmzoNzl100".getBytes());
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = 5000;
        requestAccessToken();
    }

    private String requestAccessToken() {
        Response response = given()
                .header(new Header("Authorization", "Basic " + this.basicAuth))
                .queryParam("username", "root")
                .queryParam("password", "abc123")
                .queryParam("grant_type", "password")
                .when()
                .post("/oauth/token")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();

        return response.getBody().jsonPath().getString("access_token");
    }
}
