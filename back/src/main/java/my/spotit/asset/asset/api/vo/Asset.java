package my.spotit.asset.asset.api.vo;


import my.spotit.asset.core.api.MetaObject;

public class Asset extends MetaObject {
    private String code;
    private String description;
    private Location location;
    private AssetCode assetCode;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AssetCode getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(AssetCode assetCode) {
        this.assetCode = assetCode;
    }

}
