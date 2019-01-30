package com.assettagging.spotit.system.domain.dao;

import com.assettagging.spotit.core.domain.DexMetaState;
import com.assettagging.spotit.system.domain.model.DexSequenceGenerator;
import com.assettagging.spotit.system.domain.model.DexSequenceGeneratorImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;

import com.assettagging.spotit.core.domain.GenericDaoSupport;

/**
 * @author canang technologies
 */
@SuppressWarnings({"unchecked"})
@Repository("referenceNoDao")
public class DexReferenceNoDaoImpl extends GenericDaoSupport<Long, DexSequenceGenerator> implements DexReferenceNoDao {

    public DexReferenceNoDaoImpl() {
        super(DexSequenceGeneratorImpl.class);
    }

    @Override
    public DexSequenceGenerator findByCode(String code) {
        Query query = entityManager.createQuery("select s from DexSequenceGenerator s where " +
                "s.code = :code and  " +
                " s.metadata.state = :state");
        query.setParameter("code", code);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexSequenceGenerator) query.getSingleResult();
    }

    @Override
    public List<DexSequenceGenerator> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexSequenceGenerator s where " +
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
        Query query = entityManager.createQuery("select count(s) from DexSequenceGenerator s where " +
                "(upper(s.code) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }
}
