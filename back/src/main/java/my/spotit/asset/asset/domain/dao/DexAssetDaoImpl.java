package my.spotit.asset.asset.domain.dao;


import my.spotit.asset.asset.domain.model.DexAsset;
import my.spotit.asset.asset.domain.model.DexAssetImpl;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.domain.model.DexBankImpl;
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

    // todo: filter
    // todo: metastate
    @Override
    public List<DexAsset> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select e from DexAsset e ");
        return query.getResultList();
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
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexAsset v");
        return ((Long) query.getSingleResult()).intValue();
    }

    // todo: metastate
    @Override
    public Integer count(DexLocation location) {
        return null;
    }
}
