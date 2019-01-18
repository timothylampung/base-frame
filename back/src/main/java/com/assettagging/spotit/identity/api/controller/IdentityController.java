package com.assettagging.spotit.identity.api.controller;

import com.assettagging.spotit.common.business.service.CommonService;
import com.assettagging.spotit.identity.api.vo.*;
import com.assettagging.spotit.identity.domain.model.*;
import com.assettagging.spotit.identity.api.vo.*;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.dao.RecursiveGroupException;
import com.assettagging.spotit.identity.domain.model.*;
import com.assettagging.spotit.security.business.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.assettagging.spotit.DexConstants.LIMIT;

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
    public IdentityController(IdentityService identityService,
                              SecurityService securityService,
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

    @GetMapping(value = "/users", params = {"page"})
    public ResponseEntity<UserResult> findPagedUsers(@RequestParam Integer page) {
        Integer count = identityService.countUser("%");
        List<DexUser> users = identityService.findUsers("%", (page - 1) * LIMIT, LIMIT);
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

    @GetMapping(value = "/groups", params = {"page"})
    public ResponseEntity<GroupResult> findPagedGroups(@RequestParam Integer page) {
        Integer count = identityService.countGroup();
        List<DexGroup> groups = identityService.findGroups((page - 1) * LIMIT, LIMIT);
        return new ResponseEntity<GroupResult>(
                new GroupResult(
                        identityTransformer.toGroupVos(groups),
                        count
                ), HttpStatus.OK);
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<List<Group>> findGroups() {
        return new ResponseEntity<List<Group>>(identityTransformer.toGroupVos(
                identityService.findGroups(0, Integer.MAX_VALUE)), HttpStatus.OK);
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

    @GetMapping(value = "/staffs", params = {"page"})
    public ResponseEntity<StaffResult> findPagedStaffs(@RequestParam Integer page) {
        Integer count = identityService.countStaff();
        List<DexStaff> staffs = identityService.findStaffs((page - 1) * LIMIT, LIMIT);
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
    public ResponseEntity<String> saveStaff(@RequestBody Staff vo){
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
        staff.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
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
        staff.setPositionCode(commonService.findPositionCodeById(vo.getPositionCode().getId()));
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
