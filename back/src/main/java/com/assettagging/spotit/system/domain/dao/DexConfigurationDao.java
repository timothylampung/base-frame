package com.assettagging.spotit.system.domain.dao;


import java.util.List;

import com.assettagging.spotit.system.domain.model.DexConfiguration;
import com.assettagging.spotit.core.domain.GenericDao;


/**
 */
public interface DexConfigurationDao extends GenericDao<Long, DexConfiguration> {

    DexConfiguration findByKey(String key);

    List<DexConfiguration> findByPrefix(String prefix);

    List<DexConfiguration> find(String filter, Integer offset, Integer limit);

    List<DexConfiguration> find(String filter);

    List<DexConfiguration> findInverse(String filter);

    Integer count(String filter);

}
