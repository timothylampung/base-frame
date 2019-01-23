package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequestImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexWorkOrder")
@Table(name = "DEX_WODR")

public class DexWorkOrderImpl extends DexMetadata implements DexWorkOrder {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_WODR")
    @SequenceGenerator(name = "SQ_DEX_WODR", sequenceName = "SQ_DEX_WODR", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata dexMetadata;

    @OneToOne(targetEntity = DexActorImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTER_ID", nullable = false)
    private DexActor reporter;


    @OneToOne(targetEntity = DexActorImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNEE_ID", nullable = false)
    private DexActor assignee;

    @OneToOne(targetEntity = DexMaintenanceRequestImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAINTENANCE_REQUEST_ID", nullable = true)
    private DexMaintenanceRequest MaintenanceRequestId;

    @OneToOne(targetEntity = DexLocationImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private DexLocation LocationId;


    @OneToOne(targetEntity = DexAssetImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", nullable = true)
    private DexAsset AssetId;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public DexMetadata getMetadata() {
        return this.dexMetadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.dexMetadata =metadata;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public DexMetadata getDexMetadata() {
        return dexMetadata;
    }

    @Override
    public void setDexMetadata(DexMetadata dexMetadata) {
        this.dexMetadata = dexMetadata;
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
    public DexMaintenanceRequest getMaintenanceRequestId() {
        return MaintenanceRequestId;
    }

    @Override
    public void setMaintenanceRequestId(DexMaintenanceRequest maintenanceRequestId) {
        MaintenanceRequestId = maintenanceRequestId;
    }

    @Override
    public DexLocation getLocationId() {
        return LocationId;
    }

    @Override
    public void setLocationId(DexLocation locationId) {
        LocationId = locationId;
    }

    @Override
    public DexAsset getAssetId() {
        return AssetId;
    }

    @Override
    public void setAssetId(DexAsset assetId) {
        AssetId = assetId;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexWorkOrder.class;
    }


}
