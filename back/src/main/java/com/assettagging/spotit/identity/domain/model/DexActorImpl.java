package com.assettagging.spotit.identity.domain.model;


import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author canang technologies
 */
@Entity(name = "DexActor")
@Table(name = "DEX_ACTR")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DexActorImpl implements DexActor {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ACTR")
    @SequenceGenerator(name = "SQ_DEX_ACTR", sequenceName = "SQ_DEX_ACTR", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "IDENTITY_NO", nullable = false)
    private String identityNo;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Column(name = "PHONE", nullable = true)
    private String phone;

    @Column(name = "MOBILE", nullable = true)
    private String mobile;

    @NotNull
    @Column(name = "FAX", nullable = true)
    private String fax;

    @Column(name = "ADDRESS1", nullable = true)
    private String address1;

    @Column(name = "ADDRESS2", nullable = true)
    private String address2;

    @Column(name = "ADDRESS3", nullable = true)
    private String address3;

    @Column(name = "POSTCODE", nullable = true)
    private String postcode;

    @Column(name = "ACTOR_TYPE", nullable = false)
    private DexActorType actorType;

    @Embedded
    private DexMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Override
    public String getAddress2() {
        return address2;
    }

    @Override
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String getAddress3() {
        return address3;
    }

    @Override
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    @Override
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public DexActorType getActorType() {
        return actorType;
    }

    public void setActorType(DexActorType actorType) {
        this.actorType = actorType;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

}
