package my.spotit.asset.common.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexForm")
@Table(name = "DEX_FORM")
public class DexFormImpl implements DexForm {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_FORM")
    @SequenceGenerator(name = "SQ_DEX_FORM", sequenceName = "SQ_DEX_FORM", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "REF_NO", nullable = false)
    private String refNo;

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
    public String getRefNo() {
        return refNo;
    }

    @Override
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @Embedded
    private DexMetadata metadata;


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public DexMetadata getMetadata() {
        return this.metadata;
    }

    @Override
    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexForm.class;
    }


}
