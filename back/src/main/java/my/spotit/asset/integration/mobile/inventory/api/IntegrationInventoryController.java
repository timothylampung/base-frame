package my.spotit.asset.integration.mobile.inventory.api;

import my.spotit.asset.integration.mobile.maintenenance.api.IntegrationMaintenanceController;
import my.spotit.asset.integration.mobile.security.MobileSecurityService;
import my.spotit.asset.inventory.api.controller.InventoryTransformer;
import my.spotit.asset.inventory.api.vo.Part;
import my.spotit.asset.inventory.api.vo.PartCode;
import my.spotit.asset.inventory.business.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/mobile/inventory")
public class IntegrationInventoryController {

    private static final Logger LOG = LoggerFactory.getLogger(IntegrationInventoryController.class);
    private InventoryService inventoryService;
    private InventoryTransformer inventoryTransformer;

    public IntegrationInventoryController(InventoryService inventoryService, InventoryTransformer inventoryTransformer) {
        this.inventoryService = inventoryService;
        this.inventoryTransformer = inventoryTransformer;
    }


    @GetMapping(value = "/parts")
    public ResponseEntity<List<Part>> findParts() {
        return new ResponseEntity<List<Part>>(inventoryTransformer.toPartVos(
                inventoryService.findParts()), HttpStatus.OK);
    }

    @GetMapping(value = "/part-codes")
    public ResponseEntity<List<PartCode>> findPartCodes() {
        return new ResponseEntity<>(inventoryTransformer.toPartCodeVos(
                inventoryService.findPartCodes()), HttpStatus.OK);
    }

}
