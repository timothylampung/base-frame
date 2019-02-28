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
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;

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

    @Column(name = "STOP_TIME")
    private Timestamp stopTime;

    @ManyToOne(targetEntity = DexWorkOrderImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ORDER_ID", nullable = true)
    private DexWorkOrder workOrder;

    @ManyToOne(targetEntity = DexUserImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGGER_ID", nullable = true)
    private DexUser logger;

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
    public Timestamp getStopTime() {
        return stopTime;
    }

    @Override
    public void setStopTime(Timestamp stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public DexUser getLogger() {
        return logger;
    }

    @Override
    public void setLogger(DexUser logger) {
        this.logger = logger;
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
