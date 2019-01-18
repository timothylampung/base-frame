package com.assettagging.spotit.common.domain.dao;


import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.common.domain.model.DexGradeCode;

import java.util.List;

public interface DexGradeCodeDao extends GenericDao<Long, DexGradeCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    DexGradeCode findByCode(String code);

    List<DexGradeCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);

}
