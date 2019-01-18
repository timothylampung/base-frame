package com.assettagging.spotit.common.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexBank")
@Table(name = "DEX_BANK")

public class DexBankImpl implements DexBank {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_BANK")
    @SequenceGenerator(name = "SQ_DEX_BANK", sequenceName = "SQ_DEX_BANK", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "BRANCH", nullable = false)
    private String branch;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @NotNull
    @Column(name = "PERSON_IN_CHARGE", nullable = false)
    private String personInCharge;

    @NotNull
    @Column(name = "CONTACT_NO", nullable = false)
    private String contactNo;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    private DexBankType type;

    @NotNull
    @Column(name = "REMARKS", nullable = false)
    private String remarks;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private DexBankStatus status;

    @Embedded
    private DexMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBranch() {
        return branch;
    }

    @Override
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPersonInCharge() {
        return personInCharge;
    }

    @Override
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    @Override
    public String getContactNo() {
        return contactNo;
    }

    @Override
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public DexBankType getType() {
        return type;
    }

    @Override
    public void setType(DexBankType type) {
        this.type = type;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



    @Override
    public void setStatus(DexBankStatus status) {
        this.status = status;
    }

    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexBank.class;
    }
}

