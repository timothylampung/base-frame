package my.spotit.asset.domain.model;

import my.spotit.core.domain.DexMetaObject;

public interface DexAssetCode extends DexMetaObject{
    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
