package com.assettagging.spotit.asset.api.vo;

import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.asset.domain.model.DexLocation;

public class Asset {
    private String code;
    private String description;
    private DexLocation location;
    private DexAssetCode assetCode;
    private Long id;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getId() { return id;}
    public void setId(Long Id) { this.id = id;}

    public DexLocation getLocation() { return location; }
    public void setLocation(DexLocation location) { this.location = location; }

    public DexAssetCode getAssetCode() { return assetCode; }
    public void setAssetCode(DexAssetCode assetCode) { this.assetCode = assetCode; }
}
