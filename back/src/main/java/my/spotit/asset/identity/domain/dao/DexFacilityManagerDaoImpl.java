package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexFacilityManager;
import my.spotit.asset.identity.domain.model.DexFacilityManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("facilityManagerDao")
public class DexFacilityManagerDaoImpl extends GenericDaoSupport<Long, DexFacilityManager> implements DexFacilityManagerDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexFacilityManagerDaoImpl.class);

    public DexFacilityManagerDaoImpl() {
        super(DexFacilityManagerImpl.class);
    }

    @Override
    public List<DexFacilityManager> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexFacilityManager v where " +
                "(upper(v.name) like upper(:filter)" +
                "or upper(v.code) like upper(:filter)" +
                ")" +
                "order by v.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexFacilityManager>) query.getResultList();
    }

    @Override
    public DexFacilityManager findFacilityManagerByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select u from DexFacilityManager u where " +
                "u.identityNo = :identityNo ");
        query.setParameter("identityNo", identityNo);
        return (DexFacilityManager) query.getSingleResult();
    }

    @Override
    public DexFacilityManager findFacilityManagerByCode(String code) {
        Query query = entityManager.createQuery("select u from DexFacilityManager u where " +
                "u.code = :code ");
        query.setParameter("code", code);
        return (DexFacilityManager) query.getSingleResult();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexFacilityManager v where " +
                "(upper(v.name) like upper(:filter)" +
                "or upper(v.code) like upper(:filter))");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);

        return ((Long) query.getSingleResult()).intValue();
    }
}