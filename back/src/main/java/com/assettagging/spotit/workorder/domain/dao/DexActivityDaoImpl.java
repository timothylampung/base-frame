package com.assettagging.spotit.workorder.domain.dao;

import com.assettagging.spotit.core.domain.DexMetaState;
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
    public List<DexActivity> findActivities() {
        Query q = entityManager.createQuery("select e from DexActivity e ");
        return q.getResultList();
    }

    @Override
    public void addActivity(DexWorkOrder workOrder,
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

    @Override
    public List<DexActivity> find(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexActivity s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state and s.workOrder =:workOrder");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setParameter("workOrder", workOrder);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexActivity>) query.getResultList();
    }


    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexActivity s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }




    @Override
    public void updateActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user) {

    }

    @Override
    public void deleteActivity(DexWorkOrder workOrder, DexActivity activity, DexUser user) {
        entityManager.remove(activity);
    }

}
