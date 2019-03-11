package my.spotit.asset.asset.domain.model;

import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.identity.domain.model.DexTeam;
import my.spotit.asset.identity.domain.model.DexTeamImpl;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "DEX_ASST_CTGY")
public class DexAssetCategoryImpl implements DexAssetCategory {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_ASST_CTGY")
    @SequenceGenerator(name = "SQ_DEX_ASST_CTGY", sequenceName = "SQ_DEX_ASST_CTGY", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = DexAssetImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSET_ID")
    private DexAsset asset;

    @ManyToMany(mappedBy = "expertise", targetEntity = DexTeamImpl.class)
    private Set<DexTeam> teams;

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

    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public Set<DexTeam> getTeams() {
        return teams;
    }

    @Override
    public void setTeams(Set<DexTeam> teams) {
        this.teams = teams;
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
        return DexAssetCategory.class;
    }
}
