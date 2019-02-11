package my.spotit.asset.identity.domain.model;

import my.spotit.asset.core.domain.DexMetadata;

import javax.persistence.*;

/**
 * @author canang technologies
 */
@Table(name = "DEX_PCPL_ROLE")
@Entity(name = "DexPrincipalRole")
public class DexPrincipalRoleImpl implements DexPrincipalRole {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_PCPL_ROLE")
    @SequenceGenerator(name = "SQ_DEX_PCPL_ROLE", sequenceName = "SQ_DEX_PCPL_ROLE", allocationSize = 1)
    private Long id;

    @Column(name = "ROLE_TYPE")
    private DexRoleType role;

    @ManyToOne(targetEntity = DexPrincipalImpl.class)
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

    public DexRoleType getRole() {
        return role;
    }

    public void setRole(DexRoleType role) {
        this.role = role;
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
        return DexPrincipalRole.class;
    }

}
