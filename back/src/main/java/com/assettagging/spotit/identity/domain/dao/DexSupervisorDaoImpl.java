package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexSupervisor;
import com.assettagging.spotit.identity.domain.model.DexSupervisorImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("supervisorDao")
public class DexSupervisorDaoImpl extends GenericDaoSupport<Long, DexSupervisor> implements DexSupervisorDao {

    public DexSupervisorDaoImpl() { super(DexSupervisorImpl.class); }

    @Override
    public List<DexSupervisor> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexSupervisor v");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexSupervisor>) query.getResultList();
    }

    @Override
    public DexSupervisor findSupervisorByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select u from DexSupervisor u where " +
                "u.identityNo = :identityNo ");
        query.setParameter("identityNo", identityNo);
        return (DexSupervisor) query.getSingleResult();
    }

    @Override
    public DexSupervisor findSupervisorByCode(String code) {
        Query query = entityManager.createQuery("select u from DexSupervisor u where " +
                "u.code = :code ");
        query.setParameter("code", code);
        return (DexSupervisor) query.getSingleResult();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexSupervisor v");
        return ((Long) query.getSingleResult()).intValue();
    }

}
