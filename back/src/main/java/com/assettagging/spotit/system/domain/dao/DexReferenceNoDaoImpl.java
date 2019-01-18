package com.assettagging.spotit.system.domain.dao;

import com.assettagging.spotit.core.domain.DexMetaState;
import com.assettagging.spotit.system.domain.model.DexReferenceNo;
import com.assettagging.spotit.system.domain.model.DexReferenceNoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;

import com.assettagging.spotit.core.domain.GenericDaoSupport;

/**
 * @author canang technologies
 */
@SuppressWarnings({"unchecked"})
@Repository("referenceNoDao")
public class DexReferenceNoDaoImpl extends GenericDaoSupport<Long, DexReferenceNo> implements DexReferenceNoDao {

    public DexReferenceNoDaoImpl() {
        super(DexReferenceNoImpl.class);
    }

    @Override
    public DexReferenceNo findByCode(String code) {
        Query query = entityManager.createQuery("select s from DexReferenceNo s where " +
                "s.code = :code and  " +
                " s.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexReferenceNo) query.getSingleResult();
    }

    @Override
    public List<DexReferenceNo> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexReferenceNo s where " +
                "(upper(s.code) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setParameter("filter", filter);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexReferenceNo s where " +
                "(upper(s.code) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }
}
