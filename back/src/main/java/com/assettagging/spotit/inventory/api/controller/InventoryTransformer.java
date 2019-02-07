package com.assettagging.spotit.inventory.api.controller;

import com.assettagging.spotit.asset.api.vo.Asset;
import com.assettagging.spotit.asset.api.vo.Location;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.controller.CoreTransformer;
import com.assettagging.spotit.inventory.api.vo.Part;
import com.assettagging.spotit.inventory.api.vo.PartCode;
import com.assettagging.spotit.inventory.domain.model.DexPart;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;
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

//    // =============================================================================================
//    // LOCATION
//    // =============================================================================================
//
//    public Location toLocationVo(DexLocation e) {
//        if (null == e) return null;
//        Location vo = new Location();
//        vo.setId(e.getId());
//        vo.setCode(e.getCode());
//        vo.setDescription(e.getDescription());
//        coreTransformer.toMetadata(e, vo);
//        return vo;
//    }
//
//    public List<Location> toLocationVos(List<DexLocation> e) {
//        return e.stream().map((e1) -> toLocationVo(e1)).collect(Collectors.toList());
//    }
}
