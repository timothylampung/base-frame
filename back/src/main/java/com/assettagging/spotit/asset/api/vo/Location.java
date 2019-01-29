package com.assettagging.spotit.asset.api.vo;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.MetaObject;

public class Location extends MetaObject {
    private String code;
    private String description;
    private Long id;
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

    public Long getId() { return id; }
    public void setId(Long Id) { this.id = id;}

    public DexLocation getParent() { return parent; }
    public void setParent(DexLocation parent) { this.parent = parent; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
