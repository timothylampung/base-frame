package com.assettagging.spotit.asset.domain.dao;


import com.assettagging.spotit.asset.domain.model.DexAsset;
import com.assettagging.spotit.asset.domain.model.DexAssetImpl;
import com.assettagging.spotit.common.domain.model.DexBankImpl;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
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
    public List<DexAsset> findAllAssets() {
        Query q = entityManager.createQuery("select e from DexAsset e ");
        return q.getResultList();
    }

    @Override
    public DexAsset findAssetByAssetCode(String code) {
        Query q = entityManager.createQuery("select e from DexAsset e where e.code =:code")
                .setParameter("code",code);
        return (DexAsset) q.getSingleResult();
    }

    @Override
    public DexAsset findAssetByLocation(String location) {
        Query q = entityManager.createQuery("select e from DexAsset e where e.location =:location")
                .setParameter("location",location);
        return (DexAsset) q.getSingleResult();
    }


}
