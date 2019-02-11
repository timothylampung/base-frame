package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.core.domain.GenericDaoSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalImpl;
import my.spotit.asset.identity.domain.model.DexPrincipalRole;
import my.spotit.asset.identity.domain.model.DexPrincipalType;
import my.spotit.asset.identity.domain.model.DexUser;


/**
 * @author canang technologies
 */
@Repository("principalDao")
public class DexPrincipalDaoImpl extends GenericDaoSupport<Long, DexPrincipal> implements DexPrincipalDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexPrincipalDaoImpl.class);

    public DexPrincipalDaoImpl() {
        super(DexPrincipalImpl.class);
    }

    @Override
    public List<DexPrincipal> findAllPrincipals() {
        List<DexPrincipal> results = new ArrayList<DexPrincipal>();
        Query query = entityManager.createQuery("select p from DexUser p order by p.name");
        results.addAll((List<DexPrincipal>) query.getResultList());

        Query queryGroup = entityManager.createQuery("select p from DexGroup p order by p.name ");
        results.addAll((List<DexPrincipal>) queryGroup.getResultList());

        return results;
    }

    @Override
    public List<DexPrincipal> find(String filter) {
        Query query = entityManager.createQuery("select p from DexPrincipal p where p.name like :filter order by p.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return query.getResultList();
    }

    @Override
    public List<DexPrincipal> find(String filter, DexPrincipalType type) {
        Query query = entityManager.createQuery("select p from DexPrincipal p where " +
                "p.name like :filter " +
                "and p.principalType = :principalType " +
                "order by p.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("principalType", type);
        return query.getResultList();
    }

    @Override
    public List<DexPrincipal> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select p from DexPrincipal p where " +
                "p.id in (" +
                "select u.id from DexUser u where " +
                "(upper(u.name) like upper(:filter)" +
                "or upper(u.realName) like upper(:filter))" +
                ") " +
                "or p.id in (select g.id from DexGroup g  where upper(g.name) like upper(:filter)) " +
                "order by p.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public void addRole(DexPrincipal principal, DexPrincipalRole role, DexUser user) {
        role.setPrincipal(principal);

        // prepare metadata
        DexMetadata metadata = new DexMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(DexMetaState.ACTIVE);
        role.setMetadata(metadata);
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(DexPrincipal principal, DexPrincipalRole role, DexUser user) {
        entityManager.remove(role);
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(u) from DexPrincipal u where " +
                "u.name like :filter ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return ((Long)query.getSingleResult()).intValue();
    }

    @Override
    public DexPrincipal findByName(String name) {
        Query query = entityManager.createQuery("select p from DexPrincipal p where " +
                "upper(p.name) = upper(:name) ");
        query.setParameter("name", name);
        return (DexPrincipal) query.getSingleResult();
    }
}
