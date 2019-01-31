package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexAssetDao extends GenericDao<Long, DexAsset> {

    DexAsset findByCode(String code);

    List<DexAsset> findByLocation(DexLocation location);

    Integer count(String filter);

}
