package my.spotit.asset.inventory.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexPartCode")
@Table(name = "DEX_PART_CODE")

public class DexPartCodeImpl implements DexPartCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_PART_CODE")
    @SequenceGenerator(name = "SQ_DEX_PART_CODE", sequenceName = "SQ_DEX_PART_CODE", allocationSize = 1)
    private Long id;


    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @Embedded
    private DexMetadata metadata;

    @Override
    public Long getId() {
        return id;
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
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
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
        return DexPartCode.class;
    }
}
