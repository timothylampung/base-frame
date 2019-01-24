package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexActivityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository("ActivityDao")
public class DexActivityDaoImpl extends GenericDaoSupport<Long, DexActivity> implements DexActivityDao {


    private static final Logger LOG = LoggerFactory.getLogger(DexActivityImpl.class);

    public DexActivityDaoImpl() {
        super(DexActivityImpl.class);
    }


    @Override
    public DexActivity findActivityByCode(String code) {
        Query q = entityManager.createQuery("select e from DexActivity e where e.code =:code")
                .setParameter("code",code);
        return (DexActivity) q.getSingleResult();
    }

    @Override
    public List<DexActivity> findAllActivitys() {
        Query q = entityManager.createQuery("select e from DexActivity e ");
        return q.getResultList();
    }



}
