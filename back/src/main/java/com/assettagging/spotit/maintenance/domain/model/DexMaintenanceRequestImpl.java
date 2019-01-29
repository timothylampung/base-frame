package com.assettagging.spotit.maintenance.domain.model;

import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexMaintenanceRequest")
@Table(name = "DEX_MNTC")

public class DexMaintenanceRequestImpl extends DexMetadata implements DexMaintenanceRequest {


    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_MNTC_REQ")
    @SequenceGenerator(name = "SQ_MNTC_REQ", sequenceName = "SQ_MNTC_REQ", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Embedded
    private DexMetadata metadata;

    @OneToOne(targetEntity = DexActorImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", nullable = false)
    private DexActor requester;

    @OneToOne(targetEntity = DexLocationImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private DexLocation location;




    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
}
