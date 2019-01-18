package com.assettagging.spotit.identity.domain.model;

import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexPrincipalRole extends DexMetaObject {

    DexPrincipal getPrincipal();

    void setPrincipal(DexPrincipal principal);

    DexRoleType getRole();

    void setRole(DexRoleType role);
}
