package my.spotit.asset.common.domain.dao;

import my.spotit.asset.common.domain.model.DexPositionCode;
import my.spotit.asset.common.domain.model.DexPositionCodeImpl;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("PositionCodeDao")
public class DexPositionCodeDaoImpl extends GenericDaoSupport<Long, DexPositionCode> implements DexPositionCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexPositionCodeDaoImpl.class);

    public DexPositionCodeDaoImpl() {
        super(DexPositionCodeImpl.class);
    }

    @Override
    public DexPositionCode findByCode(String code) {
        Query query = entityManager.createQuery("select s from DexPositionCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexPositionCode) query.getSingleResult();
    }

    @Override
    public List<DexPositionCode> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexPositionCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexPositionCode>) query.getResultList();
    }
    
    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexPositionCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Query query = entityManager.createQuery("select count(s) from DexPositionCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }
}
