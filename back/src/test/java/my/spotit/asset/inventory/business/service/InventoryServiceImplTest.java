package my.spotit.asset.inventory.business.service;

import my.spotit.AbstractTest;
import my.spotit.asset.asset.business.service.AssetService;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.asset.domain.model.DexLocationImpl;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.inventory.domain.model.*;
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
    private IdentityServiceHelper identityServiceHelper;
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private AssetService assetService;

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
            LOG.debug("TEST: " + component.getPartCode());
            LOG.debug("TEST: " + component.getAsset());
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

//        identityServiceHelper.changeUser("maula");
//
//        assetService.findAssetById(1L);
//
//        DexComponent component = new DexComponentImpl();
//        component.setCode("PC_70");
//        component.setDescription("PART CODE 70");
//
//        LOG.debug("----------------------prepared------------------------ {} ",partCode.getDescription() );
//        inventoryService.savePartCode(partCode);
//
//        entityManager.flush();
//
//        DexPartCode savedPartCode = inventoryService.findPartCodeByCode("PC_70");
//        LOG.debug("--------------------saved-------------------------- {} ",savedPartCode.getDescription() );
//        LOG.debug("--------------------saved-------------------------- {} ",savedPartCode.getId() );
//




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

        identityServiceHelper.changeUser("maula");

        DexPartCode partCode = new DexPartCodeImpl();
        partCode.setCode("PC_70");
        partCode.setDescription("PART CODE 70");

        LOG.debug("----------------------prepared------------------------ {} ",partCode.getDescription() );
        inventoryService.savePartCode(partCode);

        entityManager.flush();

        DexPartCode savedPartCode = inventoryService.findPartCodeByCode("PC_70");
        LOG.debug("--------------------saved-------------------------- {} ",savedPartCode.getDescription() );
        LOG.debug("--------------------saved-------------------------- {} ",savedPartCode.getId() );













    }

    @Test
    public void updatePartCode() {
    }

    @Test
    public void removePartCode() {
    }


    @Test
    public void findAllComponents() {

        List<DexComponent> components = inventoryService.findComponents();
        for (DexComponent component : components) {
            LOG.debug("TEST: " + component.getDescription());
        }
    }

    @Test
    public void findAllParts() {


        List<DexPart> parts = inventoryService.findParts();
        for (DexPart part : parts) {
            LOG.debug("TEST: " + part.getDescription());
        }
    }

    @Test
    public void findAllPartCodes() {
        List<DexPartCode> partCodes = inventoryService.findPartCodes();
        for (DexPartCode partCode : partCodes) {
            LOG.debug("TEST: " + partCode.getDescription());
        }
    }
}