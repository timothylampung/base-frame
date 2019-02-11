package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

public interface DexAssetCode extends DexMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);
}
