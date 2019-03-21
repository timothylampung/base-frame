package my.spotit.asset.identity.domain.model;

import my.spotit.asset.asset.domain.model.DexAssetCategory;
import my.spotit.asset.asset.domain.model.DexAssetCategoryImpl;
import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "DexTeamImpl")
@Table(name = "DEX_TEAM")
public class DexTeamImpl implements DexTeam {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_TEAM")
    @SequenceGenerator(name = "SQ_DEX_TEAM", sequenceName = "SQ_DEX_TEAM", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(targetEntity = DexAssetCategoryImpl.class)
    @JoinTable(name = "DEX_XPRT",
            joinColumns =
            @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "EXPERTISE_ID", referencedColumnName = "ID"))
    private Set<DexAssetCategory> expertise;

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<DexAssetCategory> getExpertise() {
        return expertise;
    }

    @Override
    public void setExpertise(Set<DexAssetCategory> expertise) {
        this.expertise = expertise;
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
        return DexTeam.class;
    }
}
