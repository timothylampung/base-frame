package com.assettagging.spotit.maintenance.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.core.domain.DexFlowdata;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;

import java.util.Date;

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

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "REQUESTED_DATE")
    private Date requestedDate;

    @Column(name = "DELEGATED")
    private Boolean delegated;

    @NotNull
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @ManyToOne(targetEntity = DexActorImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", nullable = false)
    private DexActor requester;

    @ManyToOne(targetEntity = DexActorImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELEGATOR_ID", nullable = false)
    private DexActor delegator;

    @ManyToOne(targetEntity = DexActorImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "VERIFIER_ID", nullable = false)
    private DexActor verifier;

    @ManyToOne(targetEntity = DexLocationImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private DexLocation location;

    @ManyToOne(targetEntity = DexAssetImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", nullable = false)
    private DexAsset asset;

    @Embedded
    private DexMetadata metadata;

    @Embedded
    private DexFlowdata flowdata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Date getRequestedDate() {
        return requestedDate;
    }

    @Override
    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
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

    public Boolean isDelegated() {
        return delegated;
    }

    @Override
    public void setDelegated(Boolean delegated) {
        this.delegated = delegated;
    }

    @Override
    public DexActor getDelegator() {
        return delegator;
    }

    @Override
    public void setDelegator(DexActor delegator) {
        this.delegator = delegator;
    }

    @Override
    public DexActor getVerifier() {
        return verifier;
    }

    @Override
    public void setVerifier(DexActor verifier) {
        this.verifier = verifier;
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
    public DexAsset getAsset() {
        return asset;
    }

    @Override
    public void setAsset(DexAsset asset) {
        this.asset = asset;
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
    public DexFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(DexFlowdata flowdata) {
        this.flowdata = flowdata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexMaintenanceRequest.class;
    }
}
