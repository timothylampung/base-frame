package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.inventory.domain.model.DexPart;

import java.util.List;

public interface DexPartDao extends GenericDao<Long, DexPart> {

    DexPart findPartByCode(String code);

    List<DexPart> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

}
