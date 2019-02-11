package my.spotit.asset.identity.domain.model;

import my.spotit.asset.core.domain.DexMetaObject;

/**
 * @author canang technologies
 */
public interface DexPrincipalRole extends DexMetaObject {

    DexPrincipal getPrincipal();

    void setPrincipal(DexPrincipal principal);

    DexRoleType getRole();

    void setRole(DexRoleType role);
}
