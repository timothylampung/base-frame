package my.spotit.asset.inventory.business.service;

import my.spotit.asset.inventory.domain.model.DexComponent;
import my.spotit.asset.inventory.domain.model.DexPart;
import my.spotit.asset.inventory.domain.model.DexPartCode;

import java.util.List;

public interface InventoryService {

    //==============================================================================================
    // COMPONENT
    //==============================================================================================

    List<DexComponent> findAllComponents();

    DexComponent findComponentById(Long id);

    DexComponent findComponentByCode(String code);

    List<DexComponent> findComponents(String filter, Integer offset, Integer limit);

    Integer countComponent();

    Integer countComponent(String filter);

    void saveComponent(DexComponent Component);

    void updateComponent(DexComponent Component);

    void removeComponent(DexComponent Component);



//==============================================================================================
    // PART
    //==============================================================================================




    List<DexPart> findAllParts();

    DexPart findPartByCode(String code);

    List<DexPart> findParts(String filter, Integer offset, Integer limit);

    Integer countPart();

    Integer countPart(String filter);

    void savePart(DexPart Activity);

    void updatePart(DexPart Activity);

    void removePart(DexPart Activity);

//==============================================================================================
    // PART CODES
    //==============================================================================================



    List<DexPartCode> findAllPartCodes();

    DexPartCode findPartCodeByCode(String code);

    List<DexPartCode> findPartCodes(String filter, Integer offset, Integer limit);

    Integer countPartCode();

    Integer countPartCode(String filter);

    void savePartCode(DexPartCode Activity);

    void updatePartCode(DexPartCode Activity);

    void removePartCode(DexPartCode Activity);

}
