package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexActorImpl;
import my.spotit.asset.identity.domain.model.DexActorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author canang technologies
 */
@Repository("actorDao")
public class DexActorDaoImpl extends GenericDaoSupport<Long, DexActor> implements DexActorDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexActorDaoImpl.class);

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
                "(a.name like upper(:filter)" +
                "or a.name like upper(:filter)) " +
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
                "(a.name like upper(:filter)" +
                "or a.name like upper(:filter)) " +
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
                "(upper(a.name) like upper(:filter)  " +
                "or upper(a.name) like upper(:filter))  " +
                "and a.metadata.state = :state");
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(String filter, DexActorType type) {
        Query query = entityManager.createQuery("select count(a) from DexActor a where " +
                "(upper(a.name) like upper(:filter)  " +
                "or upper(a.name) like upper(:filter))  " +
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
