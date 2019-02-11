package my.spotit.common.domain.dao;

import my.spotit.common.domain.model.DexGradeCode;
import my.spotit.common.domain.model.DexGradeCodeImpl;
import my.spotit.core.domain.DexMetaState;
import my.spotit.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("GradeCodeDao")
public class DexGradeCodeDaoImpl extends GenericDaoSupport<Long, DexGradeCode> implements DexGradeCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexGradeCodeDaoImpl.class);

    public DexGradeCodeDaoImpl() {
        super(DexGradeCodeImpl.class);
    }

    @Override
    public DexGradeCode findByCode(String code) {
        Query query = entityManager.createQuery("select s from DexGradeCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexGradeCode) query.getSingleResult();
    }

    @Override
    public List<DexGradeCode> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexGradeCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexGradeCode>) query.getResultList();
    }
    
    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexGradeCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Query query = entityManager.createQuery("select count(s) from DexGradeCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }
}
