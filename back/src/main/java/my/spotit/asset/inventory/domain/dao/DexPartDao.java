package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.inventory.domain.model.DexPart;

import java.util.List;

public interface DexPartDao extends GenericDao<Long, DexPart> {
    List<DexPart> findAllParts();
    DexPart findPartByCode(String code);
    List<DexPart> find(String filter, Integer offset, Integer limit);


    //HELPER

    Integer count(String filter);

}
