package my.spotit.asset.inventory.domain.model;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.core.domain.DexMetaObject;

public interface DexComponent extends DexMetaObject {

    String getDescription();

    void setDescription(String description);

    DexPartCode getPartCode();

    void setPartCode(DexPartCode partCode);

    DexAsset getAsset();

    void setAsset(DexAsset asset);

    String getCode();

    void setCode(String code);
}
