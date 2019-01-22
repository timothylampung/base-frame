package com.assettagging.spotit.system.domain.model;


import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 */
public interface DexModule extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getCanonicalCode();

    void setCanonicalCode(String canonicalCode);

    String getDescription();

    void setDescription(String description);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    boolean isEnabled();

    void setEnabled(boolean enabled);
}