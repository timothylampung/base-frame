package com.assettagging.spotit.asset.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.maintenance.domain.model.DexMaintenanceRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexAssetCode")
@Table(name = "DEX_ASST_CODE")
public class DexAssetCodeImpl implements DexAssetCode {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST_CODE")
    @SequenceGenerator(name = "SQ_DEX_ASST_CODE", sequenceName = "SQ_DEX_ASST_CODE", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @Override
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

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
        return DexAssetCode.class;
    }
}


