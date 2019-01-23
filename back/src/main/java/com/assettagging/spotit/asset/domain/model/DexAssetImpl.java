package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.identity.domain.model.DexActor;
import com.assettagging.spotit.identity.domain.model.DexActorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexAset")
@Table(name = "DEX_ASST")



public class DexAssetImpl extends DexMetadata implements DexAsset {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST")
    @SequenceGenerator(name = "SQ_DEX_ASST", sequenceName = "SQ_DEX_ASST", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;


    @OneToOne(targetEntity = DexAssetCodeImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_CODE_ID", nullable = false)
    private DexAssetCode assetCode;

    @OneToOne(targetEntity = DexLocationImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private DexLocation location;


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
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
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
    public DexAssetCode getAssetCode() {
        return assetCode;
    }

    @Override
    public void setAssetCode(DexAssetCode assetCode) {
        this.assetCode = assetCode;
    }
}
