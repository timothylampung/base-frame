package my.spotit.asset.identity.api.controller;

import my.spotit.asset.common.business.service.CommonService;

import my.spotit.asset.identity.api.vo.Actor;
import my.spotit.asset.identity.api.vo.FacilityManagerResult;
import my.spotit.asset.identity.api.vo.Group;
import my.spotit.asset.identity.api.vo.GroupResult;
import my.spotit.asset.identity.api.vo.Principal;
import my.spotit.asset.identity.api.vo.StaffResult;
import my.spotit.asset.identity.api.vo.Supervisor;
import my.spotit.asset.identity.api.vo.SupervisorResult;
import my.spotit.asset.identity.api.vo.TechnicianResult;
import my.spotit.asset.identity.api.vo.User;
import my.spotit.asset.identity.api.vo.UserResult;
import my.spotit.asset.identity.business.service.ActorService;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.dao.RecursiveGroupException;
import my.spotit.asset.security.business.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import my.spotit.asset.identity.api.vo.FacilityManager;
import my.spotit.asset.identity.api.vo.GroupMember;
import my.spotit.asset.identity.api.vo.Staff;
import my.spotit.asset.identity.api.vo.Technician;
import my.spotit.asset.identity.domain.model.DexFacilityManager;
import my.spotit.asset.identity.domain.model.DexFacilityManagerImpl;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexGroupImpl;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexStaffImpl;
import my.spotit.asset.identity.domain.model.DexSupervisor;
import my.spotit.asset.identity.domain.model.DexSupervisorImpl;
import my.spotit.asset.identity.domain.model.DexTechnician;
import my.spotit.asset.identity.domain.model.DexTechnicianImpl;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;

import static my.spotit.asset.DexConstants.LIMIT;

/**
 * @author canang technologies
 */
@RestController
@RequestMapping("/api/identity")
public class IdentityController {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityController.class);

    private IdentityService identityService;
    private ActorService actorService;
    private SecurityService securityService;
    private IdentityTransformer identityTransformer;
    private CommonService commonService;

    @Autowired
    public IdentityController(IdentityService identityService,
                              ActorService actorService, SecurityService securityService,
                              IdentityTransformer identityTransformer, CommonService commonService) {
        this.identityService = identityService;
        this.actorService = actorService;
        this.securityService = securityService;
        this.identityTransformer = identityTransformer;
        this.commonService = commonService;
    }

    // =============================================================================================
    // AUTHENTICATED USER AND ACTOR
    // =============================================================================================

    @GetMapping(value = "/authenticated-user")
    public ResponseEntity<User> findAuthenticatedUser() {
        DexUser currentUser = securityService.getCurrentUser();
        return new ResponseEntity<User>(identityTransformer.toUserVo(currentUser), HttpStatus.OK);
    }

    //==============================================================================================
    // PRINCIPAL
    //==============================================================================================

    @GetMapping(value = "/principals")
    public ResponseEntity<List<Principal>> findPrincipals() {
        return new ResponseEntity<List<Principal>>(identityTransformer.toPrincipalVos(
                identityService.findPrincipals("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/principal/{name}")
    public ResponseEntity<Principal> findPrincipalByName(@PathVariable String name) {
        return new ResponseEntity<Principal>(identityTransformer.toPrincipalVo(
                identityService.findPrincipalByName(name)), HttpStatus.OK);
    }

    //==============================================================================================
    // USER
    //==============================================================================================

    @GetMapping(value = "/users", params = {"page", "filter"})
    public ResponseEntity<UserResult> findPagedUsers(@RequestParam String filter, @RequestParam Integer page) {
        Integer count = identityService.countUser(filter);
        List<DexUser> users = identityService.findUsers(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<UserResult>(new UserResult(
                identityTransformer.toUserVos(users),
                count
        ), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findUsers() {
        return new ResponseEntity<List<User>>(identityTransformer.toUserVos(
                identityService.findUsers("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{username:.+}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        return new ResponseEntity<User>(identityTransformer.toUserVo(
                identityService.findUserByUsername(username)), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> saveUser(@RequestBody User vo) {
        DexUser user = new DexUserImpl();
        user.setName(vo.getName());
        user.setRealName(vo.getRealName());
        user.setEmail(vo.getEmail());
        user.setPassword(vo.getPassword());
        identityService.saveUser(user);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

//    @PutMapping(value = "/user/{username:.+}")
//    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User vo, @RequestBody Staff staff) {
//        DexUser user = identityService.findUserByUsername(username);
//        user.setName(vo.getName());
//        user.setRealName(vo.getRealName());
//        user.setEmail(vo.getEmail());
//        user.setPassword(vo.getPassword());
//        user.setActor(identityService.findStaffByIdentityNo(staff.getIdentityNo()));
//        identityService.updateUser(user);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }

    @PutMapping(value = "/user/{username:.+}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User vo) {
        DexUser user = identityService.findUserByUsername(username);
        user.setName(vo.getName());
        user.setRealName(vo.getRealName());
        user.setEmail(vo.getEmail());
        identityService.updateUser(user);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{username:.+}")
    public ResponseEntity<String> removeUser(@PathVariable String username) {
        DexUser user = identityService.findUserByUsername(username);
        identityService.removeUser(user);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //==============================================================================================
    // GROUP
    //==============================================================================================

    @GetMapping(value = "/groups", params = {"page", "filter"})
    public ResponseEntity<GroupResult> findPagedGroups(@RequestParam Integer page, @RequestParam String filter) {
        Integer count = identityService.countGroup(filter);
        List<DexGroup> groups = identityService.findGroups(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<GroupResult>(new GroupResult(identityTransformer.toGroupVos(groups), count), HttpStatus.OK);
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<List<Group>> findGroups() {
        return new ResponseEntity<List<Group>>(identityTransformer.toGroupVos(
                identityService.findGroups()), HttpStatus.OK);
    }

    @GetMapping(value = "/group/{name}")
    public ResponseEntity<Group> findGroupByName(@PathVariable String name) {
        return new ResponseEntity<Group>(identityTransformer.toGroupVo(
                identityService.findGroupByName(name)), HttpStatus.OK);
    }

    @PostMapping(value = "/group")
    public ResponseEntity<String> saveGroup(@RequestBody Group vo) {
        DexGroup group = new DexGroupImpl();
        group.setName(vo.getName());
        identityService.saveGroup(group);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/group/{name}")
    public ResponseEntity<String> updateGroup(@PathVariable String name, @RequestBody Group vo) {
        DexGroup group = identityService.findGroupByName(name);
        group.setName(vo.getName());
        identityService.updateGroup(group);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/group/{name}")
    public ResponseEntity<String> removeGroup(@PathVariable String name) {
        DexGroup group = identityService.findGroupByName(name);
        identityService.removeGroup(group);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // GROUP MEMBER
    // =============================================================================================

    @GetMapping(value = "/group/{name}/group-members")
    public ResponseEntity<List<GroupMember>> findGroupMembers(@PathVariable String name) {
        DexGroup groupByName = identityService.findGroupByName(name);
        return new ResponseEntity<List<GroupMember>>(
                identityTransformer.toGroupMemberVos(identityService.findGroupMembersAsGroupMember(groupByName)),
                HttpStatus.OK);
    }

    @PutMapping(value = "/group/{name}/add/")
    public ResponseEntity<String> addGroupMember(@PathVariable String name, @RequestBody GroupMember member) {
        DexGroup group = identityService.findGroupByName(name);
        DexPrincipal principal = identityService.findPrincipalById(member.getPrincipal().getId());
        try {
            identityService.addGroupMember(group, principal);
        } catch (RecursiveGroupException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/group/{name}/group-member/{memberName:.+}")
    public ResponseEntity<String> deleteGroupMember(@PathVariable String name, @PathVariable String memberName) {
        DexGroup group = identityService.findGroupByName(name);
        DexPrincipal principal = identityService.findPrincipalByName(memberName);
        identityService.deleteGroupMember(group, principal);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // STAFF
    // =============================================================================================

    @GetMapping(value = "/staffs", params = {"page", "filter"})
    public ResponseEntity<StaffResult> findPagedStaffs(@RequestParam Integer page, @RequestParam String filter) {
        Integer count = identityService.countStaff(filter);
        List<DexStaff> staffs = identityService.findStaffs(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<StaffResult>(
                new StaffResult(
                        identityTransformer.toStaffsVos(staffs),
                        count
                ), HttpStatus.OK);
    }

    @GetMapping(value = "/staffs")
    public ResponseEntity<List<Staff>> findStaffs() {
        return new ResponseEntity<List<Staff>>(identityTransformer.toStaffsVos(
                identityService.findStaffs(0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

//    @GetMapping(value = "/staff/{identityNo}")
//    public ResponseEntity<Staff> findStaffByIdentityNo(@PathVariable String identityNo) {
//        return new ResponseEntity<Staff>(identityTransformer.toStaffVo(
//                identityService.findStaffByIdentityNo(identityNo)), HttpStatus.OK);
//    }

    @GetMapping(value = "/staff/{code}")
    public ResponseEntity<Staff> findStaffByCode(@PathVariable String code) {
        return new ResponseEntity<Staff>(identityTransformer.toStaffVo(
                identityService.findStaffByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/staff")
    public ResponseEntity<String> saveStaff(@RequestBody Staff vo) {
        DexStaff staff = new DexStaffImpl();
        staff.setIdentityNo(vo.getIdentityNo());
        staff.setCode(vo.getCode());
        staff.setName(vo.getName());
        staff.setAddress1(vo.getAddress1());
        staff.setAddress2(vo.getAddress2());
        staff.setEmail(vo.getEmail());
        staff.setMobile(vo.getMobile());
        staff.setFax(vo.getFax());
        staff.setPhone(vo.getPhone());
//        staff.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.saveStaff(staff);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/staff/{code}")
    public ResponseEntity<String> updateStaff(@PathVariable String code, @RequestBody Staff vo) {
        DexStaff staff = identityService.findStaffByCode(code);
        staff.setIdentityNo(vo.getIdentityNo());
        staff.setCode(vo.getCode());
        staff.setName(vo.getName());
        staff.setEmail(vo.getEmail());
        staff.setMobile(vo.getMobile());
        staff.setPhone(vo.getPhone());
//        staff.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.updateStaff(staff);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/staff/{code}")
    public ResponseEntity<String> removeStaff(@PathVariable String code) {
        DexStaff staff = identityService.findStaffByCode(code);
        identityService.removeStaff(staff);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    @GetMapping(value = "/actors")
    public ResponseEntity<List<Actor>> findAllActors() {
        return ResponseEntity.ok(identityTransformer.toActors(actorService.findAllActors()));
    }

    // =============================================================================================
    // FACILITY MANAGER
    // =============================================================================================

    @GetMapping(value = "/facility-managers", params = {"page", "filter"})
    public ResponseEntity<FacilityManagerResult> findPagedFacilityManagers(@RequestParam Integer page, @RequestParam String filter) {
        Integer count = identityService.count(filter);
        List<DexFacilityManager> facilityManagers = identityService.findFacilityManagers(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<FacilityManagerResult>(
                new FacilityManagerResult(
                        identityTransformer.toFacilityManagersVos(facilityManagers),
                        count
                ), HttpStatus.OK);
    }

    @GetMapping(value = "/facility-managers")
    public ResponseEntity<List<FacilityManager>> findFacilityManagers() {
        return new ResponseEntity<List<FacilityManager>>(identityTransformer.toFacilityManagersVos(
                identityService.findFacilityManagers()), HttpStatus.OK);
    }

//    @GetMapping(value = "/facility-manager/{identityNo}")
//    public ResponseEntity<FacilityManager> findFacilityManagerByIdentityNo(@PathVariable String identityNo) {
//        return new ResponseEntity<FacilityManager>(identityTransformer.toFacilityManagerVo(
//                identityService.findFacilityManagerByIdentityNo(identityNo)), HttpStatus.OK);
//    }

    @GetMapping(value = "/facility-manager/{code}")
    public ResponseEntity<FacilityManager> findFacilityManagerByCode(@PathVariable String code) {
        return new ResponseEntity<FacilityManager>(identityTransformer.toFacilityManagerVo(
                identityService.findFacilityManagerByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/facility-manager")
    public ResponseEntity<String> saveFacilityManager(@RequestBody FacilityManager vo) {
        DexFacilityManager facilityManager = new DexFacilityManagerImpl();
        facilityManager.setIdentityNo(vo.getIdentityNo());
        facilityManager.setCode(vo.getCode());
        facilityManager.setName(vo.getName());
        facilityManager.setAddress1(vo.getAddress1());
        facilityManager.setAddress2(vo.getAddress2());
        facilityManager.setEmail(vo.getEmail());
        facilityManager.setMobile(vo.getMobile());
        facilityManager.setFax(vo.getFax());
        facilityManager.setPhone(vo.getPhone());
//        staff.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.saveFacilityManager(facilityManager);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/facility-manager/{code}")
    public ResponseEntity<String> updateFacilityManager(@PathVariable String code, @RequestBody FacilityManager vo) {
        DexFacilityManager facilityManager = identityService.findFacilityManagerByCode(code);
        facilityManager.setIdentityNo(vo.getIdentityNo());
        facilityManager.setCode(vo.getCode());
        facilityManager.setName(vo.getName());
        facilityManager.setEmail(vo.getEmail());
        facilityManager.setMobile(vo.getMobile());
        facilityManager.setPhone(vo.getPhone());
//        facilityManager.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.updateFacilityManager(facilityManager);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/facility-manager/{code}")
    public ResponseEntity<String> removeFacilityManager(@PathVariable String code) {
        DexFacilityManager facilityManager = identityService.findFacilityManagerByCode(code);
        identityService.removeFacilityManager(facilityManager);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // =============================================================================================
    // SUPERVISOR
    // =============================================================================================

    @GetMapping(value = "/supervisors", params = {"page", "filter"})
    public ResponseEntity<SupervisorResult> findPagedSupervisors(@RequestParam Integer page, @RequestParam String filter) {
        Integer count = identityService.countSupervisor(filter);
        List<DexSupervisor> supervisors = identityService.findSupervisors(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<SupervisorResult>(
                new SupervisorResult(
                        identityTransformer.toSupervisorsVos(supervisors),
                        count
                ), HttpStatus.OK);
    }

    @GetMapping(value = "/supervisors")
    public ResponseEntity<List<Supervisor>> findSupervisors() {
        return new ResponseEntity<List<Supervisor>>(identityTransformer.toSupervisorsVos(
                identityService.findSupervisors(0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

//    @GetMapping(value = "/supervisor/{identityNo}")
//    public ResponseEntity<Supervisor> findSupervisorByIdentityNo(@PathVariable String identityNo) {
//        return new ResponseEntity<Supervisor>(identityTransformer.toSupervisorVo(
//                identityService.findSupervisorByIdentityNo(identityNo)), HttpStatus.OK);
//    }

    @GetMapping(value = "/supervisor/{code}")
    public ResponseEntity<Supervisor> findSupervisorByCode(@PathVariable String code) {
        return new ResponseEntity<Supervisor>(identityTransformer.toSupervisorVo(
                identityService.findSupervisorByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/supervisor")
    public ResponseEntity<String> saveSupervisor(@RequestBody Supervisor vo) {
        DexSupervisor supervisor = new DexSupervisorImpl();
        supervisor.setIdentityNo(vo.getIdentityNo());
        supervisor.setCode(vo.getCode());
        supervisor.setName(vo.getName());
        supervisor.setAddress1(vo.getAddress1());
        supervisor.setAddress2(vo.getAddress2());
        supervisor.setEmail(vo.getEmail());
        supervisor.setMobile(vo.getMobile());
        supervisor.setFax(vo.getFax());
        supervisor.setPhone(vo.getPhone());
//        supervisor.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.saveSupervisor(supervisor);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/supervisor/{code}")
    public ResponseEntity<String> updateSupervisor(@PathVariable String code, @RequestBody Supervisor vo) {
        DexSupervisor supervisor = identityService.findSupervisorByCode(code);
        supervisor.setIdentityNo(vo.getIdentityNo());
        supervisor.setCode(vo.getCode());
        supervisor.setName(vo.getName());
        supervisor.setEmail(vo.getEmail());
        supervisor.setMobile(vo.getMobile());
        supervisor.setPhone(vo.getPhone());
//        supervisor.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.updateSupervisor(supervisor);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/supervisor/{code}")
    public ResponseEntity<String> removeSupervisor(@PathVariable String code) {
        DexSupervisor supervisor = identityService.findSupervisorByCode(code);
        identityService.removeSupervisor(supervisor);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    // =============================================================================================
    // TECHNICIAN
    // =============================================================================================

    @GetMapping(value = "/technicians", params = {"page", "filter"})
    public ResponseEntity<TechnicianResult> findPagedTechnicians(@RequestParam Integer page, @RequestParam String filter) {
        Integer count = identityService.countTechnician(filter);
        List<DexTechnician> technicians = identityService.findTechnicians(filter, (page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<TechnicianResult>(
                new TechnicianResult(identityTransformer.toTechniciansVos(technicians), count), HttpStatus.OK);
    }

    @GetMapping(value = "/technicians")
    public ResponseEntity<List<Technician>> findTechnicians() {
        return new ResponseEntity<List<Technician>>(identityTransformer.toTechniciansVos(
                identityService.findTechnicians(0, Integer.MAX_VALUE)), HttpStatus.OK);
    }

//    @GetMapping(value = "/technician/{identityNo}")
//    public ResponseEntity<Technician> findTechnicianByIdentityNo(@PathVariable String identityNo) {
//        return new ResponseEntity<Technician>(identityTransformer.toTechnicianVo(
//                identityService.findTechnicianByIdentityNo(identityNo)), HttpStatus.OK);
//    }

    @GetMapping(value = "/technician/{code}")
    public ResponseEntity<Technician> findTechnicianByCode(@PathVariable String code) {
        return new ResponseEntity<Technician>(identityTransformer.toTechnicianVo(
                identityService.findTechnicianByCode(code)), HttpStatus.OK);
    }

    @PostMapping(value = "/technician")
    public ResponseEntity<String> saveTechnician(@RequestBody Technician vo) {
        DexTechnician technician = new DexTechnicianImpl();
        technician.setIdentityNo(vo.getIdentityNo());
        technician.setCode(vo.getCode());
        technician.setName(vo.getName());
        technician.setAddress1(vo.getAddress1());
        technician.setAddress2(vo.getAddress2());
        technician.setEmail(vo.getEmail());
        technician.setMobile(vo.getMobile());
        technician.setFax(vo.getFax());
        technician.setPhone(vo.getPhone());
//        technician.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.saveTechnician(technician);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PutMapping(value = "/technician/{code}")
    public ResponseEntity<String> updateTechnician(@PathVariable String code, @RequestBody Technician vo) {
        DexTechnician technician = identityService.findTechnicianByCode(code);
        technician.setIdentityNo(vo.getIdentityNo());
        technician.setCode(vo.getCode());
        technician.setName(vo.getName());
        technician.setEmail(vo.getEmail());
        technician.setMobile(vo.getMobile());
        technician.setPhone(vo.getPhone());
//        technician.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
        identityService.updateTechnician(technician);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/technician/{code}")
    public ResponseEntity<String> removeTechnician(@PathVariable String code) {
        DexTechnician technician = identityService.findTechnicianByCode(code);
        identityService.removeTechnician(technician);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

}