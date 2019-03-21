package my.spotit.asset.identity.domain.model;

import my.spotit.asset.asset.domain.model.DexAssetCategory;
import my.spotit.asset.core.domain.DexMetaObject;

import java.util.Set;

public interface DexTeam extends DexMetaObject {
    String getName();

    void setName(String name);

    Set<DexAssetCategory> getExpertise();

    void setExpertise(Set<DexAssetCategory> expertise);
}
