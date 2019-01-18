package com.assettagging.spotit.identity.domain.model;


import com.assettagging.spotit.core.domain.DexMetaObject;

import java.util.Set;


/**
 * @author canang technologies
 */
public interface DexPrincipal extends DexMetaObject {

    String getName();

    void setName(String name);

    DexPrincipalType getPrincipalType();

    void setPrincipalType(DexPrincipalType principalType);

    boolean isLocked();

    void setLocked(boolean locked);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Set<DexPrincipalRole> getRoles();

    void setRoles(Set<DexPrincipalRole> roles);

}
