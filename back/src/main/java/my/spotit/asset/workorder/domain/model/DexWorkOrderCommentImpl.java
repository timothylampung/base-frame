package my.spotit.asset.workorder.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;

/**
 * @author team canang
 */
@Entity(name = "DexWorkOrderComment")
@Table(name = "DEX_WORK_ORDR_CMNT")
public class DexWorkOrderCommentImpl implements DexWorkOrderComment {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_WORK_ORDR_CMNT")
    @SequenceGenerator(name = "SQ_DEX_WORK_ORDR_CMNT", sequenceName = "SQ_DEX_WORK_ORDR_CMNT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "BODY", nullable = false)
    private String body;

    @ManyToOne(targetEntity = DexUserImpl.class)
    @JoinColumn(name = "POSTER_ID")
    private DexUser poster;

    @ManyToOne(targetEntity = DexWorkOrderImpl.class)
    @JoinColumn(name = "WORK_ORDER_ID")
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
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public DexUser getPoster() {
        return poster;
    }

    @Override
    public void setPoster(DexUser poster) {
        this.poster = poster;
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

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexWorkOrderComment.class;
    }
}
