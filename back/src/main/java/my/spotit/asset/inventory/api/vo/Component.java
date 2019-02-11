package my.spotit.asset.inventory.api.vo;


import my.spotit.asset.asset.api.vo.Asset;
import my.spotit.asset.core.api.MetaObject;

public class Component extends MetaObject {
    private String code;
    private String description;
    private PartCode partCode;
    private Asset asset;
    private Long id;

    public Component() {
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



    public PartCode getPartCode() {
        return partCode;
    }

    public void setPartCode(PartCode partCode) {
        this.partCode = partCode;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
