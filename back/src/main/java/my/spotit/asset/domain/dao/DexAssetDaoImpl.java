package my.spotit.asset.domain.dao;


import my.spotit.asset.domain.model.DexAsset;
import my.spotit.asset.domain.model.DexAssetImpl;
import my.spotit.asset.domain.model.DexLocation;
import my.spotit.common.domain.model.DexBankImpl;
import my.spotit.core.domain.GenericDaoSupport;
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
        Query q = entityManager.createQuery("select e from DexAsset e where e.code =:code")
                .setParameter("code", code);
        return (DexAsset) q.getSingleResult();
    }

    @Override
    public List<DexAsset> findByLocation(DexLocation location) {
        Query q = entityManager.createQuery("select e from DexAsset e where " +
                "e.location =:location")
                .setParameter("location", location);
        return q.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(v) from DexAsset v");
        return ((Long) query.getSingleResult()).intValue();
    }
}
