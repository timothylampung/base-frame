package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexLocation")
@Table(name = "DEX_LCTN")


public class DexLocationImpl extends DexMetadata implements DexLocation{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_LCTN")
    @SequenceGenerator(name = "SQ_DEX_LCTN", sequenceName = "SQ_DEX_LCTN", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;


    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexLocation.class;
    }
}
