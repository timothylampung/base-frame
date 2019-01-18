package com.assettagging.spotit.common.domain.dao;


import com.assettagging.spotit.common.domain.model.DexPositionCode;
import com.assettagging.spotit.core.domain.GenericDao;

import java.util.List;

public interface DexPositionCodeDao extends GenericDao<Long, DexPositionCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    DexPositionCode findByCode(String code);

    List<DexPositionCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);

}
