package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "DexAsset")
@Table(name = "DEX_ASST")
public class DexAssetImpl implements DexAsset {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST")
    @SequenceGenerator(name = "SQ_DEX_ASST", sequenceName = "SQ_DEX_ASST", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "COST")
    private BigDecimal cost;

    @NotNull
    @Column(name = "QUANTITY")
    private Long quantity;


    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(targetEntity = DexAssetCategoryImpl.class, mappedBy = "asset", fetch = FetchType.LAZY)
    private List<DexAssetCategory> assetCategory;

    @OneToOne(targetEntity = DexAssetCodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_CODE_ID")
    private DexAssetCode assetCode;

    @ManyToOne(targetEntity = DexLocationImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private DexLocation location;

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
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public List<DexAssetCategory> getAssetCategory() {
        return assetCategory;
    }

    @Override
    public void setAssetCategory(List<DexAssetCategory> assetCategory) {
        this.assetCategory = assetCategory;
    }

    @Override
    public DexAssetCode getAssetCode() {
        return assetCode;
    }

    @Override
    public void setAssetCode(DexAssetCode assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public DexLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(DexLocation location) {
        this.location = location;
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
        return DexAsset.class;
    }


}
