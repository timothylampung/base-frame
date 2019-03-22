package my.spotit.asset.identity.domain.model;

import my.spotit.asset.asset.domain.model.DexAssetCategory;
import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEX_TEAM")
public class DexTeamImpl extends DexActorImpl implements DexTeam {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = DexStaffImpl.class)
    @JoinTable(name = "DEX_TEAM_MMBR", joinColumns = {
            @JoinColumn(name = "TEAM_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "STAFF_ID",
                    nullable = false, updatable = false)})
    private Set<DexStaff> staff;


    @Embedded
    private DexMetadata metadata;

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
