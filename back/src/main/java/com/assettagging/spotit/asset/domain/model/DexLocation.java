package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

public interface DexLocation  extends DexMetaObject{


    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    DexLocation getParent();

    void setParent(DexLocation parent);

    String getAddress();

    void setAddress(String address);

    String getName();

    void setName(String name);
}
