package com.assettagging.spotit.identity.domain.model;

import com.assettagging.spotit.core.domain.DexMetadata;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang technologies
 */
@Entity(name = "DexPrincipal")
@Table(name = "DEX_PCPL")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DexPrincipalImpl implements DexPrincipal {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_DEX_PCPL")
    @SequenceGenerator(name = "SQ_DEX_PCPL", sequenceName = "SQ_DEX_PCPL", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    @Column(name = "LOCKED", nullable = false)
    private boolean locked = false;

    @Column(name = "PRINCIPAL_TYPE")
    private DexPrincipalType principalType;

    @OneToMany(targetEntity = DexPrincipalRoleImpl.class, mappedBy = "principal", fetch = FetchType.EAGER)
    private Set<DexPrincipalRole> roles;

    @Embedded
    private DexMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public DexPrincipalType getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(DexPrincipalType principalType) {
        this.principalType = principalType;
    }

    public Set<DexPrincipalRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<DexPrincipalRole> roles) {
        this.roles = roles;
    }

    public DexMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(DexMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DexPrincipalImpl that = (DexPrincipalImpl) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexPrincipal.class;
    }

    @Override
    public String toString() {
        return "DexPrincipalImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", principalType=" + principalType +
                '}';
    }
}
