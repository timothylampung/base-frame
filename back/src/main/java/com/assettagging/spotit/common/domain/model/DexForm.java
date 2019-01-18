package com.assettagging.spotit.common.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexForm extends DexMetaObject {
    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getRefNo();

    void setRefNo(String refNo);
}
