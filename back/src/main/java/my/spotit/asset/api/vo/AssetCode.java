package my.spotit.asset.api.vo;

import my.spotit.core.api.MetaObject;

public class AssetCode extends MetaObject {
    private String code;
    private String description;
    private Long id;

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

    public Long getId() { return id; }
    public void setId(Long Id) { this.id = id;}
}
