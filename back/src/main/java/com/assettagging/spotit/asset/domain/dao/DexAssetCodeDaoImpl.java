package com.assettagging.spotit.asset.domain.dao;


import com.assettagging.spotit.asset.domain.model.DexAssetCode;
import com.assettagging.spotit.asset.domain.model.DexAssetCodeImpl;
import com.assettagging.spotit.common.domain.model.DexBankImpl;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("AssetCodeDao")
public class DexAssetCodeDaoImpl extends GenericDaoSupport<Long, DexAssetCode> implements DexAssetCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexBankImpl.class);

    public DexAssetCodeDaoImpl() {
        super(DexAssetCodeImpl.class);
    }

    @Override
    public List<DexAssetCode> findAllAssetCodes() {
        Query q = entityManager.createQuery("select e from DexAssetCode e ");
        return q.getResultList();
    }

    @Override
    public DexAssetCode findAssetCodeByCode(String code) {
        Query q = entityManager.createQuery("select e from DexAssetCode e where " +
                "e.code =:code").setParameter("code", code);
        return (DexAssetCode) q.getSingleResult();
    }
}
