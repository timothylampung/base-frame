package my.spotit.asset.asset.api.controller;

import my.spotit.AbstractApiTest;
import my.spotit.TestUtil;
import my.spotit.asset.asset.api.vo.Asset;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AssetControllerTest extends AbstractApiTest {

    private final String ASSET_URL = "api/asset";


    @Test
    public void test_find_paged_assets() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/assets")
                .getBody().prettyPrint();
    }

    @Test
    public void findAssets() throws JsonProcessingException {


    }

    @Test
    public void findAssetByAssetCode() {
    }

    @Test
    public void findAssetByLocation() {
    }

    @Test
    public void saveAsset() throws JsonProcessingException {

        Asset vo = new Asset();
        vo.setCode("ASSET_CODE");
        vo.setDescription("ASSET_DESC");

        given()

                .contentType(ContentType.JSON)
                .body(TestUtil.convertToJsonBytes(vo))
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .post(ASSET_URL + "/asset")
                .getBody().prettyPrint();
    }

    @Test
    public void updateAsset() {
    }

    @Test
    public void removeAsset() {
    }

    @Test
    public void findPagedLocations() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/locations")
                .getBody().prettyPrint();
    }

    @Test
    public void findLocations() {
    }

    @Test
    public void findLocationByCode() {
    }

    @Test
    public void saveLocation() {
    }

    @Test
    public void updateLocation() {
    }

    @Test
    public void removeLocation() {
    }

    @Test
    public void findPagedAssetCodes() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/asset-codes")
                .getBody().prettyPrint();
    }

    @Test
    public void findAssetCodes() {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .when()
                .get(ASSET_URL + "/asset-codes")
                .getBody().prettyPrint();
    }
}