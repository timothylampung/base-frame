package com.assettagging.spotit.identity.api.controller;


import com.assettagging.spotit.common.api.controller.CommonTransformer;
import com.assettagging.spotit.core.api.MetaState;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.api.vo.*;
import com.assettagging.spotit.identity.domain.model.*;
import com.assettagging.spotit.identity.api.vo.*;
import com.assettagging.spotit.identity.business.service.IdentityService;
import com.assettagging.spotit.identity.domain.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author canang technologies
 */
@Component("identityTransformer")
public class IdentityTransformer {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityTransformer.class);

    private CommonTransformer commonTransformer;
    private IdentityService identityService;

    @Autowired
    public IdentityTransformer(CommonTransformer commonTransformer,
                               IdentityService identityService) {
        this.commonTransformer = commonTransformer;
        this.identityService = identityService;
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
        DexMetadata metadata = e.getMetadata();
        vo.setCreatedDate(metadata.getCreatedDate());
        vo.setDeletedDate(metadata.getDeletedDate());
        vo.setModifiedDate(metadata.getModifiedDate());
        vo.setMetaState(MetaState.get(metadata.getState().ordinal()));
        return vo;
    }

    public List<Actor> toActors(List<DexActor> e) {
        return e.stream().map(this::toActor).collect(Collectors.toList());
    }

    //==============================================================================================
    // FACILITY MANAGER
    //==============================================================================================

    public List<FacilityManager> toFacilityManagersVos(List<DexFacilityManager> e) {
        List<FacilityManager> vos = e.stream()
                .map((e1) -> toFacilityManagerVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public FacilityManager toFacilityManagerVo(DexFacilityManager e) {
        if (null == e) return null;
        FacilityManager vo = new FacilityManager();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setAddress1(e.getAddress1());
        vo.setAddress2(e.getAddress2());
        vo.setAddress3(e.getAddress3());
//        vo.setPositionCode(commonTransformer.toPositionCodeVo(e.getPositionCode()));
        return vo;
    }

    //==============================================================================================
    // SUPERVISOR
    //==============================================================================================

    public List<Supervisor> toSupervisorsVos(List<DexSupervisor> e) {
        List<Supervisor> vos = e.stream()
                .map((e1) -> toSupervisorVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public Supervisor toSupervisorVo(DexSupervisor e) {
        if (null == e) return null;
        Supervisor vo = new Supervisor();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setAddress1(e.getAddress1());
        vo.setAddress2(e.getAddress2());
        vo.setAddress3(e.getAddress3());
//        vo.setPositionCode(commonTransformer.toPositionCodeVo(e.getPositionCode()));
        return vo;    }

    //==============================================================================================
    // TECHNICIAN
    //==============================================================================================

    public List<Technician> toTechniciansVos(List<DexTechnician> e) {
        List<Technician> vos = e.stream()
                .map((e1) -> toTechnicianVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public Technician toTechnicianVo(DexTechnician e) {
        if (null == e) return null;
        Technician vo = new Technician();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setAddress1(e.getAddress1());
        vo.setAddress2(e.getAddress2());
        vo.setAddress3(e.getAddress3());
//        vo.setPositionCode(commonTransformer.toPositionCodeVo(e.getPositionCode()));
        return vo;
    }
}