package com.assettagging.spotit.system.domain.dao;


import java.util.List;

import com.assettagging.spotit.system.domain.model.DexModule;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.system.domain.model.DexSubModule;


/**
 */
public interface DexSubModuleDao extends GenericDao<Long, DexSubModule> {

    DexSubModule findByCode(String code);

    List<DexSubModule> find();

    List<DexSubModule> find(DexModule module, Integer offset, Integer limit);

    Integer count();

    Integer count(DexModule module);

    Integer count(String filter);

    boolean isExists(String code);
}
