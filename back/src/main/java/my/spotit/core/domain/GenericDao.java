package my.spotit.core.domain;

import java.util.List;

import my.spotit.identity.domain.model.DexUser;

/**
 * @author canang technologies
 */
public interface GenericDao<K, I> {

    I newInstance();

    I refresh(I i);

    I findById(K k);

    List<I> find();

    List<I> find(Integer offset, Integer limit);

    Integer count();

    void save(I entity, DexUser user);

    void saveOrUpdate(I entity, DexUser user);

    void update(I entity, DexUser user);

    void deactivate(I entity, DexUser user);

    void remove(I entity, DexUser user);

    void delete(I entity, DexUser user);
}
