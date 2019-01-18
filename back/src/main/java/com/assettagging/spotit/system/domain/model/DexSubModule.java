package com.assettagging.spotit.system.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 */
public interface DexSubModule extends DexMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    DexModule getModule();

    void setModule(DexModule module);
}
