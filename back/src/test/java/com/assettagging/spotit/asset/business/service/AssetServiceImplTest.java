package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.AbstractTest;

import my.spotit.asset.business.service.AssetService;
import my.spotit.asset.domain.model.DexLocation;
import my.spotit.asset.domain.model.DexLocationImpl;
import my.spotit.helper.IdentityServiceHelper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.List;

public class AssetServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(AssetServiceImplTest.class);

    @Autowired
    private IdentityServiceHelper identityServiceHelper;
    @Autowired
    private AssetService assetService;
    @Autowired
    private EntityManager entityManager;


    @Test
    public void findAllLocations() {
        List<DexLocation> locations = assetService.findAllLocations();
        for (DexLocation location : locations) {
            LOG.debug("TEST: " + location.getDescription());
        }
    }

    @Test
    public void findLocationById() {
    }

    @Test
    public void findLocationByCode() {
        DexLocation location = assetService.findLocationByCode("CODE7959");
        LOG.debug("SINA LAH" + location.getDescription());
    }

    @Test
    public void findLocations() {
    }

    @Test
    public void countLocation() {
    }

    @Test
    @Rollback(false)
    public void saveLocation() {

        DexLocation location = new DexLocationImpl();
        location.setCode("CODE44");
        location.setDescription("THIS CODE IS FAKE!");
        location.setName("Armband");
        location.setAddress("BigROom");

        LOG.debug("----------------------prepared------------------------ {} ",location.getDescription() );
        assetService.saveLocation(location);

        entityManager.flush();

        DexLocation savedLocation = assetService.findLocationByCode("CODE44");
        LOG.debug("--------------------saved-------------------------- {} ",savedLocation.getAddress() );


    }

    @Test
    public void updateLocation() {
    }

    @Test
    public void removeLocation() {
    }

    @Test
    public void findAssetById() {
    }

    @Test
    public void findAssetByAssetCode() {
    }

    @Test
    public void findAssetsByLocation() {
    }

    @Test
    public void findAllAssets() {
    }

    @Test
    public void countAsset() {
    }

    @Test
    public void countAsset1() {
    }

    @Test
    public void saveAsset() {
    }

    @Test
    public void updateAsset() {
    }

    @Test
    public void removeAsset() {
    }
}