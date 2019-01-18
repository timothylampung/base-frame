package com.assettagging.spotit.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

/**
 * @author canang technologies
 * @since 1/27/14
 */
@Embeddable
public class DexMetadata implements Serializable {

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "M_ST")
    private DexMetaState state;

    @Column(name = "C_ID")
    private Long creatorId;

    @Column(name = "M_ID")
    private Long modifierId;

    @Column(name = "D_ID")
    private Long deleterId;

    @Column(name = "C_TS")
    private Timestamp createdDate;

    @Column(name = "M_TS")
    private Timestamp modifiedDate;

    @Column(name = "D_TS")
    private Timestamp deletedDate;

    public DexMetaState getState() {
        return state;
    }

    public void setState(DexMetaState state) {
        this.state = state;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Long getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(Long deleterId) {
        this.deleterId = deleterId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Transient
    public boolean isActive() {
        return DexMetaState.ACTIVE.equals(getState());
    }
}
