package com.assettagging.spotit.common.domain.model;


import com.assettagging.spotit.core.domain.DexMetaObject;

public interface DexGradeCode extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
