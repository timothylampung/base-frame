package my.spotit.asset.identity.domain.model;

import my.spotit.asset.asset.domain.model.DexAssetCategory;
import my.spotit.asset.core.domain.DexMetaObject;

public interface DexTeamExpertise extends DexMetaObject {
    DexTeam getTeam();

    void setTeam(DexTeam team);

    DexAssetCategory getExpertise();

    void setExpertise(DexAssetCategory expertise);
}
