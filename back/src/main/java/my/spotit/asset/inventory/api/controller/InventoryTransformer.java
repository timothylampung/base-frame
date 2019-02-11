package my.spotit.asset.inventory.api.controller;


import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.inventory.api.vo.Part;
import my.spotit.asset.inventory.api.vo.PartCode;
import my.spotit.asset.inventory.domain.model.DexComponent;
import my.spotit.asset.inventory.domain.model.DexPart;
import my.spotit.asset.inventory.domain.model.DexPartCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("inventoryTransformer")
public class InventoryTransformer {


   private CoreTransformer coreTransformer;

    @Autowired
    public InventoryTransformer(CoreTransformer coreTransformer) {
        this.coreTransformer = coreTransformer;
    }


    // =============================================================================================
    // PART CODE
    // =============================================================================================
    public PartCode toPartCodeVo(DexPartCode e) {
        if (e == null) return null;
        PartCode vo = new PartCode();
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setId(e.getId());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }


    public List<PartCode> toPartCodeVos(List<DexPartCode> e) {
        return e.stream().map(this::toPartCodeVo).collect(Collectors.toList());
    }


    // =============================================================================================
    // PART
    // =============================================================================================

    public Part toPartVo(DexPart e) {
        if (null == e) return null;
        Part vo = new Part();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setPartCode(toPartCodeVo(e.getPartCode()));
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Part> toPartVos(List<DexPart> e) {
        return e.stream().map((e1) -> toPartVo(e1)).collect(Collectors.toList());
    }

    // =============================================================================================
    // Component
    // =============================================================================================

    public my.spotit.asset.inventory.api.vo.Component toComponentVo(DexComponent e) {
        if (null == e) return null;
        my.spotit.asset.inventory.api.vo.Component vo = new my.spotit.asset.inventory.api.vo.Component();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<my.spotit.asset.inventory.api.vo.Component> toComponentVos(List<DexComponent> e) {
        return e.stream().map((e1) -> toComponentVo(e1)).collect(Collectors.toList());
    }
}
