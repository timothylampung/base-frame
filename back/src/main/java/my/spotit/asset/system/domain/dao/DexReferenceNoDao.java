package my.spotit.asset.system.domain.dao;


import java.util.List;

import my.spotit.asset.system.domain.model.DexSequenceGenerator;
import my.spotit.asset.core.domain.GenericDao;

/**
 * @author canang technologies
 */
public interface DexReferenceNoDao extends GenericDao<Long, DexSequenceGenerator> {

    DexSequenceGenerator findByCode(String code);

    List<DexSequenceGenerator> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
