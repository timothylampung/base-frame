package my.spotit.asset.inventory.api.controller;

import my.spotit.asset.DexConstants;

import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.asset.domain.model.DexLocationImpl;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.inventory.business.service.InventoryService;
import my.spotit.asset.inventory.domain.model.DexComponent;
import my.spotit.asset.inventory.domain.model.DexComponentImpl;
import my.spotit.asset.inventory.domain.model.DexPartCode;
import my.spotit.asset.inventory.domain.model.DexPartCodeImpl;
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
                inventoryService.findParts()), HttpStatus.OK);
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


    @GetMapping(value = "/part-codes/{code}")
    public ResponseEntity<PartCode> findPartCodeByCode(@PathVariable String code) {
        return new ResponseEntity<PartCode>(inventoryTransformer.toPartCodeVo(
                inventoryService.findPartCodeByCode(code)), HttpStatus.OK);
    }



    @PostMapping(value = "/part-codes")
    public ResponseEntity<ApplicationSuccess> savePartCode(@RequestBody PartCode vo) {
        DexPartCode partCode = new DexPartCodeImpl();
        partCode.setCode(vo.getCode());
        partCode.setDescription(vo.getDescription());
        inventoryService.savePartCode(partCode);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

//    @PutMapping(value = "/part-codes/{code}")
//    public ResponseEntity<String> updatePartCode(@PathVariable String code, @RequestBody PartCode vo) {
//        DexPartCode partCode = inventoryService.findLocationById(vo.getId());
//        location.setDescription(vo.getDescription());
//        assetService.updateLocation(location);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/locations/{code}")
//    public ResponseEntity<String> removeLocation(@PathVariable String code) {
//        DexLocation location = assetService.findLocationByCode(code);
//        assetService.removeLocation(location);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//





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

    @GetMapping(value = "/components/{code}")
    public ResponseEntity<Component> findComponentByCode(@PathVariable String code) {
        return new ResponseEntity<Component>(inventoryTransformer.toComponentVo(
                inventoryService.findComponentByCode(code)), HttpStatus.OK);
    }
    @PostMapping(value = "/components")
    public ResponseEntity<ApplicationSuccess> saveComponent(@RequestBody Component vo) {
        DexComponent component = new DexComponentImpl();
        component.setCode(vo.getCode());
        component.setDescription(vo.getDescription());
        inventoryService.saveComponent(component);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }

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
