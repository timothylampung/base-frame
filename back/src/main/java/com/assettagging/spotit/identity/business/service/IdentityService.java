package com.assettagging.spotit.identity.business.service;

import com.assettagging.spotit.identity.domain.dao.RecursiveGroupException;
import com.assettagging.spotit.identity.domain.model.*;
import com.assettagging.spotit.identity.domain.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 */
public interface IdentityService {

    // =============================================================================================
    // PRINCIPAL
    // =============================================================================================

    DexPrincipal findPrincipalById(Long id);

    DexPrincipal findPrincipalByName(String name);

    List<DexPrincipal> findPrincipals(String filter, Integer offset, Integer limit);

    Set<String> findSids(DexPrincipal principal);

    Integer countPrincipal(String filter);

    void addPrincipalRole(DexPrincipal principal, DexPrincipalRole principalRole);

    void deletePrincipalRole(DexPrincipal principal, DexPrincipalRole principalRole);

    // =============================================================================================
    // USER
    // =============================================================================================

    DexUser findUserByEmail(String email);

    DexUser findUserByUsername(String username);

    DexUser findUserById(Long id);

    List<DexUser> findUsers(Integer offset, Integer limit);

    List<DexUser> findUsers(String filter, Integer offset, Integer limit);

    Integer countUser();

    Integer countUser(String filter);

    boolean isUserExists(String username);

    void saveUser(DexUser user);

    void updateUser(DexUser user);

    void removeUser(DexUser user);

    void changePassword(DexUser user, String newPassword);

    // =============================================================================================
    // GROUP
    // =============================================================================================

    DexGroup getRootGroup();

    DexGroup findGroupByName(String name);

    DexGroup findOrCreateGroupByName(String name);

    DexGroup findGroupById(Long id);

    List<DexGroup> findGroups(Integer offset, Integer limit);

    List<DexGroup> findImmediateGroups(DexPrincipal principal);

    List<DexGroup> findImmediateGroups(DexPrincipal principal, Integer offset, Integer limit);

    Set<DexGroup> findEffectiveGroups(DexPrincipal principal);

    Set<String> findEffectiveGroupsAsString(DexPrincipal principal);

    List<DexGroup> findAvailableUserGroups(DexUser user);

    List<DexPrincipal> findAvailableGroupMembers(DexGroup group);

    List<DexGroupMember> findGroupMembersAsGroupMember(DexGroup group);

    List<DexPrincipal> findGroupMembers(DexGroup group);

    List<DexPrincipal> findGroupMembers(DexGroup group, Integer offset, Integer limit);

    Integer countGroup();

    Integer countGroupMember(DexGroup group);

    boolean isGroupExists(String name);

    boolean hasMembership(DexGroup group, DexPrincipal principal);

    DexGroup createGroupWithRole(String groupName, DexRoleType... types);

    void saveGroup(DexGroup group);

    void updateGroup(DexGroup group);

    void removeGroup(DexGroup group);

    void addGroupMember(DexGroup group, DexPrincipal principal) throws RecursiveGroupException;

    void deleteGroupMember(DexGroup group, DexPrincipal principal);

    // =============================================================================================
    // STAFF
    // =============================================================================================

    DexStaff findStaffByIdentityNo(String identityNo);

    DexStaff findStaffByCode(String code);

    List<DexStaff> findStaffs(Integer offset, Integer limit);

    Integer countStaff();

    Integer countStaff(String filter);

    void saveStaff(DexStaff staff);

    void updateStaff(DexStaff staff);

    void removeStaff(DexStaff staff);

}

