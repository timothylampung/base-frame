package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.inventory.domain.model.DexPartCode;

import java.util.List;

public interface DexPartCodeDao extends GenericDao<Long, DexPartCode> {

    DexPartCode findPartCodeByCode(String code);

    List<DexPartCode> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
