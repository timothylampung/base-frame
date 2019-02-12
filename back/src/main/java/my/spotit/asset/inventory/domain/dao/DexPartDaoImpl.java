package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.inventory.domain.model.DexPart;
import my.spotit.asset.inventory.domain.model.DexPartImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("PartDao")
public class DexPartDaoImpl extends GenericDaoSupport<Long, DexPart> implements DexPartDao{

    private static final Logger LOG = LoggerFactory.getLogger(DexPartImpl.class);

    public DexPartDaoImpl() { super(DexPartImpl.class); }

    // todo: metadata
    @Override
    public DexPart findPartByCode(String code) {
        Query q = entityManager.createQuery("select e from DexPart e where e.code =:code")
                .setParameter("code",code);
        return (DexPart) q.getSingleResult();
    }

    // todo: metadata
    @Override
    public List<DexPart> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexPart s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexPart>) query.getResultList();
    }

    // todo: metadata
    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexPart s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

}
