package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexFacilityManager;
import com.assettagging.spotit.identity.domain.model.DexFacilityManagerImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("facilityManagerDao")
public class DexFacilityManagerDaoImpl extends GenericDaoSupport<Long, DexFacilityManager> implements DexFacilityManagerDao {

    public DexFacilityManagerDaoImpl() { super(DexFacilityManagerImpl.class); }

    @Override
    public List<DexFacilityManager> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexFacilityManager v");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexFacilityManager>) query.getResultList();
    }

    @Override
    public DexFacilityManager findFacilityManagerByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select u from DexFaciltyManager u where " +
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
        Query query = entityManager.createQuery("select count(v) from DexFacilityManager v");
        return ((Long) query.getSingleResult()).intValue();
    }
}
