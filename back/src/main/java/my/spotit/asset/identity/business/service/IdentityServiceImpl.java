package my.spotit.asset.identity.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import my.spotit.asset.identity.business.parser.StaffParserRegistry;
import my.spotit.asset.identity.domain.dao.DexActorDao;
import my.spotit.asset.identity.domain.dao.DexGroupDao;
import my.spotit.asset.identity.domain.dao.DexPrincipalDao;
import my.spotit.asset.identity.domain.dao.DexStaffDao;
import my.spotit.asset.identity.domain.dao.DexUserDao;
import my.spotit.asset.identity.domain.dao.RecursiveGroupException;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexGroupImpl;
import my.spotit.asset.identity.domain.model.DexGroupMember;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalRole;
import my.spotit.asset.identity.domain.model.DexPrincipalRoleImpl;
import my.spotit.asset.identity.domain.model.DexRoleType;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.security.business.service.SecurityService;

/**
 * @author canang technologies
 */
@Transactional
@Service("DexIdentityService")
public class IdentityServiceImpl implements IdentityService {

    private static final String GROUP_ROOT = "GRP_ADMN";
    private static final Logger LOG = LoggerFactory.getLogger(IdentityServiceImpl.class);

    private EntityManager entityManager;
    private SecurityService securityService;
    private DexPrincipalDao principalDao;
    private DexUserDao userDao;
    private DexGroupDao groupDao;
    private DexStaffDao staffDao;
    private DexActorDao actorDao;
    private StaffParserRegistry staffParserRegistry;

    @Autowired
    public IdentityServiceImpl(EntityManager entityManager, SecurityService securityService,
                               StaffParserRegistry staffParserRegistry,
                               DexPrincipalDao principalDao, DexUserDao userDao,
                               DexGroupDao groupDao, DexStaffDao staffDao,
                               DexActorDao actorDao) {
        this.entityManager = entityManager;
        this.securityService = securityService;
        this.staffParserRegistry = staffParserRegistry;
        this.principalDao = principalDao;
        this.userDao = userDao;
        this.groupDao = groupDao;
        this.actorDao = actorDao;
        this.staffDao = staffDao;
    }

    //==============================================================================================
    // PRINCIPAL
    //==============================================================================================

    @Override
    public DexPrincipal findPrincipalById(Long id) {
        return principalDao.findById(id);
    }

    @Override
    public DexPrincipal findPrincipalByName(String name) {
        return principalDao.findByName(name);
    }

    @Override
    public List<DexPrincipal> findPrincipals(String filter, Integer offset, Integer limit) {
        return principalDao.find(filter, offset, limit);
    }

    @Override
    public Set<String> findSids(DexPrincipal principal) {
        Set<DexGroup> groups = null;
        Set<String> principals = new HashSet<String>();
        try {
            groups = groupDao.findEffectiveAsNative(principal);
        } catch (Exception e) {
            LOG.error("Error occured loading principals", e);
        } finally {
            if (null != groups) {
                for (DexGroup group : groups) {
                    principals.add(group.getName());
                }
            }
            principals.add(principal.getName());
        }
        return principals;
    }

    @Override
    public Integer countPrincipal(String filter) {
        return principalDao.count(filter);
    }

    @Override
    public void addPrincipalRole(DexPrincipal principal, DexPrincipalRole principalRole) {
        principalDao.addRole(principal, principalRole, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deletePrincipalRole(DexPrincipal principal, DexPrincipalRole principalRole) {
        principalDao.deleteRole(principal, principalRole, securityService.getCurrentUser());
        entityManager.flush();
    }

    //==============================================================================================
    // USER
    //==============================================================================================

    @Override
    public DexUser findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public DexUser findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public DexUser findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DexUser findUserByActor(DexActor actor) {
        return userDao.findByActor(actor);
    }

    @Override
    public List<DexUser> findUsers(Integer offset, Integer limit) {
        return userDao.find(offset, limit);
    }

    @Override
    public List<DexUser> findStaffMobile() {
        return userDao.findStaffMobile();
    }

    @Override
    public List<DexUser> findUsers(String filter, Integer offset, Integer limit) {
        return userDao.find(filter, offset, limit);
    }

    @Override
    public Integer countUser() {
        return userDao.count();
    }

    @Override
    public Integer countUser(String filter) {
        return userDao.count(filter);
    }

    @Override
    public boolean isUserExists(String username) {
        return userDao.isExists(username);
    }

    @Override
    public void saveUser(DexUser user) {
        userDao.save(user, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateUser(DexUser user) {
        userDao.update(user, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeUser(DexUser user) {
        userDao.remove(user, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void changePassword(DexUser user, String newPassword) {
        user.setPassword(newPassword);
        userDao.update(user, securityService.getCurrentUser());
        entityManager.flush();
    }

    //==============================================================================================
    // GROUP
    //==============================================================================================

    @Override
    public DexGroup getRootGroup() {
        return groupDao.findByName(GROUP_ROOT);
    }

    @Override
    public DexGroup findGroupByName(String name) {
        return groupDao.findByName(name);
    }

    @Override
    public DexGroup findOrCreateGroupByName(String name) {
        DexGroup group = null;
        if (groupDao.isExists(name))
            group = groupDao.findByName(name);
        else {
            group = new DexGroupImpl();
            group.setName(name);
            group.setEnabled(true);
            group.setLocked(false);
            groupDao.save(group, securityService.getCurrentUser());
        }
        entityManager.flush();
        entityManager.refresh(group);
        return group;
    }

    @Override
    public DexGroup findGroupById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public List<DexGroup> findGroups() {
        return groupDao.findAll();
    }

    @Override
    public List<DexGroup> findGroups(String filter, Integer offset, Integer limit) {
        return groupDao.find(filter, offset, limit);
    }

    @Override
    public List<DexGroup> findImmediateGroups(DexPrincipal principal) {
        return groupDao.findImmediate(principal);
    }

    @Override
    public List<DexGroup> findImmediateGroups(DexPrincipal principal, Integer offset, Integer limit) {
        return groupDao.findImmediate(principal, offset, limit);
    }

    @Override
    public Set<DexGroup> findEffectiveGroups(DexPrincipal principal) {
        return groupDao.findEffectiveAsNative(principal);
    }

    @Override
    public Set<String> findEffectiveGroupsAsString(DexPrincipal principal) {
        Set<String> groups = new HashSet<>();
        Set<DexGroup> groupSet = groupDao.findEffectiveAsNative(principal);
        for (DexGroup adGroup : groupSet) {
            groups.add(adGroup.getName());
        }
        return groups;
    }

    @Override
    public List<DexGroup> findAvailableUserGroups(DexUser user) {
        return groupDao.findAvailableGroups(user);
    }

    @Override
    public List<DexPrincipal> findAvailableGroupMembers(DexGroup group) {
        return groupDao.findAvailableMembers(group);
    }

    @Override
    public List<DexGroupMember> findGroupMembersAsGroupMember(DexGroup group) {
        List<DexGroupMember> members = groupDao.findMembersAsGroupMember(group);
        return members;
    }

    @Override
    public List<DexPrincipal> findGroupMembers(DexGroup group) {
        return groupDao.findMembers(group);
    }

    @Override
    public List<DexPrincipal> findGroupMembers(DexGroup group, Integer offset, Integer limit) {
        return groupDao.findMembers(group, offset, limit);
    }

    @Override
    public Integer countGroup() {
        return groupDao.count();
    }

    @Override
    public Integer countGroup(String filter) {
        return groupDao.count();
    }

    @Override
    public Integer countGroupMember(DexGroup group) {
        return groupDao.countMember(group);
    }

    @Override
    public boolean isGroupExists(String name) {
        return groupDao.isExists(name);
    }

    @Override
    public boolean hasMembership(DexGroup group, DexPrincipal principal) {
        return groupDao.hasMembership(group, principal);
    }

    @Override
    public DexGroup createGroupWithRole(String name, DexRoleType... types) {
        DexGroup group = new DexGroupImpl();
        group.setName(name);
        group.setEnabled(true);
        group.setLocked(false);
        groupDao.save(group, securityService.getCurrentUser());
        entityManager.flush();
        entityManager.refresh(group);

        for (DexRoleType type : types) {
            DexPrincipalRole role = new DexPrincipalRoleImpl();
            role.setRole(type);
            principalDao.addRole(group, role, securityService.getCurrentUser());
            entityManager.flush();
        }
        entityManager.refresh(group);
        return group;
    }

    @Override
    public void saveGroup(DexGroup group) {
        groupDao.save(group, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateGroup(DexGroup group) {
        groupDao.update(group, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeGroup(DexGroup group) {
        groupDao.remove(group, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void addGroupMember(DexGroup group, DexPrincipal principal) throws RecursiveGroupException {
        groupDao.addMember(group, principal, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void deleteGroupMember(DexGroup group, DexPrincipal principal) {
        groupDao.deleteMember(group, principal);
        entityManager.flush();
    }

    //==============================================================================================
    // ACTOR
    //==============================================================================================

    @Override
    public DexActor findActorById(Long id) {
        return actorDao.findById(id);
    }

    @Override
    public DexActor findActorByCode(String code) {
        return actorDao.findByCode(code);
    }

    @Override
    public DexActor findActorByIdentityNo(String identityNo) {
        return actorDao.findByIdentityNo(identityNo);
    }

    //==============================================================================================
    // STAFF
    //==============================================================================================

    @Override
    public DexStaff findStaffByIdentityNo(String identityNo) {
        return staffDao.findStaffByIdentityNo(identityNo);
    }

    @Override
    public DexStaff findStaffByCode(String code) {
        return staffDao.findStaffByCode(code);
    }

    @Override
    public List<DexStaff> findStaffs(String filter, Integer offset, Integer limit) {
        return staffDao.find(filter, offset, limit);
    }

    @Override
    public List<DexStaff> findStaffs(Integer offset, Integer limit) {
        return staffDao.find(offset, limit);
    }

    @Override
    public Integer countStaff() {
        return staffDao.count();
    }

    @Override
    public Integer countStaff(String filter) {
        return staffDao.count(filter);
    }

    @Override
    public void saveStaff(DexStaff staff) {
        staffDao.save(staff, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void updateStaff(DexStaff staff) {
        staffDao.update(staff, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void removeStaff(DexStaff staff) {
        staffDao.remove(staff, securityService.getCurrentUser());
        entityManager.flush();
    }

    @Override
    public void parseStaff(File file) throws Exception {
        try {
            staffParserRegistry.process(file);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}