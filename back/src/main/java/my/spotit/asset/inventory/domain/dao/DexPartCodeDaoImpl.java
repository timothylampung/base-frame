package my.spotit.asset.inventory.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.inventory.domain.model.DexPartCode;
import my.spotit.asset.inventory.domain.model.DexPartCodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("PartCodeDao")
public class DexPartCodeDaoImpl extends GenericDaoSupport<Long, DexPartCode> implements DexPartCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexPartCodeImpl.class);

    public DexPartCodeDaoImpl() { super(DexPartCodeImpl.class); }

    @Override
    public List<DexPartCode> findAllPartCodes() {
        Query q = entityManager.createQuery("select e from DexPartCode e");
        return q.getResultList();
    }

    @Override
    public DexPartCode findPartCodeByCode(String code) {
        Query q = entityManager.createQuery("select e from DexPartCode e where e.code =:code")
                .setParameter("code",code);
        return (DexPartCode) q.getSingleResult();
    }

    @Override
    public List<DexPartCode> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexPartCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexPartCode>) query.getResultList();
    }


    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexPartCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

}
