package com.assettagging.spotit.asset.api.controller;

import com.assettagging.spotit.AbstractApiTest;
import com.assettagging.spotit.TestUtil;
import com.assettagging.spotit.asset.api.vo.Asset;
import com.assettagging.spotit.asset.api.vo.AssetCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.junit.Assert.*;
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
}