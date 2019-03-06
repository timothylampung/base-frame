package my.spotit.asset.identity.domain.dao;

import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexUser;

import java.util.List;

/**
 * @author canang technologies
 */
public interface DexUserDao extends GenericDao<Long, DexUser> {

    DexUser findByEmail(String email);

    DexUser findByUsername(String username);

    DexUser findByActor(DexActor actor);

    List<DexUser> find(String filter, Integer offset, Integer limit);

    List<DexGroup> findGroups(DexUser user);

    Integer count(String filter);

    boolean isExists(String username);

    List<DexUser> findStaffMobile();
}
