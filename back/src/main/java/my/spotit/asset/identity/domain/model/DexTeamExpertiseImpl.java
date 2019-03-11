package my.spotit.asset.identity.domain.model;


import my.spotit.asset.asset.domain.model.DexAssetCategory;
import my.spotit.asset.asset.domain.model.DexAssetCategoryImpl;
import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

@Table(name = "DEX_XPRT")
@Entity(name = "DexTeamExpertiseImpl")
public class DexTeamExpertiseImpl implements DexTeamExpertise {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_XPRT")
    @SequenceGenerator(name = "SQ_DEX_XPRT", sequenceName = "SQ_DEX_XPRT", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = DexTeamImpl.class)
    @JoinColumn(name = "TEAM_ID")
    private DexTeam team;

    @OneToOne(targetEntity = DexAssetCategoryImpl.class)
    @JoinColumn(name = "EXPERTISE_ID")
    private DexAssetCategory expertise;

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
    public DexTeam getTeam() {
        return team;
    }

    @Override
    public void setTeam(DexTeam team) {
        this.team = team;
    }

    @Override
    public DexAssetCategory getExpertise() {
        return expertise;
    }

    @Override
    public void setExpertise(DexAssetCategory expertise) {
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
        return DexTeamExpertise.class;
    }
}
