package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;
import com.assettagging.spotit.core.domain.DexMetadata;

import java.util.List;

public interface DexLocation  extends DexMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    List<DexAsset> getAssets();

    void setAssets(List<DexAsset> assets);

    DexLocation getParent();

    void setParent(DexLocation parent);

    String getAddress();

    void setAddress(String address);

    String getName();

    void setName(String name);
}
