package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.core.domain.DexFlowdata;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexWorkOrder")
@Table(name = "DEX_WORK_ORDR")
@Embeddable
public class DexWorkOrderImpl implements DexWorkOrder {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_WORK_ORDR")
    @SequenceGenerator(name = "SQ_DEX_WORK_ORDR", sequenceName = "SQ_DEX_WORK_ORDR", allocationSize = 1)
    private Long id;

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

    @OneToOne(targetEntity = DexActorImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTER_ID", nullable = true)
    private DexActor reporter;

    @OneToOne(targetEntity = DexActorImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNEE_ID", nullable = true)
    private DexActor assignee;

    @OneToOne(targetEntity = DexMaintenanceRequestImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINTENANCE_REQUEST_ID", nullable = true)
    private DexMaintenanceRequest maintenanceRequest;

    @OneToOne(targetEntity = DexLocationImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = true)
    private DexLocation location;

    @OneToOne(targetEntity = DexAssetImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", nullable = true)
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
    public DexActor getReporter() {
        return reporter;
    }

    @Override
    public void setReporter(DexActor reporter) {
        this.reporter = reporter;
    }

    @Override
    public DexActor getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(DexActor assignee) {
        this.assignee = assignee;
    }

    @Override
    public DexMaintenanceRequest getMaintenanceRequest() {
        return maintenanceRequest;
    }

    @Override
    public void setMaintenanceRequest(DexMaintenanceRequest maintenanceRequest) {
        this.maintenanceRequest = maintenanceRequest;
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
        return DexWorkOrder.class;
    }


}
