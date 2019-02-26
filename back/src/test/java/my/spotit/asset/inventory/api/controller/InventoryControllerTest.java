package my.spotit.asset.inventory.api.controller;

import my.spotit.AbstractApiTest;
import io.restassured.http.ContentType;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class InventoryControllerTest extends AbstractApiTest {

    private final String ASSET_URL = "/api/inventory";


    @Test
    public void findPagedParts() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/parts")
                .getBody().prettyPrint();
    }

    @Test
    public void findParts() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/parts")
                .getBody().prettyPrint();


    }

    @Test
    public void findPagedPartCodes() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/part-codes")
                .getBody().prettyPrint();


    }

    @Test
    public void findPartCodes() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/part-codes")
                .getBody().prettyPrint();


    }

    @Test
    public void findPagedComponents() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/components")
                .getBody().prettyPrint();

    }

    @Test
    public void findComponents() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/components")
                .getBody().prettyPrint();

    }

    @Test
    public void findPagedParts1() {
    }

    @Test
    public void findParts1() {
    }

    @Test
    public void findPartByCode() {
    }

    @Test
    public void saveAsset() {
    }

    @Test
    public void findPagedPartCodes1() {
    }

    @Test
    public void findPartCodes1() {
    }

    @Test
    public void findPartCodeByCode() {
    }

    @Test
    public void savePartCode() {
    }

    @Test
    public void findPagedComponents1() {
    }

    @Test
    public void findComponents1() {
    }

    @Test
    public void findComponentByCode() {
    }

    @Test
    public void saveComponent() {
    }
}