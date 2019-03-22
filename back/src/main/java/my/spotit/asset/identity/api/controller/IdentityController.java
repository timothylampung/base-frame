package my.spotit.asset.identity.api.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import my.spotit.asset.common.business.service.CommonService;
import my.spotit.asset.core.api.ApplicationSuccess;
import my.spotit.asset.identity.api.vo.Group;
import my.spotit.asset.identity.api.vo.GroupMember;
import my.spotit.asset.identity.api.vo.GroupResult;
import my.spotit.asset.identity.api.vo.Principal;
import my.spotit.asset.identity.api.vo.Staff;
import my.spotit.asset.identity.api.vo.StaffResult;
import my.spotit.asset.identity.api.vo.User;
import my.spotit.asset.identity.api.vo.UserResult;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.dao.RecursiveGroupException;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexGroupImpl;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexStaff;
import my.spotit.asset.identity.domain.model.DexStaffImpl;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;
import my.spotit.asset.security.business.service.SecurityService;

import static my.spotit.asset.DexConstants.LIMIT;

/**
 * @author canang technologies
 */
@RestController
@RequestMapping("/api/identity")
public class IdentityController {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityController.class);

    private IdentityService identityService;
    private SecurityService securityService;
    private IdentityTransformer identityTransformer;
    private CommonService commonService;

    @Autowired
    public IdentityController(IdentityService identityService, SecurityService securityService,
                              IdentityTransformer identityTransformer, CommonService commonService) {
        this.identityService = identityService;
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


    @PostMapping(value = "/staffs/upload")
    public ResponseEntity<?> uploadAssets(@RequestParam("file") MultipartFile file) throws Exception {
        File tempFile = File.createTempFile("tmp_", null);
        FileUtils.writeByteArrayToFile(tempFile, file.getBytes());
        identityService.parseStaff(tempFile);
        return ResponseEntity.ok(new ApplicationSuccess("success", "Attachment added"));
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
}