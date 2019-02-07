package com.assettagging.spotit.Inventory.api.controller;

import com.assettagging.spotit.AbstractApiTest;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

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
}