package my.spotit.asset.asset.api.vo;

import my.spotit.asset.core.api.MetaObject;

import java.math.BigDecimal;

public class Asset extends MetaObject {
    private String code;
    private BigDecimal cost;
    private Long quantity;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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
