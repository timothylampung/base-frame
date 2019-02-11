package my.spotit.asset.maintenance.business.service;

import my.spotit.AbstractTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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