package com.assettagging.spotit.identity.domain.dao;


import com.assettagging.spotit.core.domain.GenericDao;
import com.assettagging.spotit.identity.domain.model.*;
import com.assettagging.spotit.identity.domain.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 */
public interface DexGroupDao extends GenericDao<Long, DexGroup> {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================
    boolean hasMembership(DexGroup group, DexPrincipal principal);

    boolean isExists(String name);

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    DexGroup findByName(String name);

    List<DexGroup> findAll();

    List<DexGroup> findImmediate(DexPrincipal principal);

    List<DexGroup> findImmediate(DexPrincipal principal, Integer offset, Integer limit);

    Set<DexGroup> findEffectiveAsNative(DexPrincipal principal);

    List<DexGroup> findAvailableGroups(DexUser user);

    List<DexGroupMember> findMembersAsGroupMember(DexGroup group);

    List<DexPrincipal> findAvailableMembers(DexGroup group);

    List<DexPrincipal> findMembers(DexGroup group, DexPrincipalType principalType);

    List<DexPrincipal> findMembers(DexGroup group);

    List<DexPrincipal> findMembers(DexGroup group, Integer offset, Integer limit);

    List<DexGroup> find(String filter, Integer offset, Integer limit);

    List<DexGroup> findMemberships(DexPrincipal principal);

    Integer count(String filter);

    Integer countMember(DexGroup group);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addMember(DexGroup group, DexPrincipal member, DexUser user) throws RecursiveGroupException;

    void addMembers(DexGroup group, List<DexPrincipal> members, DexUser user) throws RecursiveGroupException;

    void deleteMember(DexGroup group, DexPrincipal member);

}
