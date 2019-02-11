package my.spotit.asset.identity.domain.model;

import java.util.Set;

/**
 * @author canang technologies
 */
public interface DexGroup extends DexPrincipal {

    Set<DexPrincipal> getMembers();

    void setMembers(Set<DexPrincipal> members);
}
