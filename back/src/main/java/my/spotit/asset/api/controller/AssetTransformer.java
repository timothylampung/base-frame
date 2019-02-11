package my.spotit.asset.api.controller;

import my.spotit.asset.api.vo.Asset;
import my.spotit.asset.api.vo.AssetCode;
import my.spotit.asset.api.vo.Location;
import my.spotit.asset.domain.model.DexAsset;
import my.spotit.asset.domain.model.DexAssetCode;
import my.spotit.asset.domain.model.DexLocation;
import my.spotit.core.api.controller.CoreTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("assetTransformer")
public class AssetTransformer {


   private CoreTransformer coreTransformer;

    @Autowired
    public AssetTransformer(CoreTransformer coreTransformer) {
        this.coreTransformer = coreTransformer;
    }


    // =============================================================================================
    // ASSET CODE
    // =============================================================================================
    public AssetCode toAssetCodeVo(DexAssetCode e) {
        if (e == null) return null;
        AssetCode vo = new AssetCode();
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setId(e.getId());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }


    public List<AssetCode> toAssetCodeVos(List<DexAssetCode> e) {
        return e.stream().map(this::toAssetCodeVo).collect(Collectors.toList());
    }


    // =============================================================================================
    // ASSET
    // =============================================================================================

    public Asset toAssetVo(DexAsset e) {
        if (null == e) return null;
        Asset vo = new Asset();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setDescription(e.getDescription());
        vo.setLocation(toLocationVo(e.getLocation()));
        vo.setAssetCode(toAssetCodeVo(e.getAssetCode()));
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Asset> toAssetVos(List<DexAsset> e) {
        return e.stream().map((e1) -> toAssetVo(e1)).collect(Collectors.toList());
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
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Location> toLocationVos(List<DexLocation> e) {
        return e.stream().map((e1) -> toLocationVo(e1)).collect(Collectors.toList());
    }
}
