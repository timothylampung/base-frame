package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.inventory.domain.model.DexPart;

import java.util.List;

public interface DexPartDao extends GenericDao<Long, DexPart> {
    List<DexPart> findAllPart();
}
