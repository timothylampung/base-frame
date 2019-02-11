package my.spotit.asset.identity.domain.dao;


import my.spotit.asset.core.domain.GenericDao;
import my.spotit.asset.identity.domain.model.DexActor;
import my.spotit.asset.identity.domain.model.DexActorType;

import java.util.List;

/**
 * @author canang technologies
 */
public interface DexActorDao extends GenericDao<Long, DexActor> {

    DexActor findByCode(String code);

    DexActor findByIdentityNo(String identityNo);

    DexActor findByEmail(String email);

    List<DexActor> find(String filter, Integer offset, Integer limit);

    List<DexActor> find(DexActorType type, Integer offset, Integer limit);

    List<DexActor> find(String filter, DexActorType type, Integer offset, Integer limit);

    Integer count(String filter);

    Integer count(String filter, DexActorType type);

    Integer count(DexActorType type);

}
