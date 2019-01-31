package com.assettagging.spotit.maintenance.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.maintenance.domain.dao.DexMaintenanceRequestDaoImplTest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.Assert.*;

public class MaintenanceServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceServiceImplTest.class);


//    @Autowired
//    private MaintenanceService maintenanceService;
//
//    @Autowired
//    private AssetService assetService;
//
//    @Test
//    public void find() {
//
//        List<DexMaintenanceRequest> maintenanceRequests = maintenanceService.find();
//
//    }
//
//    @Test
//    @Rollback(false)
//    public void submitMaintenanceRequest() {
//        DexLocation location = new DexLocationImpl();
//        location.setAddress("TEST ADDRESS");
//        location.setCode("Code123123213");
//        location.setDescription("DESC");
//        location.setName("TEST ADDRESS NAME");
//        assetService.saveLocation(location);
//        DexLocation savedLocation = assetService.findByCode("Code123123213");
//
//        DexMaintenanceRequest request = new DexMaintenanceRequestImpl();
//        request.setDescription("description");
//        maintenanceService.submitMaintenanceRequest(request, savedLocation);
//        List<DexMaintenanceRequest> requests = maintenanceService.find();
//        for(DexMaintenanceRequest maintenanceRequest : requests){
//                    LOG.debug("----------------------maintenance request location code : ------------------------ {} ",maintenanceRequest.getLocation().getCode());
//                    LOG.debug("----------------------maintenance request requestor email :------------------------ {} ",maintenanceRequest.getRequester().getEmail());
//
//        }
//
//
//    }
}