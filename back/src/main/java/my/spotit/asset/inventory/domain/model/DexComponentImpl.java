package my.spotit.asset.inventory.domain.model;

import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "DexComponent")
@Table(name = "DEX_COMP")

public class DexComponentImpl  implements DexComponent {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_DEX_COMP")
    @SequenceGenerator(name = "SQ_DEX_COMP", sequenceName = "SQ_DEX_COMP", allocationSize = 1)
    private Long id;

    @Embedded
    private DexMetadata metadata;

    @OneToOne(targetEntity = DexPartCodeImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PART_CODE_ID", nullable = false)
    private DexPartCode partCode;

    @ManyToOne(targetEntity = DexAssetImpl.class,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID", nullable = true)
    private DexAsset asset;

   @Override
    public Long getId() {
        return id;
    }


    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexComponent.class;
    }

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public DexAsset getAsset() {
        return asset;
    }

    @Override
    public void setAsset(DexAsset asset) {
        this.asset = asset;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}