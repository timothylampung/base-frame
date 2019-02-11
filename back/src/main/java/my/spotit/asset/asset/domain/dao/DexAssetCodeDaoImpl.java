package my.spotit.asset.asset.domain.dao;


import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexAssetCodeImpl;
import my.spotit.asset.common.domain.model.DexBankImpl;
import my.spotit.asset.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.Query;

@Repository("assetCodeDao")
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

    // todo:
    @Override
    public List<DexAssetCode> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select e from DexAssetCode e ");
        return query.getResultList();    }

    // todo:
    @Override
    public Integer count(String filter) {
        return null;
    }
}
