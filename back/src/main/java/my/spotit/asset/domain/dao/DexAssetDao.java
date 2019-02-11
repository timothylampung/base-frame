package my.spotit.asset.domain.dao;

import my.spotit.asset.domain.model.DexAsset;
import my.spotit.asset.domain.model.DexLocation;
import my.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexAssetDao extends GenericDao<Long, DexAsset> {

    DexAsset findByCode(String code);

    List<DexAsset> findByLocation(DexLocation location);

    Integer count(String filter);

}
