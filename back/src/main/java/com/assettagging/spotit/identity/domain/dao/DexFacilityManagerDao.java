package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexFacilityManager;
import com.assettagging.spotit.identity.domain.model.DexStaff;

import java.util.List;

public interface DexFacilityManagerDao extends GenericDao<Long, DexFacilityManager> {

    List<DexFacilityManager> find(String filter, Integer offset, Integer limit);

    DexFacilityManager findByCode(String code);
}
