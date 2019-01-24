package com.assettagging.spotit.inventory.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrderImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPart")
@Table(name = "DEX_PART")

public class DexPartImpl extends DexMetadata implements DexPart {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PART")
    @SequenceGenerator(name = "SQ_DEX_PART", sequenceName = "SQ_DEX_PART", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @OneToOne(targetEntity = DexPartCodeImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PART_CODE_ID", nullable = false)
    private DexPartCode partCode;

    @ManyToOne(targetEntity = DexWorkOrderImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ORDER_ID", nullable = true)
    private DexWorkOrder workOrder;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
    public DexWorkOrder getWorkOrder() {
        return workOrder;
    }

    @Override
    public void setWorkOrder(DexWorkOrder workOrder) {
        this.workOrder = workOrder;
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
