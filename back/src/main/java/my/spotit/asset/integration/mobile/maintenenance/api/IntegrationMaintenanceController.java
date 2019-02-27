package my.spotit.asset.integration.mobile.maintenenance.api;


import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import my.spotit.asset.maintenance.api.controller.MaintenanceRequestTransformer;
import my.spotit.asset.maintenance.api.vo.MaintenanceRequest;
import my.spotit.asset.maintenance.business.service.MaintenanceRequestService;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequest;
import my.spotit.asset.maintenance.domain.model.DexMaintenanceRequestImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Transactional
@RestController
@RequestMapping("/api/mobile/maintenance-request")
public class IntegrationMaintenanceController {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationMaintenanceController.class);
    private MaintenanceRequestService maintenanceRequestService;
    private MobileSecurityService mobileSecurityService;
    private AssetService assetService;
    private MaintenanceRequestTransformer maintenanceRequestTransformer;

    @Autowired
    public IntegrationMaintenanceController(MaintenanceRequestService maintenanceRequestService, MobileSecurityService mobileSecurityService,
                                            AssetService assetService, MaintenanceRequestTransformer maintenanceRequestTransformer) {
        this.maintenanceRequestService = maintenanceRequestService;
        this.mobileSecurityService = mobileSecurityService;
        this.assetService = assetService;
        this.maintenanceRequestTransformer = maintenanceRequestTransformer;
    }

    @PostMapping(value = "/start-task")
    public ResponseEntity<?> startMaintenanceRequestTask(@RequestParam String username, @RequestParam String deviceId, @RequestBody MaintenanceRequest vo) throws Exception {

        LOG.debug("Username : {} , DeviceId : {}", username, deviceId);

        DexUser currentUser = mobileSecurityService.authenticate(username, deviceId);
        DexMaintenanceRequest maintenanceRequest = new DexMaintenanceRequestImpl();
        DexAsset asset = assetService.findAssetByCode(vo.getAsset().getCode());
        DexActor requester = currentUser.getActor();

        DexLocation location = assetService.findLocationByCode(vo.getLocation().getCode());
        maintenanceRequest.setRequester(requester);
        maintenanceRequest.setAsset(asset);
        maintenanceRequest.setDescription(vo.getDescription());
        maintenanceRequest.setRemark(vo.getRemark());
        maintenanceRequest.setLocation(location);
        maintenanceRequest.setRequestedDate(new Date());

        maintenanceRequestService.startMaintenanceRequestTask(maintenanceRequest);
        LOG.debug("end task");
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceRequestTransformer.toMaintenanceRequestVo(maintenanceRequest));
    }

}
