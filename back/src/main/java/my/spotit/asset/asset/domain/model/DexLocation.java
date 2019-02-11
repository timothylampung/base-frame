package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

import java.util.List;

public interface DexLocation  extends DexMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    DexLocation getParent();

    void setParent(DexLocation parent);

    List<DexAsset> getAssets();

    void setAssets(List<DexAsset> assets);
}
