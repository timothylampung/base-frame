package com.assettagging.spotit.asset.domain.dao;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexLocationDao extends GenericDao <Long, DexLocation> {


  DexLocation findByCode(String code);

  Integer count(String filter);

  List<DexLocation> find(String filter, Integer offset, Integer limit);
}
