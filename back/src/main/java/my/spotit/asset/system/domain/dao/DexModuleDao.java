package my.spotit.asset.system.domain.dao;


import my.spotit.asset.system.domain.model.DexModule;
import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.system.domain.model.DexSubModule;


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
