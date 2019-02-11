package my.spotit.asset.asset.domain.dao;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.core.domain.GenericDao;

import java.util.List;

public interface DexAssetDao extends GenericDao<Long, DexAsset> {

    DexAsset findByCode(String code);

    List<DexAsset> find(String filter, Integer offset, Integer limit);

    List<DexAsset> findByLocation(DexLocation location);

    Integer count(String filter);

    Integer count(DexLocation location);

}
