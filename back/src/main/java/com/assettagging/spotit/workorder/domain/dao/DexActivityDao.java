package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.workorder.domain.model.DexActivity;

import java.util.List;

public interface DexActivityDao extends GenericDao<Long, DexActivity> {

    DexActivity findActivityByCode(String code);
    List<DexActivity> findAllActivitys();
}
