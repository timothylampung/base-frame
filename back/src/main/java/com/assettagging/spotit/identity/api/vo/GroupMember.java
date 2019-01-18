package com.assettagging.spotit.identity.api.vo;


import com.assettagging.spotit.core.api.MetaObject;

/**
 */
public class GroupMember extends MetaObject {
    private Principal principal;

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
