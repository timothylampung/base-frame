package my.spotit.system.domain.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;

import my.spotit.core.domain.GenericDaoSupport;
import my.spotit.system.domain.model.DexModule;
import my.spotit.system.domain.model.DexSubModule;
import my.spotit.system.domain.model.DexSubModuleImpl;

/**
 */
@Repository("subModuleDao")
public class DexSubModuleDaoImpl extends GenericDaoSupport<Long, DexSubModule> implements DexSubModuleDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexSubModuleDaoImpl.class);

    public DexSubModuleDaoImpl() {
        super(DexSubModuleImpl.class);
    }

    @Override
    public DexSubModule findByCode(String code) {
        Query query = entityManager.createQuery("select c from DexSubModule c where c.code = :code");
        query.setParameter("code", code);
        return (DexSubModule) query.getSingleResult();
    }

    @Override
    public List<DexSubModule> find(DexModule module, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select c from DexSubModule c where " +
                "c.module = :module");
        query.setParameter("module", module);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexSubModule>) query.getResultList();
    }

    @Override
    public Integer count() {
        Query query = entityManager.createQuery("select count(c) from DexSubModule c");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(DexModule module) {
        Query query = entityManager.createQuery("select count(s) from DexSubModule s where " +
                "s.module = :module");
        query.setParameter("module", module);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexSubModule s where " +
                "upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Query query = entityManager.createQuery("select count(c) from DexSubModule c where " +
                "c.code = :code");
        query.setParameter("code", code);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }
}
