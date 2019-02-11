package my.spotit.common.domain.dao;


import my.spotit.common.domain.model.DexPositionCode;
import my.spotit.core.domain.GenericDao;

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
