package my.spotit.asset.asset.api.vo;

import my.spotit.asset.core.api.MetaObject;

public class Asset extends MetaObject {
    private String code;
    private String cost;
    private String quantity;
    private String description;
    private String category;
    private Location location;
    private AssetCode assetCode;

    // transient
    private String summary;

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

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
