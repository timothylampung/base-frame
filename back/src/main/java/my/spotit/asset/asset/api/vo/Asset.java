package my.spotit.asset.asset.api.vo;


import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.api.MetaObject;

public class Asset extends MetaObject {
    private String code;
    private String description;
    private DexLocation location;
    private DexAssetCode assetCode;

    public Asset() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DexLocation getLocation() {
        return location;
    }

    public void setLocation(DexLocation location) {
        this.location = location;
    }

    public DexAssetCode getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(DexAssetCode assetCode) {
        this.assetCode = assetCode;
    }

}
