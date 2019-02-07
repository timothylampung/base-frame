package com.assettagging.spotit.inventory.api.vo;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.api.MetaObject;

public class PartCode extends MetaObject {
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
