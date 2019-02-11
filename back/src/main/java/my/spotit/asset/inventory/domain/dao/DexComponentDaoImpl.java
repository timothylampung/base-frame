package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.inventory.domain.model.DexComponent;
import my.spotit.asset.inventory.domain.model.DexComponentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("ComponentDao")
public class DexComponentDaoImpl extends GenericDaoSupport<Long, DexComponent> implements DexComponentDao{

    private static final Logger LOG = LoggerFactory.getLogger(DexComponentImpl.class);

    public DexComponentDaoImpl() { super(DexComponentImpl.class); }

    // todo: metadata
    @Override
    public DexComponent findComponentByCode(String code) {
        Query q = entityManager.createQuery("select e from DexComponent e where e.code =:code")
                .setParameter("code",code);
        return (DexComponent) q.getSingleResult();
    }

    // todo: metadata
    @Override
    public List<DexComponent> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexComponent s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexComponent>) query.getResultList();
    }


    // todo: metadata
    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexComponent s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

}
