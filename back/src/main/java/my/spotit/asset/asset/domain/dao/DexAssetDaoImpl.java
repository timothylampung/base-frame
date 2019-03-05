package my.spotit.asset.asset.domain.dao;


import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.domain.model.DexBankImpl;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.util.List;

@Repository("AssetDao")
public class DexAssetDaoImpl extends GenericDaoSupport<Long, DexAsset> implements DexAssetDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexBankImpl.class);

    public DexAssetDaoImpl() {
        super(DexAssetImpl.class);
    }

    @Override
    public DexAsset findByCode(String code) {
        Query query = entityManager.createQuery("select e from DexAsset e where e.code =:code")
                .setParameter("code", code);
        return (DexAsset) query.getSingleResult();
    }

    @Override
    public List<DexAsset> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexAsset s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)" +
                ") " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexAsset>) query.getResultList();
    }

    // todo: metastate
    @Override
    public List<DexAsset> findByLocation(DexLocation location) {
        Query query = entityManager.createQuery("select e from DexAsset e where " +
                "e.location =:location");
        query.setParameter("location", location);


        return query.getResultList();
    }


    // todo: metastate
    @Override
    public List<DexAsset> findByCategory(String category) {
        Query query = entityManager.createQuery("select e from DexAsset e where " +
                "e.category =:category");
        query.setParameter("category", category);


        return query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexAsset s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    // todo: metastate
    @Override
    public Integer count(DexLocation location) {
        return null;
    }
}
