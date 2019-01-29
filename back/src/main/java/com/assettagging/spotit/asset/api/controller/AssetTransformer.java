package com.assettagging.spotit.asset.api.controller;

import com.assettagging.spotit.asset.api.vo.Asset;
import com.assettagging.spotit.asset.api.vo.AssetCode;
import com.assettagging.spotit.asset.api.vo.Location;
import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("assetTransformer")
public class AssetTransformer {

    // =============================================================================================
    // ASSET
    // =============================================================================================

    public Asset toAssetVo(DexAsset e) {
        if (null == e) return null;
        Asset vo = new Asset();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setLocation(e.getLocation());
        vo.setAssetCode(e.getAssetCode());
        return vo;
    }

    public List<Asset> toAssetVos(List<DexAsset> e) {
        List<Asset> vos = e.stream().map((e1) -> toAssetVo(e1)).collect(Collectors.toList());
        return vos;
    }

    // =============================================================================================
    // LOCATION
    // =============================================================================================

    public Location toLocationVo(DexLocation e) {
        if (null == e) return null;
        Location vo = new Location();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        return vo;
    }

    public List<Location> toLocationVos(List<DexLocation> e) {
        List<Location> vos = e.stream().map((e1) -> toLocationVo(e1)).collect(Collectors.toList());
        return vos;
    }
}
