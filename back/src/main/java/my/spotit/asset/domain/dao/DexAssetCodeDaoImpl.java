package my.spotit.asset.domain.dao;


import my.spotit.asset.domain.model.DexAssetCode;
import my.spotit.asset.domain.model.DexAssetCodeImpl;
import my.spotit.common.domain.model.DexBankImpl;
import my.spotit.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository("AssetCodeDao")
public class DexAssetCodeDaoImpl extends GenericDaoSupport<Long, DexAssetCode> implements DexAssetCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexBankImpl.class);

    public DexAssetCodeDaoImpl() {
        super(DexAssetCodeImpl.class);
    }

    @Override
    public DexAssetCode findByCode(String code) {
        Query q = entityManager.createQuery("select e from DexAssetCode e where " +
                "e.code =:code").setParameter("code", code);
        return (DexAssetCode) q.getSingleResult();
    }
}
