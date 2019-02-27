package my.spotit.asset.workorder.domain.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.spotit.asset.core.domain.DexMetadata;

/**
 * @author canang technologies
 */
@Entity(name = "DexWorkOrderLog")
@Table(name = "DEX_WORK_ORDR_LOG")
public class DexWorkOrderLogImpl implements DexWorkOrderLog
{
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_WORK_ORDR_LOG")
    @SequenceGenerator(name = "SQ_DEX_WORK_ORDR_LOG", sequenceName = "SQ_DEX_WORK_ORDR_LOG", allocationSize = 1)
    private Long id;

    @Column(name = "START_TIME")
    private Timestamp startTime;

    @Column(name = "END_TIME")
    private Timestamp endTime;

    @ManyToOne(targetEntity = DexWorkOrderImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ORDER_ID", nullable = true)
    private DexWorkOrder workOrder;

    @Embedded
    private DexMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Timestamp getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public Timestamp getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public DexWorkOrder getWorkOrder() {
        return workOrder;
    }

    @Override
    public void setWorkOrder(DexWorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexWorkOrderLog.class;
    }
}
