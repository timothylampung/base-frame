package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.GenericDaoSupport;
import com.assettagging.spotit.identity.domain.model.DexUser;
import com.assettagging.spotit.workorder.domain.model.DexActivity;
import com.assettagging.spotit.workorder.domain.model.DexActivityImpl;
import com.assettagging.spotit.workorder.domain.model.DexWorkOrder;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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
                .setParameter("code", code);
        return (DexActivity) q.getSingleResult();
    }

    @Override
    public DexActivity findActivityById(Long id) {
        return null;
    }

    @Override
    public List<DexActivity> findAllActivitys() {
        Query q = entityManager.createQuery("select e from DexActivity e ");
        return q.getResultList();
    }

    @Override
    public void saveActivity(DexWorkOrder workOrder,
                             DexActivity activity,
                             DexUser user) {
        Assert.notNull(user, "User cannot be null");
        Assert.notNull(workOrder, "Object cannot be null");
        try {
            activity.setWorkOrder(workOrder);
            save(activity, user);
        } catch (HibernateException e) {
            LOG.debug("error occurred", e);
        }
    }


}
