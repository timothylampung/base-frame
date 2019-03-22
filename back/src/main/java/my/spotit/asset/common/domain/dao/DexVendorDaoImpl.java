package my.spotit.asset.common.domain.dao;

import my.spotit.asset.common.domain.model.DexVendor;
import my.spotit.asset.common.domain.model.DexVendorImpl;
import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("VendorDao")
public class DexVendorDaoImpl extends GenericDaoSupport<Long, DexVendor> implements DexVendorDao {


    private static final Logger LOG = LoggerFactory.getLogger(DexVendorImpl.class);

    public DexVendorDaoImpl() {
        super(DexVendorImpl.class);
    }


    @Override
    public DexVendor findVendorByCode(String code) {
        Query query = entityManager.createQuery("select e from DexVendor e where e.code =:code")
                .setParameter("code", code);
        return (DexVendor) query.getSingleResult();
    }

    @Override
    public List<DexVendor> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexVendor s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)" +
                ") " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexVendor>) query.getResultList();
    }


    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexVendor s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }


}
