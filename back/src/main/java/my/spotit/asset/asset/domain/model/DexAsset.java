package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

import java.math.BigDecimal;
import java.util.List;

public interface DexAsset  extends  DexMetaObject  {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    BigDecimal getCost();

    void setCost(BigDecimal cost);

    Long getQuantity();

    void setQuantity(Long quantity);

    String getCategory();

    void setCategory(String category);

    List<DexAssetCategory> getAssetCategory();

    void setAssetCategory(List<DexAssetCategory> assetCategory);

    DexAssetCode getAssetCode();

    void setAssetCode(DexAssetCode assetCode);

    DexLocation getLocation();

    void setLocation(DexLocation location);

}
