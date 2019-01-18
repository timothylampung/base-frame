package com.assettagging.spotit.identity.domain.dao;

import com.assettagging.spotit.core.domain.DexMetaState;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;
import com.assettagging.spotit.identity.domain.model.DexActorType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author canang technologies
 */
@Repository("actorDao")
public class DexActorDaoImpl extends GenericDaoSupport<Long, DexActor> implements DexActorDao {

    public DexActorDaoImpl() {
        super(DexActorImpl.class);
    }

    @Override
    public DexActor findByCode(String code) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "a.code = :code");
        query.setParameter("code", code);
        return (DexActor) query.getSingleResult();
    }

    @Override
    public DexActor findByEmail(String email) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "a.email = :email");
        query.setParameter("email", email);
        return (DexActor) query.getSingleResult();
    }

    @Override
    public DexActor findByIdentityNo(String identityNo) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "a.identityNo = :identityNo");
        query.setParameter("identityNo", identityNo);
        return (DexActor) query.getSingleResult();
    }

    @Override
    public List<DexActor> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "(a.firstName like upper(:filter)" +
                "or a.lastName like upper(:filter)) " +
                "order by a.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return query.getResultList();
    }

    @Override
    public List<DexActor> find(DexActorType type, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "a.actorType = :actorType " +
                "order by a.name");
        query.setParameter("actorType", type);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public List<DexActor> find(String filter, DexActorType type, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select a from DexActor a where " +
                "(a.firsName like upper(:filter)" +
                "or a.lastName like upper(:filter)) " +
                "and a.actorType = :actorType " +
                "order by a.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("actorType", type);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(a) from DexActor a where " +
                "(upper(a.firstName) like upper(:filter)  " +
                "or upper(a.lastName) like upper(:filter))  " +
                "and a.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(String filter, DexActorType type) {
        Query query = entityManager.createQuery("select count(a) from DexActor a where " +
                "(upper(a.firstName) like upper(:filter)  " +
                "or upper(a.lastName) like upper(:filter))  " +
                "and a.actorType = :actorType " +
                "and a.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("actorType", type);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(DexActorType type) {
        Query query = entityManager.createQuery("select count(a) from DexActor a where " +
                "a.actorType = :actorType " +
                "and a.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("actorType", type);
        return ((Long) query.getSingleResult()).intValue();
    }
}
