package com.assettagging.spotit.inventory.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.inventory.domain.model.DexPartCode;

import java.util.List;

public interface DexPartCodeDao extends GenericDao<Long, DexPartCode> {

    List<DexPartCode> findAllPartCode();
}
