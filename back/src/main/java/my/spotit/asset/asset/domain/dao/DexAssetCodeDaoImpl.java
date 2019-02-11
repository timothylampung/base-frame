package my.spotit.asset.asset.domain.dao;


import my.spotit.asset.asset.domain.model.DexAssetCode;
import my.spotit.asset.asset.domain.model.DexAssetCodeImpl;
import my.spotit.asset.asset.domain.model.DexLocation;
import my.spotit.asset.common.domain.model.DexBankImpl;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.inventory.domain.model.DexComponent;
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
    public DexAssetCode findAssetCodeByCode(String code) {
        Query q = entityManager.createQuery("select e from DexAssetCode e where " +
                "e.code =:code").setParameter("code", code);
        return (DexAssetCode) q.getSingleResult();
    }

    // todo: filter and metastate added?
    @Override
    public List<DexAssetCode> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexAssetCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexAssetCode>) query.getResultList();
    }

    // todo:
    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexAssetCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }
}
