package my.spotit.asset.common.domain.dao;


import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.common.domain.model.DexGradeCode;

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
