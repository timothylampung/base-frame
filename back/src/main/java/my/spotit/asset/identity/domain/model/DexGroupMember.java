package my.spotit.asset.identity.domain.model;


import my.spotit.asset.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexGroupMember extends DexMetaObject {

    DexGroup getGroup();

    void setGroup(DexGroup group);

    DexPrincipal getPrincipal();

    void setPrincipal(DexPrincipal principal);
}
