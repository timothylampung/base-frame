package my.spotit.asset.inventory.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexPartCode extends DexMetaObject {
    String getDescription();

    void setDescription(String description);

    String getCode();

    void setCode(String code);
}
