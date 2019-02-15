package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexActivity;
import my.spotit.asset.workorder.domain.model.DexActivityImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.sql.Timestamp;
import java.util.List;

@Repository("WorkOrderDao")
public class DexWorkOrderDaoImpl extends GenericDaoSupport<Long, DexWorkOrder> implements DexWorkOrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderImpl.class);

    public DexWorkOrderDaoImpl() {
        super(DexWorkOrderImpl.class);
    }


    // todo: metadata
    @Override
    public DexWorkOrder findByReferenceNo(String referenceNo) {
        Query q = entityManager.createQuery("select e from DexWorkOrder e where e.referenceNo =:referenceNo")
                .setParameter("referenceNo", referenceNo);
        return (DexWorkOrder) q.getSingleResult();
    }

    @Override
    public DexActivity findActivityById(Long id) {
        return entityManager.find(DexActivityImpl.class, id);
    }

    @Override
    public List<DexWorkOrder> find(String filter, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexWorkOrder s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexWorkOrder>) query.getResultList();
    }

    @Override
    public List<DexActivity> findActivities(String filter, DexWorkOrder order, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexActivity s where " +
                "upper(s.description) like upper(:filter) " +
                "and s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexActivity>) query.getResultList();
    }

    @Override
    public List<DexActivity> findActivities(DexWorkOrder order) {
        Query query = entityManager.createQuery("select s from DexActivity s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexActivity>) query.getResultList();
    }

    @Override
    public Integer count(String filter) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrder s where " +
                "(upper(s.referenceNo) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countActivity(String filter, DexWorkOrder order) {
        Query query = entityManager.createQuery("select count(s) from DexActivity s where " +
                "upper(s.description) like upper(:filter) " +
                "and s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countActivity(DexWorkOrder order) {
        Query query = entityManager.createQuery("select count(s) from DexActivity s where " +
                "s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void addActivity(DexWorkOrder wo, DexActivity activity, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        activity.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = new DexMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(DexMetaState.ACTIVE);
        activity.setMetadata(metadata);
        entityManager.persist(activity);
    }

    @Override
    public void updateActivity(DexWorkOrder wo, DexActivity activity, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        activity.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = activity.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        activity.setMetadata(metadata);
        entityManager.merge(activity);
    }

    @Override
    public void deleteActivity(DexWorkOrder wo, DexActivity activity, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        entityManager.remove(activity);
    }
}
