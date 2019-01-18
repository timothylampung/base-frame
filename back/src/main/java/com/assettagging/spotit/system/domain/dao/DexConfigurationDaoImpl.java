package com.assettagging.spotit.system.domain.dao;

import com.assettagging.spotit.core.domain.DexMetaState;
import com.assettagging.spotit.system.domain.model.DexConfiguration;
import com.assettagging.spotit.system.domain.model.DexConfigurationImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;

import com.assettagging.spotit.core.domain.GenericDaoSupport;

/**
 */
@Repository("configurationDao")
public class DexConfigurationDaoImpl extends GenericDaoSupport<Long, DexConfiguration> implements DexConfigurationDao {


    public DexConfigurationDaoImpl() {
        super(DexConfigurationImpl.class);
    }

    public DexConfigurationDaoImpl(Class clazz) {
        super(clazz);
    }

    @Override
    public DexConfiguration findByKey(String key) {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "s.key = :key and  " +
                " s.metadata.state = :state");
        query.setParameter("key", key);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexConfiguration) query.getSingleResult();
    }

    @Override
    public List<DexConfiguration> findByPrefix(String prefix) {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "upper(s.key) like upper(:prefix)  " +
                "and s.metadata.state = :state");
        query.setParameter("prefix", prefix + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return query.getResultList();
    }

    @Override
    public List<DexConfiguration> find() {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "s.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        return query.getResultList();
    }

    @Override
    public List<DexConfiguration> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "(upper(s.key) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<DexConfiguration> find(String filter) {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "upper(s.key) like upper(:filter)  " +
                "and s.metadata.state = :state");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return query.getResultList();
    }

    @Override
    public List<DexConfiguration> findInverse(String filter) {
        Query query = entityManager.createQuery("select s from DexConfiguration s where " +
                "upper(s.key) not like upper(:filter)  " +
                "and s.metadata.state = :state");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexConfiguration s where " +
                "(upper(s.key) like upper(:filter)  " +
                "or upper(s.description) like upper(:filter))  " +
                "and s.metadata.state = :state");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

}
