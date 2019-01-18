package com.assettagging.spotit.identity.domain.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang technologies
 */
@Entity(name = "DexGroup")
@Table(name = "DEX_GROP")
public class DexGroupImpl extends DexPrincipalImpl implements DexGroup {

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = DexPrincipalImpl.class)
    @JoinTable(name = "DEX_GROP_MMBR", joinColumns = {
            @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PRINCIPAL_ID",
                    nullable = false, updatable = false)})
    private Set<DexPrincipal> members;

    public DexGroupImpl() {
        setPrincipalType(DexPrincipalType.GROUP);
    }

    public Set<DexPrincipal> getMembers() {
        return members;
    }

    public void setMembers(Set<DexPrincipal> members) {

        this.members = members;
    }

    @Override
    public Class<?> getInterfaceClass() {

        return DexGroup.class;
    }

}
