package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "DexLocation")
@Table(name = "DEX_LCTN")
public class DexLocationImpl extends DexMetadata implements DexLocation{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_LCTN")
    @SequenceGenerator(name = "SQ_DEX_LCTN", sequenceName = "SQ_DEX_LCTN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "NAME")
    private String name;


    @ManyToOne(targetEntity = DexLocationImpl.class, fetch = FetchType.LAZY)
    private DexLocation parent;

    @OneToMany(targetEntity = DexAssetImpl.class, mappedBy = "location", fetch = FetchType.LAZY)
    private List<DexAsset> assets;

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public DexLocation getParent() {
        return parent;
    }

    @Override
    public void setParent(DexLocation parent) {
        this.parent = parent;
    }

    @Override
    public List<DexAsset> getAssets() {
        return assets;
    }

    @Override
    public void setAssets(List<DexAsset> assets) {
        this.assets = assets;
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
        return DexLocation.class;
    }
}
