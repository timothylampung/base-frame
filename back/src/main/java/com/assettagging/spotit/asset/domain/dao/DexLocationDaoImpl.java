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
    public List<DexLocation> findAllLocations() {
        Query q = entityManager.createQuery("select e from DexLocation e ");
        return q.getResultList();
    }

    @Override
    public DexLocation findLocationByCode(String code) {
        Query q = entityManager.createQuery("select e from DexLocation e where e.code =:code")
                .setParameter("code",code);
        return (DexLocation) q.getSingleResult();
    }




}
