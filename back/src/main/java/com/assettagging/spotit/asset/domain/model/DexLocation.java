package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexLocation  {
    Long getId();

    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
