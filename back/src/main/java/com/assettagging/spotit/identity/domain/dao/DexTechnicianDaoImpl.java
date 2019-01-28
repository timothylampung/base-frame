package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexTechnician;
import com.assettagging.spotit.identity.domain.model.DexTechnicianImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("technicianDao")
public class DexTechnicianDaoImpl extends GenericDaoSupport<Long, DexTechnician> implements DexTechnicianDao {

    public DexTechnicianDaoImpl() { super(DexTechnicianImpl.class); }

    @Override
    public List<DexTechnician> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexTechnician v");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexTechnician>) query.getResultList();
    }

    @Override
    public DexTechnician findTechnicianByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select u from DexTechnician u where " +
                "u.identityNo = :identityNo ");
        query.setParameter("identityNo", identityNo);
        return (DexTechnician) query.getSingleResult();
    }

    @Override
    public DexTechnician findTechnicianByCode(String code) {
        Query query = entityManager.createQuery("select u from DexTechnician u where " +
                "u.code = :code ");
        query.setParameter("code", code);
        return (DexTechnician) query.getSingleResult();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexTechnician v");
        return ((Long) query.getSingleResult()).intValue();
    }

}
