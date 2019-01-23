package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPartCode")
@Table(name = "DEX_PART_CODE")

public class DexPartCodeImpl extends DexMetadata implements DexPartCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PART_CODE")
    @SequenceGenerator(name = "SQ_DEX_PART_CODE", sequenceName = "SQ_DEX_PART_CODE", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public DexMetadata getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {

    }

    @Override
    public Class<?> getInterfaceClass() {
        return null;
    }
}
