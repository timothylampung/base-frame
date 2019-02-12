package my.spotit.asset.inventory.api.vo;

import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.api.MetaObject;

public class PartCode extends MetaObject {
    private String code;
    private String description;
    private DexLocation parent;
    private String address;
    private String name;

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

    public DexLocation getParent() {
        return parent;
    }

    public void setParent(DexLocation parent) {
        this.parent = parent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
