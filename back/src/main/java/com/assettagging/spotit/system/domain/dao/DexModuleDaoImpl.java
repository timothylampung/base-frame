package com.assettagging.spotit.system.domain.dao;

import com.assettagging.spotit.system.domain.model.DexModule;
import com.assettagging.spotit.system.domain.model.DexModuleImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.system.domain.model.DexSubModule;


/**
 */
@Repository("moduleDao")
public class DexModuleDaoImpl extends GenericDaoSupport<Long, DexModule> implements DexModuleDao {

    public DexModuleDaoImpl() {
        super(DexModuleImpl.class);
    }

    @Override
    public DexModule findByCode(String code) {
        Query query = entityManager.createQuery("select c from DexModule c where c.code = :code");
        query.setParameter("code", code);
        return (DexModule) query.getSingleResult();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexModule s where " +
                "upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Query query = entityManager.createQuery("select count(c) from DexModule c where " +
                "c.code = :code");
        query.setParameter("code", code);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isSubModuleExists(String code) {
        Query query = entityManager.createQuery("select count(c) from DexSubModule c where " +
                "c.code = :code");
        query.setParameter("code", code);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void addSubModule(DexModule module, DexSubModule subModule, DexUser user) {
        subModule.setModule(module);
        entityManager.persist(subModule);
    }

    @Override
    public void updateSubModule(DexModule module, DexSubModule subModule, DexUser user) {
        subModule.setModule(module);
        entityManager.persist(subModule);
    }

    @Override
    public void removeSubModule(DexModule module, DexSubModule subModule, DexUser user) {
        entityManager.remove(subModule);
    }
}
