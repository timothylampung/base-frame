package com.assettagging.spotit.asset.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.common.business.service.CommonService;
import com.assettagging.spotit.common.business.service.CommonServiceImplTest;
import com.assettagging.spotit.common.domain.model.DexGradeCode;
import com.assettagging.spotit.common.domain.model.DexGradeCodeImpl;
import com.assettagging.spotit.helper.IdentityServiceHelper;
import com.assettagging.spotit.maintenance.business.service.MaintenanceRequestServiceImplTest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.smartcardio.ATR;
import java.util.List;

import static org.junit.Assert.*;

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
        List<DexLocation> locations = assetService.findAllLocations("%",0,999);
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