package com.assettagging.spotit.asset.domain.dao;



import com.assettagging.spotit.asset.domain.model.DexLocation;
import com.assettagging.spotit.asset.domain.model.DexLocationImpl;
import com.assettagging.spotit.common.domain.model.DexBankImpl;
import com.assettagging.spotit.core.domain.GenericDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("LocationDao")
public class DexLocationDaoImpl extends GenericDaoSupport<Long, DexLocation> implements DexLocationDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexBankImpl.class);

    public DexLocationDaoImpl() {
        super(DexLocationImpl.class);
    }


    @Override
    public DexLocation findByCode(String code) {
        Query q = entityManager.createQuery("select e from DexLocation e where e.code =:code")
                .setParameter("code",code);
        return (DexLocation) q.getSingleResult();
    }

    @Override
    public List<DexLocation> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select v from DexLocation v where " +
                "(upper(v.name) like upper(:filter)" +
                "or upper(v.description) like upper(:filter)" +
                "or upper(v.address) like upper(:filter)" +
                ")" +
                "order by v.name");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexLocation>) query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(e) from DexLocation e");
        return ((Long) query.getSingleResult()).intValue();
    }



}
