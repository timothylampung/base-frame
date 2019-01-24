package com.assettagging.spotit.workorder.domain.model;

import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexActivity")
@Table(name = "DEX_ATVT")


public class DexActivityImpl extends DexMetadata implements DexActivity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ATVT")
    @SequenceGenerator(name = "SQ_DEX_ATVT", sequenceName = "SQ_DEX_ATVT", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;


    @ManyToOne(targetEntity = DexWorkOrderImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ORDER_ID", nullable = false)
    private DexWorkOrder workOrder;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;


    @Override
    public Class<?> getInterfaceClass() {
        return DexActivity.class;
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
    public DexMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public DexWorkOrder getWorkOrder() {
        return workOrder;
    }

    @Override
    public void setWorkOrder(DexWorkOrder workOrder) {
        this.workOrder = workOrder;
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
}
