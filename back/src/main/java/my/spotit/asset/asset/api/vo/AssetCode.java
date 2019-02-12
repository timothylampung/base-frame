package my.spotit.asset.asset.api.vo;

import my.spotit.asset.core.api.MetaObject;

public class AssetCode extends MetaObject {
    private String code;
    private String description;

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

}
