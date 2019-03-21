package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;
import my.spotit.asset.identity.domain.model.DexTeam;

import java.util.Set;

public interface DexAssetCategory  extends DexMetaObject {
    String getCode();

    void setCode(String code);

    void setDescription(String description);

    DexAsset getAsset();

    void setAsset(DexAsset asset);

    Set<DexTeam> getTeams();

    void setTeams(Set<DexTeam> teams);
}
