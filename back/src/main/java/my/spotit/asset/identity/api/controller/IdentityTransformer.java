package my.spotit.asset.identity.api.controller;


import my.spotit.asset.common.api.controller.CommonTransformer;
import my.spotit.asset.core.api.MetaState;
import my.spotit.asset.core.api.controller.CoreTransformer;
import my.spotit.asset.core.domain.DexMetadata;

import my.spotit.asset.identity.api.vo.Actor;
import my.spotit.asset.identity.api.vo.ActorType;
import my.spotit.asset.identity.api.vo.Group;
import my.spotit.asset.identity.api.vo.Principal;
import my.spotit.asset.identity.api.vo.PrincipalType;
import my.spotit.asset.identity.api.vo.Supervisor;
import my.spotit.asset.identity.api.vo.User;
import my.spotit.asset.identity.business.service.IdentityService;
import my.spotit.asset.identity.domain.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import my.spotit.asset.identity.api.vo.FacilityManager;
import my.spotit.asset.identity.api.vo.GroupMember;
import my.spotit.asset.identity.api.vo.Staff;
import my.spotit.asset.identity.api.vo.Technician;

/**
 * @author canang technologies
 */
@Component("identityTransformer")
public class IdentityTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityTransformer.class);

    private CommonTransformer commonTransformer;
    private IdentityService identityService;
    private CoreTransformer coreTransformer;

    @Autowired
    public IdentityTransformer(CommonTransformer commonTransformer,
                               IdentityService identityService, CoreTransformer coreTransformer) {
        this.commonTransformer = commonTransformer;
        this.identityService = identityService;
        this.coreTransformer = coreTransformer;
    }

    //==============================================================================================
    // PRINCIPAL
    //==============================================================================================

    public Principal toPrincipalVo(DexPrincipal e) {
        if (null == e) return null;
        Principal vo = new Principal();
        vo.setId(e.getId());
        vo.setName(e.getName());
        vo.setPrincipalType(PrincipalType.get(e.getPrincipalType().ordinal()));
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Principal> toPrincipalVos(List<DexPrincipal> e) {
        List<Principal> vos = e.stream()
                .map((e1) -> toPrincipalVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    //==============================================================================================
    // USER
    //==============================================================================================

    public User toUserVo(DexUser e) {
        if (null == e) return null;
        User vo = new User();
        vo.setId(e.getId());
        vo.setName(e.getName());
        vo.setRealName(e.getRealName());
        vo.setEmail(e.getEmail());
        vo.setPassword(e.getPassword());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }


    public List<User> toUserVos(List<DexUser> e) {
        List<User> vos = e.stream()
                .map((e1) -> toUserVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    //==============================================================================================
    // GROUP
    //==============================================================================================

    public Group toGroupVo(DexGroup e) {
        if (null == e) return null;
        Group vo = new Group();
        vo.setId(e.getId());
        vo.setName(e.getName());
        coreTransformer.toMetadata(e, vo);
        vo.setMemberCount(identityService.countGroupMember(e));
        return vo;
    }

    public List<Group> toGroupVos(List<DexGroup> e) {
        List<Group> vos = e.stream()
                .map((e1) -> toGroupVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    //==============================================================================================
    // GROUP MEMBER
    //==============================================================================================

    public GroupMember toGroupMemberVo(DexGroupMember e) {
        if (null == e) return null;
        GroupMember vo = new GroupMember();
        vo.setId(e.getId());
        coreTransformer.toMetadata(e, vo);
        vo.setPrincipal(toPrincipalVo(e.getPrincipal()));
        return vo;
    }

    public List<GroupMember> toGroupMemberVos(List<DexGroupMember> e) {
        List<GroupMember> vos = e.stream()
                .map((e1) -> toGroupMemberVo(e1))
                .collect(Collectors.toList());
        return vos;
    }


    //==============================================================================================
    // STAFF
    //==============================================================================================

    public Staff toStaffVo(DexStaff e) {
        if (null == e) return null;
        Staff vo = new Staff();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setAddress1(e.getAddress1());
        vo.setAddress2(e.getAddress2());
        coreTransformer.toMetadata(e, vo);
        vo.setAddress3(e.getAddress3());
//        vo.setPositionCode(commonTransformer.toPositionCodeVo(e.getPositionCode()));
        return vo;
    }

    public List<Staff> toStaffsVos(List<DexStaff> e) {
        List<Staff> vos = e.stream()
                .map((e1) -> toStaffVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public Actor toActor(DexActor e) {
        if (null == e) return null;
        Actor vo = new Actor();
        vo.setId(e.getId());
        vo.setActorType(ActorType.get(e.getActorType().ordinal()));
        vo.setAddress1(e.getAddress1());
        vo.setAddress2(e.getAddress2());
        vo.setAddress3(e.getAddress3());
        vo.setCode(e.getCode());
        vo.setEmail(e.getEmail());
        vo.setFax(e.getFax());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setMobile(e.getMobile());
        vo.setName(e.getName());
        vo.setPhone(e.getPhone());
        coreTransformer.toMetadata(e, vo);
        return vo;
    }

    public List<Actor> toActors(List<DexActor> e) {
        return e.stream().map(this::toActor).collect(Collectors.toList());
    }
}
