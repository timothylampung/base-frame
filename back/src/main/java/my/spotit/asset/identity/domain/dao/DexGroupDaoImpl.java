package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;

import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexGroupImpl;
import my.spotit.asset.identity.domain.model.DexGroupMember;
import my.spotit.asset.identity.domain.model.DexGroupMemberImpl;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalType;
import my.spotit.asset.identity.domain.model.DexUser;


/**
 * @author canang technologies
 */
@Repository("groupDao")
public class DexGroupDaoImpl extends GenericDaoSupport<Long, DexGroup> implements DexGroupDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexGroupDaoImpl.class);

    public DexGroupDaoImpl() {
        super(DexGroupImpl.class);
    }
    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<DexGroup> findAll() {
        Query query = entityManager.createQuery("select g from DexGroup g order by g.name");
        return (List<DexGroup>) query.getResultList();
    }

    @Override
    public DexGroup findByName(String name) {
        Query query = entityManager.createQuery("select g from DexGroup g where g.name = :name");
        query.setParameter("name", name);
        List resultList = query.getResultList();
        return (resultList.size() == 0) ? null : (DexGroup) resultList.get(0);
    }


    @Override
    public List<DexGroup> findImmediate(DexPrincipal principal) {
        Query query = entityManager.createQuery("select gm.group from DexGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setParameter("principal", principal);
        return (List<DexGroup>) query.getResultList();
    }


    @Override
    public List<DexGroup> findImmediate(DexPrincipal principal, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select gm.group from DexGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setParameter("principal", principal);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexGroup>) query.getResultList();
    }

    @Override
    public Set<DexGroup> findEffectiveAsNative(DexPrincipal principal) {
        Set<DexGroup> groups = new HashSet<DexGroup>();
        Query nativeQuery = entityManager.createNativeQuery("WITH RECURSIVE " +
                "Q AS " +
                "( SELECT  GROUP_ID, PRINCIPAL_ID " +
                "    FROM    DEX_GROP_MMBR " +
                "    WHERE   PRINCIPAL_ID = " + principal.getId() +
                "    UNION ALL " +
                "    SELECT  GM.GROUP_ID, GM.PRINCIPAL_ID " +
                "    FROM    DEX_GROP_MMBR GM " +
                "    JOIN    Q " +
                "    ON      GM.PRINCIPAL_ID = Q.GROUP_ID " +
                ") " +
                "SELECT  GROUP_ID FROM  Q");
        nativeQuery.setParameter("GROUP_ID", LongType.INSTANCE);
        List<Long> results = (List<Long>) nativeQuery.getResultList();
        for (Long result : results) {
            groups.add(findById(result));
        }
        return groups;
    }

    @Override
    public List<DexGroup> findAvailableGroups(DexUser user) {
        Query query = entityManager.createQuery("select p from DexGroup p where " +
                "p not in (select gm.group from DexGroupMember gm where gm.principal = :user) " +
                "and p <> :user " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("user", user);
        return (List<DexGroup>) query.getResultList();
    }

    @Override
    public List<DexGroupMember> findMembersAsGroupMember(DexGroup group) {
        Query query = entityManager.createQuery("select gm from DexGroupMember gm where " +
                "gm.group = :group ");
        query.setParameter("group", group);
        return (List<DexGroupMember>) query.getResultList();
    }

    @Override
    public List<DexPrincipal> findAvailableMembers(DexGroup group) {
        Query query = entityManager.createQuery("select p from DexPrincipal p where " +
                "p not in (select gm.principal from DexGroupMember gm where gm.group = :group) " +
                "and p <> :group " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("group", group);
        return (List<DexPrincipal>) query.getResultList();
    }

    @Override
    public List<DexPrincipal> findMembers(DexGroup group, DexPrincipalType principalType) {
        Query query = entityManager.createQuery("select gm.group from DexGroupMember gm inner join gm.principal where " +
                "gm.group = :group " +
                "and gm.principal.principalType= :principalType " +
                "and gm.principal.metadata.state = :state ");
        query.setParameter("group", group);
        query.setParameter("principalType", principalType);
        return (List<DexPrincipal>) query.getResultList();
    }

    @Override
    public List<DexPrincipal> findMembers(DexGroup group) {
        Query query = entityManager.createQuery("select gm.principal from DexGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setParameter("group", group);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexPrincipal>) query.getResultList();
    }

    @Override
    public List<DexPrincipal> findMembers(DexGroup group, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select gm.principal from DexGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setParameter("group", group);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexPrincipal>) query.getResultList();
    }

    @Override
    public List<DexGroup> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select distinct g from DexGroup g where " +
                "g.name like upper(:filter) ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexGroup>) query.getResultList();
    }

    @Override
    public List<DexGroup> findMemberships(DexPrincipal principal) {
        Query query = entityManager.createQuery("select distinct gm.group from DexGroupMember gm where " +
                "gm.principal = :principal ");
        query.setParameter("principal", principal);
        return (List<DexGroup>) query.getResultList();
    }

    @Override
    public Integer count() {
        Query query = entityManager.createQuery("select count(g) from DexGroup g");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(g) from DexGroup g where " +
                "g.name like upper(:filter)");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countMember(DexGroup group) {
        Query query = entityManager.createQuery("select count(gm.principal) from DexGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state");
        query.setParameter("group", group);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean isExists(String name) {
        Query query = entityManager.createQuery("select count(g) from DexGroup g where " +
                "g.name = :name");
        query.setParameter("name", name);
        return ((Long) query.getSingleResult()).intValue() >= 1;
    }

    @Override
    public boolean hasMembership(DexGroup group, DexPrincipal principal) {
        Query query = entityManager.createQuery("select count(gm) from DexGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal = :principal");
        query.setParameter("group", group);
        query.setParameter("principal", principal);
        return ((Long) query.getSingleResult()).intValue() >= 1;
    }

// =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addMember(DexGroup group, DexPrincipal member, DexUser user) throws RecursiveGroupException {
        if (member instanceof DexGroup) {
            if (checkRecursive(group, (DexGroup) member))
                throw new RecursiveGroupException("Recursive user group " + group.getName() + " > " + member.getName());
        }

        DexGroupMember groupMember = new DexGroupMemberImpl();
        groupMember.setGroup(group);
        groupMember.setPrincipal(member);
        entityManager.persist(groupMember);
    }

    @Override
    public void addMembers(DexGroup group, List<DexPrincipal> members, DexUser user) throws RecursiveGroupException {
        List<DexPrincipal> currentMembers = findMembers(group);
        List<DexPrincipal> newMembers = new ArrayList<DexPrincipal>();

        for (DexPrincipal currentMember : currentMembers) {
            if (!newMembers.contains(currentMember)) {
                deleteMember(group, currentMember);
            }
        }
        for (DexPrincipal newMember : newMembers) {
            if (!currentMembers.contains(newMember)) {
                addMember(group, newMember, user);
            }
        }
    }

    @Override
    public void deleteMember(DexGroup group, DexPrincipal member) {
        Query query = entityManager.createQuery("select g from DexGroupMember g where " +
                "g.group = :group " +
                "and g.principal = :principal");
        query.setParameter("group", group);
        query.setParameter("principal", member);
        DexGroupMember groupMember = (DexGroupMember) query.getSingleResult();
        entityManager.remove(groupMember);
    }

    private boolean checkRecursive(DexGroup parent, DexGroup child) {
        Set<DexGroup> hierarchicalGroup = findEffectiveAsNative(parent);
        return !hierarchicalGroup.add(child);
    }
}
