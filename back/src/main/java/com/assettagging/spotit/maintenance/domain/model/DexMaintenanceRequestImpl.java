package com.assettagging.spotit.maintenance.domain.model;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.core.domain.DexFlowdata;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexMaintenanceRequest")
@Table(name = "DEX_MNTC")

public class DexMaintenanceRequestImpl implements DexMaintenanceRequest {


    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_MNTC_REQ")
    @SequenceGenerator(name = "SQ_MNTC_REQ", sequenceName = "SQ_MNTC_REQ", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = DexActorImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", nullable = false)
    private DexActor requester;

    @OneToOne(targetEntity = DexLocationImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private DexLocation location;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @NotNull
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Embedded
    private DexMetadata metadata;

    @Embedded
    private DexFlowdata flowdata;


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public DexMetadata getMetadata() {
        return this.metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexMaintenanceRequest.class;
    }


    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public DexActor getRequester() {
        return requester;
    }

    @Override
    public void setRequester(DexActor requester) {
        this.requester = requester;
    }

    @Override
    public DexLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(DexLocation location) {
        this.location = location;
    }

    @Override
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Override
    public String getRemoveComment() {
        return removeComment;
    }

    @Override
    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }

    @Override
    public String getCancelComment() {
        return cancelComment;
    }

    @Override
    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    @Override
    public DexFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(DexFlowdata flowdata) {
        this.flowdata = flowdata;
    }
}
