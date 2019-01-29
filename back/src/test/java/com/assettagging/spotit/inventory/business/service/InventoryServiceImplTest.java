package com.assettagging.spotit.Inventory.business.service;

import com.assettagging.spotit.AbstractTest;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.inventory.business.service.InventoryService;
import com.assettagging.spotit.inventory.domain.dao.DexPartDao;
import com.assettagging.spotit.inventory.domain.model.DexComponent;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
public class InventoryServiceImplTest extends AbstractTest {

    private static final Logger LOG = LoggerFactory.getLogger(InventoryServiceImplTest.class);


    @Autowired
    private IdentityService identityService;
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private EntityManager entityManager;




    @Test
    public void findComponentById() {
        long id = 1;
        DexComponent componentById = inventoryService.findComponentById(id);

        LOG.debug("TEST: " + componentById.getDescription());
    }

    @Test
    public void findComponentByCode() {
        String code ="CO01";
        DexComponent componentByCode = inventoryService.findComponentByCode(code);

        LOG.debug("TEST: " + componentByCode.getDescription());
    }

    @Test
    public void findComponents() {
        List<DexComponent> components = inventoryService.findComponents("%",0,999);
        for (DexComponent component : components) {
            LOG.debug("TEST: " + component.getDescription());
        }
    }


    @Test
    public void countComponent() {
    }

    @Test
    public void countComponent1() {
    }

    @Test
    public void saveComponent() {
    }

    @Test
    public void updateComponent() {
    }

    @Test
    public void removeComponent() {
    }

    @Test
    public void findPartByCode() {
        String code ="PA01";
        DexPart partByCode = inventoryService.findPartByCode(code);

        LOG.debug("TEST: " + partByCode.getDescription());
    }

    @Test
    public void findParts() {
        List<DexPart> parts = inventoryService.findParts("%",0,999);
        for (DexPart part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }

    }

    @Test
    public void countPart() {
    }

    @Test
    public void countPart1() {
    }

    @Test
    public void savePart() {
    }

    @Test
    public void updatePart() {
    }

    @Test
    public void removePart() {
    }

    @Test
    public void findPartCodeByCode() {
    }

    @Test
    public void findPartCodes() {
    }

    @Test
    public void countPartCode() {
    }

    @Test
    public void countPartCode1() {
    }

    @Test
    public void savePartCode() {
    }

    @Test
    public void updatePartCode() {
    }

    @Test
    public void removePartCode() {
    }


    @Test
    public void findAllComponents() {

        List<DexComponent> components = inventoryService.findAllComponents();
        for (DexComponent component : components) {
            LOG.debug("TEST: " + component.getDescription());
        }
    }

    @Test
    public void findAllParts() {


        List<DexPart> parts = inventoryService.findAllParts();
        for (DexPart part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }
    }

    @Test
    public void findAllPartCodes() {
        List<DexPartCode> partCodes = inventoryService.findAllPartCodes();
        for (DexPartCode partCode : partCodes) {
            LOG.debug("TEST: " + partCode.getDescription());
        }
    }
}