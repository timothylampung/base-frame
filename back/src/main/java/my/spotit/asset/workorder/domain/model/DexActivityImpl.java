package my.spotit.asset.workorder.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

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

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = DexWorkOrderImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ORDER_ID", nullable = false)
    private DexWorkOrder workOrder;

    @Embedded
    private DexMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public DexWorkOrder getWorkOrder() {
        return workOrder;
    }

    @Override
    public void setWorkOrder(DexWorkOrder workOrder) {
        this.workOrder = workOrder;
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
    public Class<?> getInterfaceClass() {
        return DexActivity.class;
    }
}
