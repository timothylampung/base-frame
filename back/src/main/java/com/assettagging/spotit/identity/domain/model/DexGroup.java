package com.assettagging.spotit.identity.domain.model;

import java.util.Set;

/**
 * @author canang technologies
 */
public interface DexGroup extends DexPrincipal {

    Set<DexPrincipal> getMembers();

    void setMembers(Set<DexPrincipal> members);
}
