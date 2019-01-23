package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

public interface DexLocation  extends DexMetaObject{
    DexMetadata getMetadata();

    void setMetadata(DexMetadata metadata);

    Long getId();

    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
