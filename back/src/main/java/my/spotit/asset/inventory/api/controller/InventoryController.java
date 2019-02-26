package my.spotit.asset.inventory.api.controller;

import my.spotit.asset.DexConstants;

import my.spotit.asset.asset.api.controller.AssetTransformer;
import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.AssetResult;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.asset.api.vo.LocationResult;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.asset.domain.model.DexLocationImpl;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.inventory.business.service.InventoryService;
import my.spotit.asset.inventory.domain.model.*;
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

import static my.spotit.asset.DexConstants.LIMIT;

@Transactional
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

    private InventoryService inventoryService;
    private SystemService systemService;
    private InventoryTransformer inventoryTransformer;
    private AssetTransformer assetTransformer;
    private AssetService assetService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public InventoryController(InventoryService inventoryService, SystemService systemService,
                               InventoryTransformer inventoryTransformer,
                               AuthenticationManager authenticationManager,
                               AssetService assetService,
                               AssetTransformer assetTransformer) {
        this.inventoryService = inventoryService;
        this.systemService = systemService;
        this.inventoryTransformer = inventoryTransformer;
        this.assetTransformer = assetTransformer;

        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // PARTS
    //==============================================================================================

    @GetMapping(value = "/parts", params ={"page", "filter"})
    public ResponseEntity<PartResult> findPagedParts(@RequestParam Integer page, @RequestParam String filter) {
        LOG.debug("findPagedParts: {}", page);
        Integer count = inventoryService.countPart("%");
        List<DexPart> parts = inventoryService.findParts(filter, ((page - 1) * LIMIT), LIMIT);
        return new ResponseEntity<PartResult>(new PartResult(inventoryTransformer.toPartVos(parts), count), HttpStatus.OK);
    }


    @GetMapping(value = "/parts")
    public ResponseEntity<List<Part>> findParts() {
        return new ResponseEntity<List<Part>>(inventoryTransformer.toPartVos(
                inventoryService.findParts()), HttpStatus.OK);
    }


    @GetMapping(value = "/part/{code}")
    public ResponseEntity<Part> findPartByCode(@PathVariable String code) {
        return new ResponseEntity<Part>(inventoryTransformer.toPartVo(
                inventoryService.findPartByCode(code)), HttpStatus.OK);
    }

//    @GetMapping(value = "/assets/{location}") // one location have many assets no One asset
//    public ResponseEntity<List<Asset>> findAssetByLocation(@PathVariable DexLocation location) {
//        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
//                assetService.findAssetsByLocation(location)), HttpStatus.OK);
//    }

    @PostMapping(value = "/parts")
    public ResponseEntity<ApplicationSuccess> savePart(@RequestBody Part vo) {
        DexPart part = new DexPartImpl();
        part.setCode(vo.getCode());
        part.setDescription(vo.getDescription());
        part.setPartCode(inventoryService.findPartCodeById(vo.getPartCode().getId()));
        inventoryService.savePart(part);
        return new ResponseEntity<ApplicationSuccess>(new ApplicationSuccess("Success", ""), HttpStatus.OK);
    }


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

    @GetMapping(value = "/part-codes", params ={"page","filter"})
    public ResponseEntity<PartCodeResult> findPagedPartCodes(@RequestParam Integer page, @RequestParam(defaultValue = "%") String filter) {
        LOG.debug("findPagedPartCodes: {}", page);
        Integer count = inventoryService.countPartCode("%");
        List<DexPartCode> partCodes = inventoryService.findPartCodes(filter, ((page - 1) * LIMIT), LIMIT);
        return new ResponseEntity<PartCodeResult>(new PartCodeResult(inventoryTransformer.toPartCodeVos(partCodes), count), HttpStatus.OK);
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

    @GetMapping(value = "/components", params = {"page", "filter"})
    public ResponseEntity<ComponentResult> findPagedComponents(@RequestParam Integer page, @RequestParam String filter) {
        LOG.debug("findPagedComponents: {}", page);
        Integer count = inventoryService.countComponent("%");
        List<DexComponent> components = inventoryService.findComponents(filter, ((page - 1) * LIMIT), LIMIT);
        return new ResponseEntity<ComponentResult>(new ComponentResult(inventoryTransformer.toComponentVos(components), count), HttpStatus.OK);
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
        component.setAsset(assetService.findAssetById(vo.getAsset().getId()));
        component.setPartCode(inventoryService.findPartCodeById(vo.getPartCode().getId()));
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
