package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;

@Entity(name = "DexComponent")
@Table(name = "DEX_COMP")

public class DexComponentImpl extends DexMetadata implements DexComponent {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_COMP")
    @SequenceGenerator(name = "SQ_DEX_COMP", sequenceName = "SQ_DEX_COMP", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @OneToOne(targetEntity = DexPartCodeImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PART_CODE_ID", nullable = false)
    private DexPartCode partCode;

    @ManyToOne(targetEntity = DexAssetImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", nullable = true)
    private DexAsset asset;

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexComponent.class;
    }

    @Override
    public DexPartCode getPartCode() {
        return partCode;
    }

    @Override
    public void setPartCode(DexPartCode partCode) {
        this.partCode = partCode;
    }

    @Override
    public DexAsset getAsset() {
        return asset;
    }

    @Override
    public void setAsset(DexAsset asset) {
        this.asset = asset;
    }
}