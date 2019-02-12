package my.spotit.asset.asset.api.controller;

import my.spotit.asset.asset.api.vo.*;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.*;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.system.business.service.SystemService;
import my.spotit.asset.workflow.business.service.WorkflowService;
import my.spotit.asset.workorder.api.vo.WorkOrder;
import my.spotit.asset.workorder.api.vo.WorkOrderRecordSummaryResult;
import my.spotit.asset.workorder.api.vo.WorkOrderResult;
import my.spotit.asset.workorder.api.vo.WorkOrderTask;
import my.spotit.asset.workorder.api.vo.WorkOrderTaskSummaryResult;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexActivityImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;

import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static my.spotit.asset.DexConstants.LIMIT;
import static my.spotit.asset.DexConstants.WORK_ORDER_REFERENCE_NO;

@Transactional
@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private static final Logger LOG = LoggerFactory.getLogger(AssetController.class);

    private AssetService assetService;
    private SystemService systemService;
    private WorkflowService workflowService;
    private AssetTransformer assetTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AssetController(AssetService assetService, SystemService systemService,
                           WorkflowService workflowService,
                           AssetTransformer assetTransformer,
                           AuthenticationManager authenticationManager) {
        this.assetService = assetService;
        this.systemService = systemService;
        this.workflowService = workflowService;
        this.assetTransformer = assetTransformer;
        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // ASSET
    //==============================================================================================

    @GetMapping(value = "/assets", params = {"page, filter"})
    public ResponseEntity<AssetResult> findPagedAssets(@RequestParam Integer page, @RequestParam String filter) {
        LOG.debug("findPagedAssets: {}", page);
        Integer count = assetService.countAsset(filter);
        List<DexAsset> assets = assetService.findAssets(filter, ((page - 1) * LIMIT), LIMIT);
        return new ResponseEntity<AssetResult>(new AssetResult(assetTransformer.toAssetVos(assets), count), HttpStatus.OK);
    }

    @GetMapping(value = "/assets")
    public ResponseEntity<List<Asset>> findAssets() {
        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
                assetService.findAssets("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/asset/{code}")
    public ResponseEntity<Asset> findAssetByAssetCode(@PathVariable String code) {
        return new ResponseEntity<Asset>(assetTransformer.toAssetVo(
                assetService.findAssetByCode(code)), HttpStatus.OK);
    }

    // todo: maybe /locations/{code}/assets?
    @GetMapping(value = "/assets/{location}")
    public ResponseEntity<List<Asset>> findAssetByLocation(@PathVariable DexLocation location) {
        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
                assetService.findAssetsByLocation(location)), HttpStatus.OK);
    }

    @PostMapping(value = "/assets")
    public ResponseEntity<ApplicationSuccess> saveAsset(@RequestBody Asset vo) {
        DexAsset asset = new DexAssetImpl();
        asset.setCode(vo.getCode());
        asset.setDescription(vo.getDescription());
        assetService.saveAsset(asset);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

    @PutMapping(value = "/assets/{code}")
    public ResponseEntity<ApplicationSuccess> updateAsset(@PathVariable String code, @RequestBody Asset vo) {
        DexAsset asset = assetService.findAssetById(vo.getId());
        asset.setDescription(vo.getDescription());
        assetService.updateAsset(asset);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

    @DeleteMapping(value = "/assets/{code}")
    public ResponseEntity<ApplicationSuccess> removeAsset(@PathVariable String code) {
        DexAsset asset = assetService.findAssetByCode(code);
        assetService.removeAsset(asset);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

    //==============================================================================================
    // LOCATION
    //==============================================================================================

    @GetMapping(value = "/locations", params = {"page", "filter"})
    public ResponseEntity<LocationResult> findPagedLocations(@RequestParam Integer page, @RequestParam String filter) {
        LOG.debug("findPagedLocations: {}", page);
        Integer count = assetService.countLocation(filter);
        List<DexLocation> locations = assetService.findLocations(filter, ((page - 1) * LIMIT), LIMIT);
        return new ResponseEntity<LocationResult>(new LocationResult(assetTransformer.toLocationVos(locations), count), HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> findLocations() {
        return new ResponseEntity<List<Location>>(assetTransformer.toLocationVos(
                assetService.findLocations()), HttpStatus.OK);
    }

    @GetMapping(value = "/locations/{code}")
    public ResponseEntity<Location> findLocationByCode(@PathVariable String code) {
        return new ResponseEntity<Location>(assetTransformer.toLocationVo(
                assetService.findLocationByCode(code)), HttpStatus.OK);
    }

    @GetMapping(value = "/locations/{code}/assets")
    public ResponseEntity<List<Asset>> findAssetsByLocation(@PathVariable String code) {
        DexLocation location = assetService.findLocationByCode(code);
        List<DexAsset> assets = assetService.findAssetsByLocation(location);
        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(assets), HttpStatus.OK);
    }

    @PostMapping(value = "/locations")
    public ResponseEntity<String> saveLocation(@RequestBody Location vo) {
        DexLocation location = new DexLocationImpl();
        location.setCode(vo.getCode());
        location.setDescription(vo.getDescription());
        assetService.saveLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/locations/{code}")
    public ResponseEntity<String> updateLocation(@PathVariable String code, @RequestBody Location vo) {
        DexLocation location = assetService.findLocationById(vo.getId());
        location.setDescription(vo.getDescription());
        assetService.updateLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/locations/{code}")
    public ResponseEntity<String> removeLocation(@PathVariable String code) {
        DexLocation location = assetService.findLocationByCode(code);
        assetService.removeLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //==============================================================================================
    // ASSET-CODES
    //==============================================================================================

    @GetMapping(value = "/asset-codes", params = {"page"})
    public ResponseEntity<AssetCodeResult> findPagedAssetCodes(@RequestParam Integer page) {
        LOG.debug("findPagedAssetCodes: {}", page);
        Integer count = assetService.countAsset("%");
        List<AssetCode> assetCodes = assetTransformer.toAssetCodeVos(
                assetService.findAssetCodes("%", ((page - 1) * LIMIT), LIMIT));
        return new ResponseEntity<AssetCodeResult>(new AssetCodeResult(assetCodes, count), HttpStatus.OK);
    }

    @GetMapping(value = "/asset-codes")
    public ResponseEntity<List<AssetCode>> findAssetCodes() {
        return new ResponseEntity<List<AssetCode>>(assetTransformer.toAssetCodeVos(
                assetService.findAssetCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/asset-code/{code}")
    public ResponseEntity<AssetCode> findAssetCodeByCode(@PathVariable String code) {
        return new ResponseEntity<AssetCode>(assetTransformer.toAssetCodeVo(
                assetService.findAssetCodeByCode(code)), HttpStatus.OK);
    }


    @PostMapping(value = "/asset-codes")
    public ResponseEntity<ApplicationSuccess> saveAssetCode(@RequestBody AssetCode vo) {
        DexAssetCode assetCode = new DexAssetCodeImpl();
        assetCode.setCode(vo.getCode());
        assetCode.setDescription(vo.getDescription());
        assetService.saveAssetCode(assetCode);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

    @PutMapping(value = "/asset-codes/{code}")
    public ResponseEntity<ApplicationSuccess> updateAssetCode(@PathVariable String code, @RequestBody AssetCode vo) {
        DexAssetCode assetCode = assetService.findAssetCodeById(vo.getId());
        assetCode.setDescription(vo.getDescription());
        assetService.updateAssetCode(assetCode);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

    @DeleteMapping(value = "/asset-codes/{code}")
    public ResponseEntity<ApplicationSuccess> removeAssetCode(@PathVariable String code) {
        DexAssetCode assetCode = assetService.findAssetCodeByCode(code);
        assetService.removeAssetCode(assetCode);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }
}
