package my.spotit.asset.workorder.domain.dao;

import my.spotit.asset.core.domain.DexMetaState;
import my.spotit.asset.core.domain.DexMetadata;
import my.spotit.asset.core.domain.GenericDaoSupport;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.workorder.domain.model.DexWorkOrderActivity;
import my.spotit.asset.workorder.domain.model.DexWorkOrderActivityImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrder;
import my.spotit.asset.workorder.domain.model.DexWorkOrderComment;
import my.spotit.asset.workorder.domain.model.DexWorkOrderCommentImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrderImpl;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLog;
import my.spotit.asset.workorder.domain.model.DexWorkOrderLogImpl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.sql.Timestamp;
import java.util.List;

@Repository("workOrderDao")
public class DexWorkOrderDaoImpl extends GenericDaoSupport<Long, DexWorkOrder> implements DexWorkOrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(DexWorkOrderImpl.class);

    public DexWorkOrderDaoImpl() {
        super(DexWorkOrderImpl.class);
    }


    // todo: metadata
    @Override
    public DexWorkOrder findByReferenceNo(String referenceNo) {
        Query query = entityManager.createQuery("select e from DexWorkOrder e where " +
                "e.referenceNo =:referenceNo");
        query.setParameter("referenceNo", referenceNo);
        return (DexWorkOrder) query.getSingleResult();
    }

    @Override
    public DexWorkOrderActivity findActivityById(Long id) {
        return entityManager.find(DexWorkOrderActivityImpl.class, id);
    }

    @Override
    public DexWorkOrderLog findLogById(Long id) {
        return entityManager.find(DexWorkOrderLogImpl.class, id);
    }

    @Override
    public DexWorkOrderComment findCommentById(Long id) {
        return entityManager.find(DexWorkOrderCommentImpl.class, id);
    }

    @Override
    public DexWorkOrderLog findUnendedLog(DexWorkOrder order) {
        Query query = entityManager.createQuery("select s from DexWorkOrderLog s where " +
                " s.workOrder = :order " +
                "and s.startTime is not null " +
                "and s.stopTime is null " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (DexWorkOrderLog) query.getSingleResult();
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
    public List<DexWorkOrderActivity> findActivities(String filter, DexWorkOrder order, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexActivity s where " +
                "upper(s.description) like upper(:filter) " +
                "and s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<DexWorkOrderActivity>) query.getResultList();
    }

    @Override
    public List<DexWorkOrderActivity> findActivities(DexWorkOrder order) {
        Query query = entityManager.createQuery("select s from DexActivity s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexWorkOrderActivity>) query.getResultList();
    }

    @Override
    public List<DexWorkOrderLog> findLogs(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexWorkOrderLog s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", workOrder);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexWorkOrderLog>) query.getResultList();
    }

    @Override
    public List<DexWorkOrderLog> findLogs(DexWorkOrder order) {
        Query query = entityManager.createQuery("select s from DexWorkOrderLog s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexWorkOrderLog>) query.getResultList();
    }

    @Override
    public List<DexWorkOrderComment> findComments(String filter, DexWorkOrder workOrder, Integer offset, Integer limit) {
        Query query = entityManager.createQuery("select s from DexWorkOrderComment s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", workOrder);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexWorkOrderComment>) query.getResultList();
    }

    @Override
    public List<DexWorkOrderComment> findComments(DexWorkOrder order) {
        Query query = entityManager.createQuery("select s from DexWorkOrderComment s where " +
                " s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return (List<DexWorkOrderComment>) query.getResultList();
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
    public Integer countLog(String filter, DexWorkOrder order) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrderLog s where " +
                "upper(s.description) like upper(:filter) " +
                "and s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("filter", WILDCARD + filter + WILDCARD);
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countLog(DexWorkOrder order) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrderLog s where " +
                "s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Integer countComment(DexWorkOrder workOrder) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrderComment s where " +
                "s.workOrder = :order " +
                "and s.metadata.state = :state ");
        query.setParameter("order", workOrder);
        query.setParameter("state", DexMetaState.ACTIVE);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public boolean hasUnendedLog(DexWorkOrder order) {
        Query query = entityManager.createQuery("select count(s) from DexWorkOrderLog s where " +
                " s.workOrder = :order " +
                "and s.startTime is not null " +
                "and s.stopTime is null " +
                "and s.metadata.state = :state ");
        query.setParameter("order", order);
        query.setParameter("state", DexMetaState.ACTIVE);
        return 0 < ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void addActivity(DexWorkOrder wo, DexWorkOrderActivity activity, DexUser user) {
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
    public void updateActivity(DexWorkOrder wo, DexWorkOrderActivity activity, DexUser user) {
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
    public void deleteActivity(DexWorkOrder wo, DexWorkOrderActivity activity, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        entityManager.remove(activity);
    }

    @Override
    public void addLog(DexWorkOrder wo, DexWorkOrderLog log, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        log.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = new DexMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(DexMetaState.ACTIVE);
        log.setMetadata(metadata);
        entityManager.persist(log);
    }

    @Override
    public void updateLog(DexWorkOrder wo, DexWorkOrderLog log, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        log.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = log.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        log.setMetadata(metadata);
        entityManager.merge(log);
    }

    @Override
    public void deleteLog(DexWorkOrder wo, DexWorkOrderLog log, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        entityManager.remove(log);
    }

    @Override
    public void addComment(DexWorkOrder wo, DexWorkOrderComment comment, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        comment.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = new DexMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(DexMetaState.ACTIVE);
        comment.setMetadata(metadata);
        entityManager.persist(comment);
    }

    @Override
    public void updateComment(DexWorkOrder wo, DexWorkOrderComment comment, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        comment.setWorkOrder(wo);

        // prepare metadata
        DexMetadata metadata = comment.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        comment.setMetadata(metadata);
        entityManager.merge(comment);
    }

    @Override
    public void deleteComment(DexWorkOrder wo, DexWorkOrderComment comment, DexUser user) {
        Validate.notNull(user, "User cannot be null");
        entityManager.remove(comment);
    }
}
