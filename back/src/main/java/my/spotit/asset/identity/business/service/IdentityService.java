package my.spotit.asset.identity.business.service;

import my.spotit.asset.identity.domain.dao.RecursiveGroupException;

import java.util.List;
import java.util.Set;

import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexFacilityManager;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexGroupMember;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalRole;
import my.spotit.asset.identity.domain.model.DexRoleType;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexSupervisor;
import my.spotit.asset.identity.domain.model.DexTechnician;
import my.spotit.asset.identity.domain.model.DexUser;

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

    List<DexGroup> findGroups();

    List<DexGroup> findGroups(String filter, Integer offset, Integer limit);

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

    Integer countGroup(String filter);

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
    // ACTOR
    // =============================================================================================
    DexActor findActorById(Long id);

    DexActor findActorByCode(String code);

    DexActor findActorByIdentityNo(String identityNo);

    // =============================================================================================
    // STAFF
    // =============================================================================================

    DexStaff findStaffByIdentityNo(String identityNo);

    DexStaff findStaffByCode(String code);

    List<DexStaff> findStaffs(String filter, Integer offset, Integer limit);

    List<DexStaff> findStaffs(Integer offset, Integer limit);

    Integer countStaff();

    Integer countStaff(String filter);

    void saveStaff(DexStaff staff);

    void updateStaff(DexStaff staff);

    void removeStaff(DexStaff staff);

    // =============================================================================================
    // FACILITY MANAGER
    // =============================================================================================

    DexFacilityManager findFacilityManagerByIdentityNo(String identityNo);

    DexFacilityManager findFacilityManagerByCode(String code);

    List<DexFacilityManager> findFacilityManagers();

    List<DexFacilityManager> findFacilityManagers(String filter, Integer offset, Integer limit);

    Integer count();

    Integer count(String filter);

    void saveFacilityManager(DexFacilityManager facilityManager);

    void updateFacilityManager(DexFacilityManager facilityManager);

    void removeFacilityManager(DexFacilityManager facilityManager);

    // =============================================================================================
    // SUPERVISOR
    // =============================================================================================

    DexSupervisor findSupervisorByIdentityNo(String identityNo);

    DexSupervisor findSupervisorByCode(String code);

    List<DexSupervisor> findSupervisors(Integer offset, Integer limit);

    List<DexSupervisor> findSupervisors(String filter, Integer offset, Integer limit);

    Integer countSupervisor();

    Integer countSupervisor(String filter);

    void saveSupervisor(DexSupervisor supervisor);

    void updateSupervisor(DexSupervisor supervisor);

    void removeSupervisor(DexSupervisor supervisor);

    // =============================================================================================
    // TECHNICIAN
    // =============================================================================================

    DexTechnician findTechnicianByIdentityNo(String identityNo);

    DexTechnician findTechnicianByCode(String code);

    List<DexTechnician> findTechnicians(Integer offset, Integer limit);

    List<DexTechnician> findTechnicians(String filter, Integer offset, Integer limit);

    Integer countTechnician();

    Integer countTechnician(String filter);

    void saveTechnician(DexTechnician technician);

    void updateTechnician(DexTechnician technician);

    void removeTechnician(DexTechnician technician);
}

