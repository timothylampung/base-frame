package my.spotit.asset.integration.mobile.asset.api;

import my.spotit.asset.asset.api.controller.AssetTransformer;
import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.asset.api.vo.AssetCode;
import my.spotit.asset.asset.api.vo.Location;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/mobile/asset")
public class IntegrationAssetController {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationAssetController.class);

    private AssetService assetService;
    private AssetTransformer assetTransformer;

    @Autowired
    public IntegrationAssetController(AssetService assetService, AssetTransformer assetTransformer) {
        this.assetService = assetService;
        this.assetTransformer = assetTransformer;
    }

    @GetMapping(value = "/assets")
    public ResponseEntity<List<Asset>> findAssets() {
        return new ResponseEntity<List<Asset>>(assetTransformer.toAssetVos(
                assetService.findAssets("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> findLocations() {
        return new ResponseEntity<List<Location>>(assetTransformer.toLocationVos(
                assetService.findLocations()), HttpStatus.OK);
    }

    @GetMapping(value = "/asset-codes")
    public ResponseEntity<List<AssetCode>> findAssetCodes() {
        return new ResponseEntity<List<AssetCode>>(assetTransformer.toAssetCodeVos(
                assetService.findAssetCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

}
