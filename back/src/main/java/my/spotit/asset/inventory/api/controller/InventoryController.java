package my.spotit.asset.inventory.api.controller;

import my.spotit.asset.DexConstants;

import my.spotit.asset.inventory.business.service.InventoryService;
import my.spotit.asset.system.business.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import my.spotit.asset.inventory.api.vo.Component;
import my.spotit.asset.inventory.api.vo.ComponentResult;
import my.spotit.asset.inventory.api.vo.Part;
import my.spotit.asset.inventory.api.vo.PartCode;
import my.spotit.asset.inventory.api.vo.PartCodeResult;
import my.spotit.asset.inventory.api.vo.PartResult;

@Transactional
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

    private InventoryService inventoryService;
    private SystemService systemService;
    private InventoryTransformer inventoryTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public InventoryController(InventoryService inventoryService, SystemService systemService,
                               InventoryTransformer inventoryTransformer,
                               AuthenticationManager authenticationManager) {
        this.inventoryService = inventoryService;
        this.systemService = systemService;
        this.inventoryTransformer = inventoryTransformer;
        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // PARTS
    //==============================================================================================

    @GetMapping(value = "/parts", params = {"page"})
    public ResponseEntity<PartResult> findPagedParts(@RequestParam Integer page) {
        LOG.debug("findPagedParts: {}", page);
        Integer count = inventoryService.countPart("%");
        List<Part> parts = inventoryTransformer.toPartVos(
                inventoryService.findParts("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<PartResult>(new PartResult(parts, count), HttpStatus.OK);
    }


    @GetMapping(value = "/parts")
    public ResponseEntity<List<Part>> findParts() {
        return new ResponseEntity<List<Part>>(inventoryTransformer.toPartVos(
                inventoryService.findAllParts()), HttpStatus.OK);
    }

//
//    @GetMapping(value = "/asset/{code}")
//    public ResponseEntity<Asset> findAssetByAssetCode(@PathVariable String code) {
//        return new ResponseEntity<Asset>(assetTransformer.toAssetVo(
//                assetService.findAssetByCode(code)), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/assets/{location}") // one location have many assets no One asset
//    public ResponseEntity<List<Asset>> findAssetByLocation(@PathVariable DexLocation location) {
//        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
//                assetService.findAssetsByLocation(location)), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/asset")
//    public ResponseEntity<String> saveAsset(@RequestBody Asset vo) {
//        DexAsset asset = new DexAssetImpl();
//        asset.setCode(vo.getCode());
//        asset.setDescription(vo.getDescription());
//        assetService.saveAsset(asset);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/asset/{code}")
//    public ResponseEntity<String> updateAsset(@PathVariable String code, @RequestBody Asset vo) {
//        DexAsset asset = assetService.findAssetById(vo.getId());
//        asset.setDescription(vo.getDescription());
//        assetService.updateAsset(asset);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/asset/{code}")
//    public ResponseEntity<String> removeAsset(@PathVariable String code) {
//        DexAsset asset = assetService.findAssetByCode(code);
//        assetService.removeAsset(asset);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }

    //==============================================================================================
    // PART CODE
    //==============================================================================================

    @GetMapping(value = "/part-codes", params = {"page"})
    public ResponseEntity<PartCodeResult> findPagedPartCodes(@RequestParam Integer page) {
        LOG.debug("findPagedPartCodes: {}", page);
        Integer count = inventoryService.countPartCode("%");
        List<PartCode> partCodes = inventoryTransformer.toPartCodeVos(
                inventoryService.findPartCodes("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<PartCodeResult>(new PartCodeResult(partCodes, count), HttpStatus.OK);
    }

    @GetMapping(value = "/part-codes")
    public ResponseEntity<List<PartCode>> findPartCodes() {
        return new ResponseEntity<List<PartCode>>(inventoryTransformer.toPartCodeVos(
                inventoryService.findPartCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    //==============================================================================================
    // COMPONENT
    //==============================================================================================

    @GetMapping(value = "/components", params = {"page"})
    public ResponseEntity<ComponentResult> findPagedComponents(@RequestParam Integer page) {
        LOG.debug("findPagedComponents: {}", page);
        Integer count = inventoryService.countComponent("%");
        List<Component> components = inventoryTransformer.toComponentVos(
                inventoryService.findComponents("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<ComponentResult>(new ComponentResult(components, count), HttpStatus.OK);
    }

    @GetMapping(value = "/components")
    public ResponseEntity<List<Component>> findComponents() {
        return new ResponseEntity<List<Component>>(inventoryTransformer.toComponentVos(
                inventoryService.findComponents("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

//    @GetMapping(value = "/location/{code}")
//    public ResponseEntity<Location> findLocationByCode(@PathVariable String code) {
//        return new ResponseEntity<Location>(assetTransformer.toLocationVo(
//                assetService.findLocationByCode(code)), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/location")
//    public ResponseEntity<String> saveLocation(@RequestBody Location vo) {
//        DexLocation location = new DexLocationImpl();
//        location.setCode(vo.getCode());
//        location.setDescription(vo.getDescription());
//        assetService.saveLocation(location);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/location/{code}")
//    public ResponseEntity<String> updateLocation(@PathVariable String code, @RequestBody Location vo) {
//        DexLocation location = assetService.findLocationById(vo.getId());
//        location.setDescription(vo.getDescription());
//        assetService.updateLocation(location);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/location/{code}")
//    public ResponseEntity<String> removeLocation(@PathVariable String code) {
//        DexLocation location = assetService.findLocationByCode(code);
//        assetService.removeLocation(location);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
}
