package com.assettagging.spotit.system.domain.dao;


import com.assettagging.spotit.system.domain.model.DexModule;
import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.system.domain.model.DexSubModule;


/**
 */
public interface DexModuleDao extends GenericDao<Long, DexModule> {

    DexModule findByCode(String code);

    Integer count(String filter);

    boolean isExists(String code);

    boolean isSubModuleExists(String code);

    void addSubModule(DexModule module, DexSubModule subModule, DexUser user);

    void updateSubModule(DexModule module, DexSubModule subModule, DexUser user);

    void removeSubModule(DexModule module, DexSubModule subModule, DexUser user);
}
