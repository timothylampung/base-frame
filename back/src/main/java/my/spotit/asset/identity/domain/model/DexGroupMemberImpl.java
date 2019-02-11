package my.spotit.asset.identity.domain.model;


import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

/**
 * @author canang technologies
 */
@Entity(name = "DexGroupMember")
@Table(name = "DEX_GROP_MMBR")
public class DexGroupMemberImpl implements DexGroupMember {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_GROP_MMBR")
    @SequenceGenerator(name = "SQ_DEX_GROP_MMBR", sequenceName = "SQ_DEX_GROP_MMBR", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = DexGroupImpl.class)
    @JoinColumn(name = "GROUP_ID")
    private DexGroup group;

    @OneToOne(targetEntity = DexPrincipalImpl.class)
    @JoinColumn(name = "PRINCIPAL_ID")
    private DexPrincipal principal;

    @Embedded
    private DexMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DexGroup getGroup() {
        return group;
    }

    public void setGroup(DexGroup group) {
        this.group = group;
    }

    public DexPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(DexPrincipal principal) {
        this.principal = principal;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexGroupMember.class;
    }


}
