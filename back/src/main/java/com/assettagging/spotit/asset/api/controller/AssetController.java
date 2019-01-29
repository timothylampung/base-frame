package com.assettagging.spotit.asset.api.controller;

import com.assettagging.spotit.DexConstants;
import com.assettagging.spotit.asset.api.vo.Asset;
import com.assettagging.spotit.asset.api.vo.AssetResult;
import com.assettagging.spotit.asset.api.vo.Location;
import com.assettagging.spotit.asset.api.vo.LocationResult;
import com.assettagging.spotit.asset.business.service.AssetService;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.system.business.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private static final Logger LOG = LoggerFactory.getLogger(AssetController.class);

    private AssetService assetService;
    private SystemService systemService;
    private AssetTransformer assetTransformer;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AssetController(AssetService assetService, SystemService systemService,
                           AssetTransformer assetTransformer,
                           AuthenticationManager authenticationManager) {
        this.assetService = assetService;
        this.systemService = systemService;
        this.assetTransformer = assetTransformer;
        this.authenticationManager = authenticationManager;
    }

    //==============================================================================================
    // ASSET
    //==============================================================================================

    @GetMapping(value = "/assets", params = {"page"})
    public ResponseEntity<AssetResult> findPagedAssets(@RequestParam Integer page) {
        LOG.debug("findPagedAssets: {}", page);
        Integer count = assetService.countAsset("%");
        List<Asset> assets = assetTransformer.toAssetVos(
                assetService.findAllAssets("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<AssetResult>(new AssetResult(assets, count), HttpStatus.OK);
    }

    @GetMapping(value = "/assets")
    public ResponseEntity<List<Asset>> findAssets() {
        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
                assetService.findAllAssets("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/asset/{code}")
    public ResponseEntity<Asset> findAssetByAssetCode(@PathVariable String code) {
        return new ResponseEntity<Asset>(assetTransformer.toAssetVo(
                assetService.findAssetByAssetCode(code)), HttpStatus.OK);
    }

    @GetMapping(value = "/asset/{location}")
    public ResponseEntity<Asset> findAssetByLocation(@PathVariable DexLocation location) {
        return new ResponseEntity<Asset>(assetTransformer.toAssetVo(
                assetService.findAssetByLocation(location)), HttpStatus.OK);
    }

    @PostMapping(value = "/asset")
    public ResponseEntity<String> saveAsset(@RequestBody Asset vo) {
        DexAsset asset = new DexAssetImpl();
        asset.setCode(vo.getCode());
        asset.setDescription(vo.getDescription());
        assetService.saveAsset(asset);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/asset/{code}")
    public ResponseEntity<String> updateAsset(@PathVariable String code, @RequestBody Asset vo) {
        DexAsset asset = assetService.findAssetById(vo.getId());
        asset.setDescription(vo.getDescription());
        assetService.updateAsset(asset);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/asset/{code}")
    public ResponseEntity<String> removeAsset(@PathVariable String code) {
        DexAsset asset = assetService.findAssetByAssetCode(code);
        assetService.removeAsset(asset);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    //==============================================================================================
    // LOCATION
    //==============================================================================================

    @GetMapping(value = "/locations", params = {"page"})
    public ResponseEntity<LocationResult> findPagedLocations(@RequestParam Integer page) {
        LOG.debug("findPagedLocations: {}", page);
        Integer count = assetService.countLocation("%");
        List<Location> locations = assetTransformer.toLocationVos(
                assetService.findAllLocations("%", ((page - 1) * DexConstants.LIMIT), DexConstants.LIMIT));
        return new ResponseEntity<LocationResult>(new LocationResult(locations, count), HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> findLocations() {
        return new ResponseEntity<List<Location>>(assetTransformer.toLocationVos(
                assetService.findAllLocations("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/location/{code}")
    public ResponseEntity<Location> findLocationByCode(@PathVariable String code) {
        return new ResponseEntity<Location>(assetTransformer.toLocationVo(
                assetService.findLocationByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/location")
    public ResponseEntity<String> saveLocation(@RequestBody Location vo) {
        DexLocation location = new DexLocationImpl();
        location.setCode(vo.getCode());
        location.setDescription(vo.getDescription());
        assetService.saveLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/location/{code}")
    public ResponseEntity<String> updateLocation(@PathVariable String code, @RequestBody Location vo) {
        DexLocation location = assetService.findLocationById(vo.getId());
        location.setDescription(vo.getDescription());
        assetService.updateLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/location/{code}")
    public ResponseEntity<String> removeLocation(@PathVariable String code) {
        DexLocation location = assetService.findLocationByCode(code);
        assetService.removeLocation(location);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
