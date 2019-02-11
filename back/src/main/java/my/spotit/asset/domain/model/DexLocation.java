package my.spotit.asset.domain.model;

import my.spotit.core.domain.DexMetaObject;

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
