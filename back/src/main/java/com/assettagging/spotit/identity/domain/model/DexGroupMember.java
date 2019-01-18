package com.assettagging.spotit.identity.domain.model;


import com.assettagging.spotit.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexGroupMember extends DexMetaObject {

    DexGroup getGroup();

    void setGroup(DexGroup group);

    DexPrincipal getPrincipal();

    void setPrincipal(DexPrincipal principal);
}
